package AmazingJava.HighConcurrency.ThreadApi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi
 * @description 3-1 Thread的api
 * @date 2018/9/18 9:35
 */
public class BaseThreadApi {
    //yield会主动放弃cpu的使用，如果cpu在空闲则不一定会放弃
    //sleep是让当前线程进入休眠状态
    //sleep是百分比能block，在给定时间释放cpu资源，而yield则不一定能放弃cpu资源

    /**
     TimeUnit.DAYS          //天
     TimeUnit.HOURS         //小时
     TimeUnit.MINUTES       //分钟
     TimeUnit.SECONDS       //秒
     TimeUnit.MILLISECONDS  //毫秒
     TimeUnit.NANOSECONDS   //毫微秒
     TimeUnit.MICROSECONDS  /``
     */
    final static AtomicInteger atomicInteger = new AtomicInteger(0);



    public static void main(String[] args) {

        Thread yieldThread = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("yield befor");
                Thread.yield();//放弃过后拿回cpu的执行权才会执行下面一句
                System.out.println("yield after");


            }
        });

        Thread sleepThread = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(0, 100000).forEach(i -> {

                        System.out.println(atomicInteger.getAndIncrement());

                });
            }
        });


        sleepThread.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        yieldThread.start();
    }
}
