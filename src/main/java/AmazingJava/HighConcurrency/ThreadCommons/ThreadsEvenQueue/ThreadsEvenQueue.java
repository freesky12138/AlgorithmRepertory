package AmazingJava.HighConcurrency.ThreadCommons.ThreadsEvenQueue;

import java.util.LinkedList;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons
 * @description
 * @date 2018/10/11 16:07
 */
public class ThreadsEvenQueue {
    public static class Even {

    }

    private final LinkedList<Even> evenQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVEN = 10;

    private int max;

    public ThreadsEvenQueue() {
        this(DEFAULT_MAX_EVEN);
    }

    public ThreadsEvenQueue(int max) {
        this.max = max;
    }

    public Even take() {
        synchronized (evenQueue) {
            while (evenQueue.isEmpty()) {
                try {
                    console("queue empty");
                    evenQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            Even even = evenQueue.removeFirst();
            console("queue remove");
            evenQueue.notifyAll();
            return even;
        }
    }

    //添加时间
    public void offert(Even even) {
        synchronized (evenQueue) {

            //为什么要加这个while
            //因为是notifyAll，所以会唤醒所有等待的线程，被唤醒后，开始争抢 锁， 虽然有先后顺序，但是用if都会往下执行，导致evenQueue.size() >= max实际没有受到限制
            //如果使用notify，只会唤醒其中一个线程，这样就导致每次唤醒一个固定线程
            while (evenQueue.size() >= max) {
                try {
                    console("queue full");

                    evenQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            console("even submit");
            evenQueue.addLast(even);
            evenQueue.notifyAll();
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s                ====%d\n", Thread.currentThread().getName(), message, evenQueue.size());
    }


}
