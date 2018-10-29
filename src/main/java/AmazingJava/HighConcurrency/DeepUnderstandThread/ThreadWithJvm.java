package AmazingJava.HighConcurrency.DeepUnderstandThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description
 *
 * 2.4
 jvm可以创建多少个线程
staticsize和计算机硬件还有设置有关
当staticsize设置越大，可以递归的层数越深，可以创建的线程数量越少
当staticsize设置越小，可以递归的层数越浅，可以创建的线程数量越多
可以创建多少线程与堆内存，栈内存有关系


java -Xmx256m -Xms64m ThreadWithJvm
来设置大小 配置不同的栈内存大小，此类就是测试系统可以创建多少个线程
 * @date 2018/9/17 17:01
 */
public class ThreadWithJvm extends Thread {
    //支持原子操作的基本类型,getAndIncrement每获取一次，自动加一
    final static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        while (true) {
            try {
                new ThreadWithJvm().start();
            }catch (Throwable e){
                System.out.println("error with thread "+counter.get());
            }

        }
    }

    @Override
    public void run() {
        try {
            System.out.println(counter.getAndIncrement());
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
