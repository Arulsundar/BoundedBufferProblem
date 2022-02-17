package com.queue;

import com.new2.FileQueue;
import com.new2.MyArrayBlockingQueue;

public class WrapperQueue {
    private final MyArrayBlockingQueue producerQueue ;
    private final MyArrayBlockingQueue consumerQueue ;
    private final FileQueue fileQueue ;

    public WrapperQueue(int capacity) {
    	//splits the queue into two
        producerQueue = new MyArrayBlockingQueue(capacity) ;
        consumerQueue = new MyArrayBlockingQueue(capacity / 2) ;
        fileQueue = new FileQueue() ;

        // Tries to keep the producerQueue empty at all times
        Thread moveToFileDaemon = new Thread(() -> {
            while(true) moveToFile();
        }) ;
        moveToFileDaemon.setDaemon(true);
        moveToFileDaemon.start();

        // Tries to keep the consumerQueue full at all times
        Thread moveFromFileDaemon = new Thread(() -> {
            while(true) moveFromFile();
        }) ;
        moveFromFileDaemon.setDaemon(true);
        moveFromFileDaemon.start() ;
    }
    //2.take values from the producer queue and place it in the file and always make the producer queue empty using service thread
    private void moveToFile() {
        Integer taken = producerQueue.take() ;
        fileQueue.offer(String.valueOf(taken));
    }
    //3.take values from file and put it in consumer queue and always make it full using service Threads
    private void moveFromFile() {
        String taken = fileQueue.poll();
        consumerQueue.put(Integer.valueOf(taken));
    }
    //1.Producer put values in the producer queue
    public void put(Integer e) {
        producerQueue.put(e);
    }
    //4.At last,Consumer consumes it from Consumer queue
    public Integer take() {
        return consumerQueue.take();
    }
}
