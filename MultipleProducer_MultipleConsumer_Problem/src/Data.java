import java.util.LinkedList;
import java.util.Queue;

public class Data {

    private Queue<String> messages = new LinkedList<String>();
    private int maxSize;

    public Data(Queue<String> messages, int maxSize)
    {
        this.messages = messages;
        this.maxSize = maxSize;
    }

    public Queue<String> getMessages() {
        return this.messages;
    }

    public void setMessages(Queue<String> messages) {
        this.messages = messages;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
