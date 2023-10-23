import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Distributer {
    public static void main(String[] args) throws InterruptedException {

        Queue<String> messages;
        int maxSize = 4;
        Data data ;

        ArrayList<Data> listOfData = new ArrayList<Data>();
        int N = 4;

        for(int i = 0; i < N; i++)
        {
            messages = new LinkedList<String>();
            data = new Data(messages, maxSize);
            listOfData.add(data);
        }



        ArrayList<Thread> listOfProducer = new ArrayList<Thread>();
        int y = 7;

        ArrayList<Thread> listOfConsumer = new ArrayList<Thread>();
        int x = 5;

        listOfProducer = createListOfProducer(y, listOfProducer, listOfData);
        listOfConsumer = createListOfConsumer(x, listOfConsumer, listOfData);

        startThreads(listOfProducer);
        startThreads(listOfConsumer);

        joinThreads(listOfProducer);
        joinThreads(listOfConsumer);
    }

    //function that creates a list of producers
    public static ArrayList<Thread> createListOfProducer(int nbr, ArrayList<Thread> listOfProducer, ArrayList<Data> listOfData)
    {
        Producer runnableP;
        for(int i = 0; i < nbr; i++)
        {
            runnableP = new Producer(listOfData);
            listOfProducer.add(new Thread(runnableP));
        }
        return listOfProducer;
    }

    //function that creates a list of consumers
    public static ArrayList<Thread> createListOfConsumer(int nbr, ArrayList<Thread> listOfConsumer, ArrayList<Data> listOfData)
    {
        Consumer runnableC;
        for(int i = 0; i < nbr; i++)
        {
            runnableC = new Consumer(listOfData);
            listOfConsumer.add(new Thread(runnableC));
        }
        return listOfConsumer;
    }

    //function that starts a list of threads
    public static void startThreads(ArrayList<Thread> listOfThreads)
    {
        for(Thread thread : listOfThreads)
        {
            thread.start();
        }
    }

    public static void joinThreads(ArrayList<Thread> listOfThreads) throws InterruptedException {
        for (Thread thread : listOfThreads)
        {
            thread.join();
        }
    }

}