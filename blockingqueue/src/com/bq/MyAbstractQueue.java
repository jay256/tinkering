package com.bq;

import com.bq.api.MyQueue;

public class MyAbstractQueue<T> implements MyQueue<T> {
    @Override
    public boolean enqueue(T t) throws InterruptedException {
        return false;
    }

    @Override
    public T dequeue() throws InterruptedException {
        return null;
    }
}
