import com.lb.LoadBalancer;
import com.lb.impl.RoundRobinLoadBalancer;
import com.lb.models.Request;
import com.lb.models.ServerNode;
import com.lb.registry.ServerRegistry;

/**
 1. request comes in
 2. load balancer has info of all registered nodes
 3. applies logic based on health and chooses node to send request to



 */

public class Main {
    public static void main(String[] args) {
        ServerNode [] serverNodes = new ServerNode[10];
        Request []  requests = new Request[100];
        for(int i=0;i<10;i++){
            serverNodes[i] = new ServerNode();
            serverNodes[i].setServerId(i);
        }
        for(int i=0;i<100;i++){
            requests[i] = new Request();
            requests[i].setRequestId(i);
        }
        ServerRegistry registry = new ServerRegistry();
        for(int i=0;i<10;i++){
            registry.register(serverNodes[i]);
        }
        LoadBalancer loadBalancer = new RoundRobinLoadBalancer();
        loadBalancer.setRegistry(registry);
        for(int i=0;i<100;i++){
            int finalI = i;
            Thread t = new Thread(() -> loadBalancer.handleRequest(requests[finalI]));
            t.start();
        }
    }
}

