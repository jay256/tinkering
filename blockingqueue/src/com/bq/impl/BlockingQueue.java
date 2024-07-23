package com.bq.impl;

import com.bq.MyAbstractQueue;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> extends MyAbstractQueue<T> {

    private int capacity;
    private Queue<T> queue;

    public BlockingQueue(int capacity){
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    @Override
    public synchronized boolean enqueue(T t) throws InterruptedException {
        while(queue.size()==capacity){
            wait();
        }
        if(queue.isEmpty()){
            notifyAll();
        }
        System.out.println("Enqueuing: " + t.toString());
        return queue.add(t);
    }

    @Override
    public synchronized T dequeue() throws InterruptedException {
        while(queue.isEmpty()){
            wait();
        }
        if(queue.size()==capacity){
            notifyAll();
        }
        T t=queue.poll();
        System.out.println("Dequeuing: " + t.toString());
        return t;
    }
}
