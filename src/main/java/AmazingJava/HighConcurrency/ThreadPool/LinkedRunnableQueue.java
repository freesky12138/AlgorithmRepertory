package AmazingJava.HighConcurrency.ThreadPool;

import java.util.LinkedList;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadPool
 * @description
 * @date 2018/10/17 14:31
 */
public class LinkedRunnableQueue implements RunnableQueue {

    private final int limit;
    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableLinkedList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableLinkedList) {
            if (runnableLinkedList.size() >= limit){
                denyPolicy.reject(runnable,threadPool);
            }else {
                runnableLinkedList.addLast(runnable);
                runnableLinkedList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableLinkedList){
            while (runnableLinkedList.isEmpty()){
                try {
                    runnableLinkedList.wait();
                }catch (InterruptedException e){
                    throw e;
                }
            }
            return runnableLinkedList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableLinkedList){
            return runnableLinkedList.size();
        }
    }
}
