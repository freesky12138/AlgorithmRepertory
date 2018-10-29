package AmazingJava.HighConcurrency.ThreadApi;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi
 * @description 3.5Join 和 Sleep一样是一个可中断的方法
 * 在当前线程A中执行B的Join方法，A会等待B线程结束后方能继续进行，A进入一个阻塞的状态。
 * @date 2018/9/18 16:39
 */
public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads= IntStream.range(1,3).mapToObj(ThreadJoin::create).collect(Collectors.toList());

        threads.forEach(Thread::start);

        //在main中执行 join,main会等待 threads中的线程执行完才开始执行
        //在当前线程执行其他线程join，会等待其他线程执行完后才开始执行当前线程
        //也可传入毫秒，会在对方周期结束后或者毫秒结束后结束阻塞
        for (Thread thread : threads) {
            thread.join();
        }

        IntStream.range(1,10).forEach(i->{
            System.out.println(Thread.currentThread().getName()+"#"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static Thread create(int seq){
        return new Thread(()->{
           for(int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"#"+i);
               try {
                   TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        },String.valueOf(seq));
    }
}
