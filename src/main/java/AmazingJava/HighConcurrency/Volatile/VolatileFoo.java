package AmazingJava.HighConcurrency.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Volatile
 * @description
 * Reader线程会将init_value从主内存缓存到CPU Cache中，从主内存缓存到线程所在内存，Updater操作的是主内存的initValue，对Reader是不可见的
 * 此段代码主要是因为initValue对Reader不具有可见性
 *
 * @date 2018/10/24 15:22
 */
public class VolatileFoo {
    final static int MAX = 5;
    static  int initValue = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = initValue;
            while (localValue < MAX) {
                if (localValue != initValue) {

                    System.out.println("update" + localValue);
                    localValue = initValue;
                }
            }
        },"Reader").start();

        new Thread(() -> {
            int localValue=initValue;
            while (localValue < MAX) {
                System.out.println("change" + initValue++);
                localValue=initValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        },"Update").start();
    }
}
