package com.q;

public class Main {
    public static void main(String[] args) {

        MyBlockingQueue q = new MyBlockingQueue(3) ;

//        while(true)
//        {
          new Thread(new Producer(q)).start() ;

        new Thread(new Consumer(q)).start();
//        }
    }
}