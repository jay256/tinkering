package com.lb.impl;

import com.lb.LoadBalancer;
import com.lb.models.ServerNode;

public class RoundRobinLoadBalancer extends LoadBalancer {

    private ServerNode lastServerNode;

    public RoundRobinLoadBalancer() {
        super();
    }

    @Override
    public synchronized ServerNode loadBalance() throws Exception{
        if(lastServerNode==null){
            if(null!=this.getRegistry()
                    && null!=this.getRegistry().getNodes()
                    && !this.getRegistry().getNodes().isEmpty()){
                lastServerNode = this.getRegistry().getNodes().iterator().next();
                System.out.println("Assigning server: " + lastServerNode.getServerId());
                return lastServerNode;
            }
            else {
                throw new Exception("No server available to handle request");
            }
        }
        else{
            int size = this.getRegistry().getNodes().size();
            ServerNode serverNode;
            for(int i=0;i<size;i++){
                serverNode = (ServerNode)this.getRegistry().getNodes().toArray()[i];
                if(lastServerNode==serverNode && i<size-1){
                    lastServerNode = (ServerNode)this.getRegistry().getNodes().toArray()[i+1];
                    System.out.println("Assigning server: " + lastServerNode.getServerId());
                    return (ServerNode)this.getRegistry().getNodes().toArray()[i+1];
                }
                else if(lastServerNode==serverNode && i==size-1){
                    lastServerNode = (ServerNode)this.getRegistry().getNodes().toArray()[0];
                    System.out.println("Assigning server: " + lastServerNode.getServerId());
                    return (ServerNode)this.getRegistry().getNodes().toArray()[0];
                }
            }
        }
        return null;
    }
}
