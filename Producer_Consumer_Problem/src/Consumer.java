import java.util.LinkedList;
import java.util.Queue;

public class Consumer implements Runnable{

    private Data data;

    public Consumer(Data data)
    {
        this.data = data;
    }

    @Override
    public void run() {
        while(true)
        {
            synchronized (this.data)
            {
                //while the queue is empty, the consumer waits
                while (this.data.getMessages().size() == 0)
                {
                    try {
                        System.out.println("the queue is empty : consumer is waiting");
                        this.data.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                //the queue is no longer empty, so the consumer can consume an item
                try {
                    Thread.sleep(1000); // it takes 1 second for a consumer to consume an item
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("consumer reads the "+this.data.getMessages().poll());
                this.data.notify();
            }
        }
    }

}
