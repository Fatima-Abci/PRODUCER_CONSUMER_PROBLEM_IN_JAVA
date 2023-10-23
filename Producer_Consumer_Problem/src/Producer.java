public class Producer implements Runnable{

    private Data data;

    public Producer(Data data)
    {
        this.data = data;
    }

    @Override
    public void run() {
        int i = 0;
        while(true)
        {
            synchronized (this.data)
            {
                // while the queue is full, the producer waits
                while(this.data.getMessages().size() == this.data.getMaxSize())
                {
                    try {
                        System.out.println("the queue is full : producer is waiting");
                        this.data.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // the queue is no longer full, so the producer can produce an item
                i++;
                try {
                    Thread.sleep(1000); // it takes 1 second for a producer to produce an item
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("producer writes the message " + i);
                this.data.getMessages().add("message " + i);
                this.data.notify();
            }
        }
    }

}
