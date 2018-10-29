package AmazingJava.HighConcurrency.ThreadCommons.ThreadsEvenQueue;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.EvenQueue
 * @description 5-2 wait 和 notifyall
 * 多个线程存或者取数据
 *
 * notifi只能唤醒其中一个线程，而notifiAll会唤醒全部的线程，这些被唤醒的线程会争抢 threadsEvenQueue的monitor锁
 * @date 2018/10/11 16:09
 */

/**
 * 下面类中建立EvenQueue ，可以向其中添加或者去除Even，当添加满后会等待被取出，单取出为空后会等待添加
 * 在本例子中notifyall和notify是一样的
 */
public class ThreadsEvenMain {
    public static void main(String[] args) {
        ThreadsEvenQueue threadsEvenQueue = new ThreadsEvenQueue();

        IntStream.range(1, 5).mapToObj(i -> {
            return new Thread(() -> {
                while (true) {

                    threadsEvenQueue.offert(new ThreadsEvenQueue.Even());
                }
            }, "Producer" + i);

        }).forEach(Thread::start);

        IntStream.range(1, 5).mapToObj(i -> new Thread(() -> {
            while (true) {
                /*try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                threadsEvenQueue.take();
            }
        }, "Custom" + i)).forEach(Thread::start);


    }
}
