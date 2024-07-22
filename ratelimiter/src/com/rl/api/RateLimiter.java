package com.rl.api;

public interface RateLimiter {

    public boolean tryAcquire();

}
