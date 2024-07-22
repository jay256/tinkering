package com.rl.api;

public interface TokenRateLimiter extends RateLimiter{

    public void refillTokens();

}
