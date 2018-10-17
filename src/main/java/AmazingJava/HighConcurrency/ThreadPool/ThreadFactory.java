package AmazingJava.HighConcurrency.ThreadPool;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadPool
 * @description
 * @date 2018/10/17 11:46
 */
@FunctionalInterface
public interface ThreadFactory {
    Thread creatThread(Runnable runnable);
}
