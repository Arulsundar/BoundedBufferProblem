package com.q;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBlockingQueue {
    private final BlockingQueue<Integer> q ;
    private final int capacity ;
    private OutputStream writer;
    private BufferedReader reader ;

    public MyBlockingQueue(int capacity) {
        this.q = new LinkedBlockingQueue<>(capacity) ;
        this.capacity = capacity;
        Path path = Paths.get("C:\\Users\\gnana-pt4726\\Desktop\\New\\new.txt");
        try {
            this.writer = Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING) ;
            this.reader = Files.newBufferedReader(path) ;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0) ;
        }
    }

    private void shiftFromFileToQueue() throws IOException, InterruptedException {
        String line ;
        while (q.size() < capacity && (line = reader.readLine()) != null) {
            q.put(Integer.valueOf(line)) ;
//            System.out.println("shift takes place");
        }
    }

    public Integer take() throws InterruptedException, IOException {
        Integer taken = q.take();
        shiftFromFileToQueue();
        return taken ;
    }

    public void put(Integer e) throws InterruptedException, IOException {
        shiftFromFileToQueue();
        if(q.size() < capacity)
            q.put(e) ;
        else 
        {
        	writer.write(String.format("%s%n", e).getBytes());
//        	System.out.println("writing in a file");
        }
    }
}