import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
public class Consumer implements Runnable{

    private ArrayList<Data> listOfData;

    public Consumer(ArrayList<Data> listOfData)
    {

        this.listOfData = listOfData;
    }

    @Override
    public void run() {
        while(true)
        {
            for (int j = 0; j < 3; j++)
            {
                synchronized (this.listOfData.get(j))
                {
                        //while the queue is empty, the consumer waits
                        while (this.listOfData.get(j).getMessages().size() == 0)
                        {
                            try {
                                System.out.println("the queue " + j + " is empty : consumer " + Thread.currentThread().getName() + " is waiting");
                                this.listOfData.get(j).wait();
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
                        System.out.println("consumer " + Thread.currentThread().getName() + " reads the " + this.listOfData.get(j).getMessages().poll() + " from the queue " + j);
                        this.listOfData.get(j).notifyAll();
                    }

                }
        }
    }

}