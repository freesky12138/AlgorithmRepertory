package AmazingJava.HighConcurrency.ThreadPool;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadPool
 * @description 线程池中初始大小为init ，当前大小为core，上限为max
 * @date 2018/10/17 11:35
 */
public interface ThreadPool {
    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutdown();

    boolean isShutDown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    //任务队列数量
    int getQueueSize();

    //活跃线程数
    int getActiveCount();
}
