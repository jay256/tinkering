package com.lb;

import com.lb.api.Controller;
import com.lb.models.Request;
import com.lb.models.ServerNode;
import com.lb.registry.ServerRegistry;

public abstract class LoadBalancer implements Controller {

    public ServerRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(ServerRegistry registry) {
        this.registry = registry;
    }

    private ServerRegistry registry;

    public LoadBalancer() {
        this.registry = new ServerRegistry();
    }

    public abstract ServerNode loadBalance() throws Exception;

    @Override
    public boolean handleRequest(Request request) {
        //validate request
        try {
            System.out.println("Load balancing request: " + request.getRequestId());
            return null!=loadBalance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
