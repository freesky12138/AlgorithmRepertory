package AmazingJava.HighConcurrency.ThreadCommons.BooleanLock;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.BooleanLock
 * @description
 * @date 2018/10/12 11:21
 */
public interface Lock {
    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();
}
