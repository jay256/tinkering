import com.rl.api.RateLimiter;
import com.rl.api.impl.HttpTokenRateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        RateLimiter rateLimiter = new HttpTokenRateLimiter(10,1000,5);

        Runnable task = () -> {
            if(rateLimiter.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + ": Request processed at: " + System.currentTimeMillis());
            }
            else{
                System.out.println(Thread.currentThread().getName() + ": Rate limit exceeded at: " + System.currentTimeMillis());
            }
        };

        for(int i = 0; i < 100; i++){
            new Thread(task).start();
            Thread.sleep(100);
        }

    }
}