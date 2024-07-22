package com.lb.api;

import com.lb.models.Request;

public interface Controller {
    boolean handleRequest(Request request);
}
