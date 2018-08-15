package AmazingJava.HighConcurrency.HelloThread;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HelloThread
 * @description
 * 1-1 尝试并行运行
   线程的生命周期分五个部分
    NEW       创建一个thread对象，在没有start之前，和普通对象没有区别
    RUNNABLE  调用start后线程被创建，但还未被执行，在等待cpu的调度。在runnable状态下，
    RUNNING
    BLOCKED
    TERMINATED
 * @date 2018/8/14 11:19
 */
public class TryConcurrency {

    private static int value=0;

    public static void main(String[] args){
        new Thread(TryConcurrency::browseNews).start();
        new Thread(TryConcurrency::enjoyMusic).start();
    }

    private static void browseNews() {
        for(int i=0;i<1000;i++){
            value++;
            System.out.printf("browseNewsbrowseNews%d\n",value);
            sleep(1);
        }
    }

    private static void enjoyMusic() {
        for(int i=0;i<1000;i++){
            value--;
            System.out.printf("enjoyMusicenjoyMusic%d\n",value);
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
