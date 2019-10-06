package AmazingJava.HighConcurrency.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Volatile
 * @description
 * @date 2018/10/24 15:22
 * 1：原子性 同时只有一个线程可以对其进行操作
 * 2：可见性 volatile可以保持变量的可见性
 * 3：有序性 代码所执行的先后顺序
 *
 *
 * Jvm的原子性操作：
 * 1多个原子性操作在一起就不再是原子性操作。
 * 2如果需要对某些代码段具备原子性操作，使用synchronized，或者用lock。如果对变量进行原子性操作,使用AtomicInteger
 * 3volatile关键字不具有原子性
 *
 * Jvm的可见性操作：
 * 1：使用volatile关键字，使用volatile关键字会将其放入cpu内存中，让各个线程共享
 * 2：使用synchronized关键字
 * 3：使用锁
 *
 * Jvm的有序性：
 * 1：使用volatile关键字，使用volatile关键字会将其放入cpu内存中，让各个线程共享
 * 2：使用synchronized关键字
 * 3：使用锁
 * Jva的内存弄些具备一些天生的有序性规则，这些规则成为Happens-before
 * 1：在一个线程内，是有序执行的。
 * 2：同一个锁，一个unlock操作要先于一个lock操作
 * 3：对于volatile变量，对他的写操作会早于读操作
 *
 * volatile和synchronized的区别
 * 1：volatile用来修饰实例或者类变量，不能修饰方法，synchronized用来修饰方法或语句块
 * 2：volatile修饰的变量可以为null，synchronized的monitor锁不能为null
 * 3：volatile不会使线程进入阻塞，synchronized会
 */
public class Volatile {
    final static int MAX = 100;
    static volatile int initValue = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = initValue;
            while (localValue < MAX) {
                if (localValue != initValue) {
                    System.out.println("update" + localValue);
                    localValue = initValue;
                }
            }
        }).start();

        new Thread(() -> {
            int localValue=initValue;
            while (localValue < MAX) {
                System.out.println("change" + initValue++);
                localValue=initValue;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
