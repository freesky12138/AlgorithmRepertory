package AmazingJava.HighConcurrency.ThreadApi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi
 * @description
 * @date 2018/9/18 10:31
 */
public class ThreadPriority {

    final static AtomicInteger atomicT1 = new AtomicInteger(0);
    final static AtomicInteger atomicT2 = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            while (true) {
                atomicT1.getAndIncrement();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                atomicT2.getAndIncrement();
            }
        });

        t1.setDaemon(true);
        t2.setDaemon(true);
        //线程共1-10级，main中默认是5级
        t1.setPriority(1);
        System.out.println(t2.getPriority());
        t2.setPriority(10);
        //通过查看setPriority源码，如果当前设置的线程优先级大于group的最大优先级，那么设置的这个优先级就会是当前group的最大优先级
        System.out.println(t2.getPriority());
        System.out.println(t2.getThreadGroup().getMaxPriority());

        t1.start();
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //pass:当执行时间长的时候t1和t2比较接近
        //当执行时间断的时候t2明显大于t1
        System.out.println(atomicT1.get());
        System.out.println(atomicT2.get());
    }
}
