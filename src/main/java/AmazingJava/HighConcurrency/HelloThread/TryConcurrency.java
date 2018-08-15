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
    RUNNABLE  调用start后线程被创建，但还未被执行，在等待cpu的调度。在runnable状态下，线程是不能直接终止，除非线程被杀，必须进入running后再能终止。

    RUNNING   cpu通过轮询的方式或者其他方式选中了线程，开始执行线程中的逻辑
                RUNNING可以直接进入TERMINATED状态，比如使用了stop方法
                RUNNING调用了sleep，wait方法，或者某个阻塞的IO操作，获取了某个锁资源，会进入Blocked状态
                RUNNING主动调用yield，放弃cpu执行权，或者cpu轮询是该进程放弃执行权，会进入RUNNABLE状态
    BLOCKED    线程被锁，进去等待状态，BLOCKE的线程阻塞结束或者休眠结束或者被唤醒，或者获取了某个锁资源，进入runnable状态，不直接进入running状态

    TERMINATED 线程的最终状态，生命周期结束，不会切换到任何其他状态，在线程正常结束，意外结束，或者JVM Crash，就会导致线程结束
 * @date 2018/8/14 11:19
 */
public class TryConcurrency {

    private static int value=0;

    public static void main(String[] args){
        Thread thread =new Thread(TryConcurrency::browseNews);
        thread.start();
        //调用start后 private ThreadGroup group; 线程会被放入 ThreadGroup
        //run方法会在jni中被调用
        //一个线程不能两次执行start方法 ，再调一次start会报错
        //thread.start();
        System.out.printf("-------------------------------");
        new Thread(){
            @Override
            public void run() {
                enjoyMusic();
            }
        }.start();

        System.out.printf("-------------------------------");
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
