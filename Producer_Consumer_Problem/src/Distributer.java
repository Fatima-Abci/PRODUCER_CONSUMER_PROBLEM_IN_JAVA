import java.util.LinkedList;
import java.util.Queue;

public class Distributer {
    public static void main(String[] args) throws InterruptedException {

        Queue<String> messages = new LinkedList<String>();
        int maxSize = 4;
        Data data = new Data(messages, maxSize);

        Producer runnableP = new Producer(data);
        Thread T1 = new Thread(runnableP);
        Consumer runnableC = new Consumer(data);
        Thread T2 = new Thread(runnableC);

        T1.start();
        T2.start();

        T1.join();
        T2.join();
    }
}