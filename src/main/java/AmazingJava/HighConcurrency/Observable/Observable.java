package AmazingJava.HighConcurrency.Observable;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Observable
 * @description
 * @date 2018/10/30 17:25
 */
public interface Observable {
    enum Cycle{
        STARTED,RUNNABLE,DONE,ERROR
    }

    Cycle getCycle();

    //覆写start方法
    void start();

    //覆写interrupt方法
    void interrupt();
}
