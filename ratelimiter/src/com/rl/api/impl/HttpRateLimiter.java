package com.rl.api.impl;

import com.rl.AbstractRateLimiter;

public class HttpRateLimiter extends AbstractRateLimiter {


    @Override
    public boolean tryAcquire() {
        return super.tryAcquire();
    }
}
