package AmazingJava.HighConcurrency.ThreadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadPool
 * @description
 * @date 2018/10/17 14:38
 */
public class BasicThreadPool extends Thread implements ThreadPool {

    private final int initSize;
    private final int maxSize;
    private final int coreSize;
    private int activeCount;
    private final ThreadFactory threadFactory;
    //任务队列
    private final RunnableQueue runnableQueue;
    private volatile boolean isShutdown = false;
    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();
    private final static ThreadFactory DEFAULT_THREAD_FACTOR = new DefaultThreadFactor();
    //线程池多久检查一次更新线程数量
    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize, maxSize, coreSize, DEFAULT_THREAD_FACTOR, queueSize
                , DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory,
                           int queueSize, DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        IntStream.range(0, initSize).forEach((i) -> {
            newThread();
        });
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.creatThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }


    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {

            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }


            synchronized (this) {
                if (isShutdown)
                    break;

                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    IntStream.range(initSize, coreSize).forEach((i) -> {
                        newThread();
                    });
                    continue;
                }
                if (runnableQueue.size() > 0 && activeCount < maxSize) {
                    IntStream.range(coreSize, maxSize).forEach((i) -> {
                        newThread();
                    });
                }
                if (runnableQueue.size() == 0 && activeCount
                        > coreSize) {
                    IntStream.range(
                            coreSize
                            , activeCount
                    ).forEach((i) -> {
                        removeThread();
                    });
                }

            }
        }
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown)
                return;

            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.isInterrupted();
            });
            this.interrupt();
        }
    }

    @Override
    public boolean isShutDown() {
        return isShutdown;
    }

    @Override
    public int getInitSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        return initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        return maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        return coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        return runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (isShutdown)
            throw new IllegalStateException("The thread pool is destory");
        return activeCount;
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    public static class DefaultThreadFactor implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("Group-ThreadPool-" + GROUP_COUNTER.getAndIncrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread creatThread(Runnable runnable) {
                return new Thread(group,runnable,"ThreadPool-"+COUNTER.getAndIncrement());
        }
    }
}
