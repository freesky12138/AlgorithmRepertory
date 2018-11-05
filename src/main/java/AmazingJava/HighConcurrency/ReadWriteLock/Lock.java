package AmazingJava.HighConcurrency.ReadWriteLock;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 11:25
 */
public interface Lock {
    void lock() throws InterruptedException;

    void unlock();
}
