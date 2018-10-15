package AmazingJava.HighConcurrency.ThreadCommons.EvenQueue;

import java.util.LinkedList;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons
 * @description
 * @date 2018/10/11 16:07
 */
public class EvenQueue {
    public static class Even {

    }

    private final LinkedList<Even> evenQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVEN = 10;

    private int max;

    public EvenQueue() {
        this(DEFAULT_MAX_EVEN);
    }

    public EvenQueue(int max) {
        this.max = max;
    }

    public Even take() {
        synchronized (evenQueue) {
            if (evenQueue.isEmpty()) {
                try {
                    console("queue empty");
                    evenQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Even even = evenQueue.removeFirst();
            console("queue remove");
            evenQueue.notify();
            return even;
        }
    }

    //添加事件
    public void offert(Even even) {
        synchronized (evenQueue) {
            if (evenQueue.size() >= max) {
                try {
                    console("queue full");

                    evenQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            console("even submit");
            evenQueue.addLast(even);
            evenQueue.notify();

        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
    }


}
