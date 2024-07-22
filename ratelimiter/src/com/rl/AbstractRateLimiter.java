package com.rl;

import com.rl.api.RateLimiter;

public class AbstractRateLimiter implements RateLimiter {

    @Override
    public boolean tryAcquire() {
        return false;
    }

}
