package com.bq.api;

public interface MyQueue<T> {

    public boolean enqueue(T t) throws InterruptedException;

    public T dequeue() throws InterruptedException;

}
