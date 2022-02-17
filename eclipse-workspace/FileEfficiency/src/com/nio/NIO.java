package com.nio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


import java.io.FileNotFoundException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NIO {

    private final RandomAccessFile raf ;

    private long readPosition;
    private long writePosition;

    private int size = 0;

    private final ReentrantLock lock = new ReentrantLock() ;
    private final Condition notEmpty = lock.newCondition() ;

    public NIO() {
        try {
            raf = new RandomAccessFile("C:\\Users\\gnana-pt4726\\Desktop\\New\\new.txt", "rw") ;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void offer(Integer s) {
        lock.lock() ;
        try {
            raf.seek(writePosition);

            raf.writeBytes(String.format("%s%n", s));

            writePosition = raf.getFilePointer() ;

            size++ ;

            notEmpty.signal();

//            System.out.println(">>> FileQueue :: offer :: " + s);
        } catch (IOException e) {
            throw new RuntimeException(e) ;
        } finally {
            lock.unlock();
        }
    }

    // Blocking poll
    public String poll() {
        lock.lock() ;
        try {
            while(size == 0)
                notEmpty.await();

            raf.seek(readPosition) ;

            String s = raf.readLine() ;

            readPosition = raf.getFilePointer() ;

            size-- ;

            System.out.println(">>> FileQueue :: poll  :: " + s);

            return s;
        } catch (Exception e) {
            throw new RuntimeException(e) ;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return size ;
    }
}
