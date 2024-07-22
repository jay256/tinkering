package com.rl.api.impl;

import com.rl.api.TokenRateLimiter;

import java.util.concurrent.atomic.AtomicLong;

public class HttpTokenRateLimiter extends HttpRateLimiter implements TokenRateLimiter {

    private final long maxTokens;
    private final long refillIntervalMillis;
    private final long refillTokens;
    private final AtomicLong tokens;
    private long lastRefillTimestamp;

    public HttpTokenRateLimiter(int maxTokens, long refillIntervalMillis, long refillTokens) {
        this.maxTokens = maxTokens;
        this.refillIntervalMillis = refillIntervalMillis;
        this.refillTokens = refillTokens;
        this.tokens = new AtomicLong(maxTokens);
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    @Override
    public synchronized boolean tryAcquire() {
        refillTokens();
        if(tokens.get() > 0) {
            tokens.decrementAndGet();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTimestamp;
        if(elapsedTime > refillIntervalMillis) {
            long tokensToAdd = (elapsedTime / refillIntervalMillis)*refillTokens;
            long newTokenCount = Math.min(maxTokens, tokens.get()+tokensToAdd);
            tokens.set(newTokenCount);
            lastRefillTimestamp = now;
        }
    }
}
