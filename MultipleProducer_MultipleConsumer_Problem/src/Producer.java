import java.util.ArrayList;

public class Producer implements Runnable{
    private ArrayList<Data> listOfData;

    public Producer(ArrayList<Data> listOfData)
    {

        this.listOfData = listOfData;
    }

    @Override
    public void run() {
        int i = 0;
        while(true)
        {
            for(int j = 0; j < 3; j++)
            {
                synchronized (this.listOfData.get(j))
                {
                        // while the queue is full, the producer waits
                        while(this.listOfData.get(j).getMessages().size() == this.listOfData.get(j).getMaxSize())
                        {
                            try {
                                System.out.println("the queue " + j +  " is full : producer " + Thread.currentThread().getName() + " is waiting");
                                this.listOfData.get(j).wait();
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
                        System.out.println("producer " + Thread.currentThread().getName() + " writes the message " + i + " in the queue " + j);
                        this.listOfData.get(j).getMessages().add("message " + i);
                        this.listOfData.get(j).notifyAll();
                    }
                }
        }
    }

}
