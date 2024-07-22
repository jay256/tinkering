package com.lb.registry;

import com.lb.api.Registry;
import com.lb.models.ServerNode;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ServerRegistry implements Registry {

    public Set<ServerNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<ServerNode> nodes) {
        this.nodes = nodes;
    }

    public Set<ServerNode> nodes;

    public ServerRegistry() {
        nodes = ConcurrentHashMap.newKeySet();
    }

    @Override
    public boolean register(Object o) {
        if(o instanceof ServerNode) {
            nodes.add((ServerNode) o);
            return true;
        }
        else{
            return false;
        }
    }
}
