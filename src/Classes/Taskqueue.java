package Classes;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Taskqueue {

    BlockingQueue<Task> queue;

    public Taskqueue() {
        queue = new LinkedBlockingQueue<>();
    }

    public void add(Task task) throws InterruptedException {
        queue.add(task); 
        if(!queue.isEmpty())
        System.out.println("task added to queue " + task.toString());
    }

    public  Task take() throws InterruptedException {

        return queue.take();
    }

}
