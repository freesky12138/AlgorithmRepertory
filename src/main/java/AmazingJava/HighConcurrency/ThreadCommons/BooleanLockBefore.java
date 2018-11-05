package AmazingJava.HighConcurrency.ThreadCommons;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.EvenQueue
 * @description 5-4-1 synchronized的缺陷，其他线程无法进入就无法打断
 * @date 2018/10/11 16:10
 */
public class BooleanLockBefore {

    /**
     * 如果加synchronized
     * thread2无法打断thread1的方法，因为无法获取锁
     * thread1自己可以打断
     * <p>
     * 如果不加synchronized
     * thread1和thread2都可以打断sleep
     */
    public synchronized void setMothod() {

        try {
            System.out.println(Thread.currentThread().getName());
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BooleanLockBefore booleanLockBefore = new BooleanLockBefore();

        System.out.printf("1");
        Thread thread1 = new Thread(booleanLockBefore::setMothod);
        thread1.start();
        System.out.printf("2");
        try {
            //为了让Thread0先执行
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("3");
        Thread thread2 = new Thread(booleanLockBefore::setMothod);
        thread2.start();
        thread2.interrupt();
        System.out.printf("4");
    }

}
