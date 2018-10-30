package AmazingJava.HighConcurrency.Observable;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Observable
 * @description
 * @date 2018/10/30 17:27
 */
public interface TaskLifecycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread,T result);

    void onError(Thread thread,Exception e);

    class EmptyLifecycle<T> implements TaskLifecycle<T>{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
