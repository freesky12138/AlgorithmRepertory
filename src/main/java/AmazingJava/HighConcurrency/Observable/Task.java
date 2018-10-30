package AmazingJava.HighConcurrency.Observable;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Observable
 * @description
 * @date 2018/10/30 17:31
 */
@FunctionalInterface
public interface Task<T> {
    T call();
}
