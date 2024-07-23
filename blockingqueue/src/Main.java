import com.bq.api.MyQueue;
import com.bq.impl.BlockingQueue;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new BlockingQueue<>(5);

        Runnable producer = () ->
            {
                try {
                    for (int i = 0; i < 100; i++) {
//                        System.out.println("Produced: " + i);
                        queue.enqueue(i);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            };

        Runnable consumer = () -> {
            try {
                while(true){
                    Thread.sleep(10);
                    queue.dequeue();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread prod = new Thread(producer);
        Thread cons = new Thread(consumer);

        prod.start();
        cons.start();

        try {
            prod.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        try {
            cons.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}