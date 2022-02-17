package com.blockingqueue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.finall.FileStore;

public class MyBlockingQueue {
	private Queue<Integer> q;
	private int capacity;
	private ReentrantLock lock;
	private Condition notEmpty;
	private FileStore file;
//	private OutputStream writer;
//	private BufferedReader reader;

	public MyBlockingQueue(int capacity) {
		this.q = new LinkedList<Integer>();
		this.capacity = capacity;
		lock = new ReentrantLock(true);
		this.notEmpty = lock.newCondition();
//		try {
//			this.writer=Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING);
//			this.reader=Files.newBufferedReader(path);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		this.file = new FileStore();
	}

	public void shiftFromFileToQueue() throws IOException {
		String line = file.readLine();
		while (q.size() < capacity && line != null) {
			q.add(Integer.valueOf(line));
			System.out.println("Shifting from file");
		}
	}

	public void put(Integer e) throws InterruptedException, IOException {
		lock.lock();
		try {
			shiftFromFileToQueue();
			if (q.size() < capacity)
				q.add(e);
			else {
//				writer.write(String.format("%s%n", e).getBytes());
//			    System.out.println("writing to a file");
				file.writeLine(e);
			}
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public Integer take() throws InterruptedException, IOException {
		lock.lock();
		try {
			while (q.size() == 0)
				notEmpty.await();
			Integer item = q.remove();
			shiftFromFileToQueue();
			return item;
		} finally {
			lock.unlock();
		}

	}

}
/*
 * 1.producer should not wait for consuming... 
 * 2.File writing Methods should be in a separate file... 
 * 3.Even if we have started multiple threads of producer and consumer,the queue must works properly... 
 * Do it with Array implementation and make the implementation more efficient with thread safe (instead of LinkedList)..
 */
