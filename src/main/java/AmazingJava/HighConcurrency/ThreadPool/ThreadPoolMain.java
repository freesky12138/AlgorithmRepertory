package AmazingJava.HighConcurrency.ThreadPool;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadPool
 * @description
 * @date 2018/10/17 15:18
 */
public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 10, 4, 1000);


        new Thread(()->{
            while (true) {
                System.out.println("ActivityCount" + threadPool.getActiveCount());
                System.out.println("QueueSize" + threadPool.getQueueSize());
                System.out.println("CoreSize" + threadPool.getCoreSize());
                System.out.println("MaxSize" + threadPool.getMaxSize());
                System.out.println("============================================" );
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {

                }
            }
        }).start();

        for (int i = 0; i < 200; i++) {
            threadPool.execute(
                    () -> {
                        try {
                            TimeUnit.SECONDS.sleep(new Random().nextInt(10));
                            System.out.println(Thread.currentThread().getName() + " done.");

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );

        }

    }
}
