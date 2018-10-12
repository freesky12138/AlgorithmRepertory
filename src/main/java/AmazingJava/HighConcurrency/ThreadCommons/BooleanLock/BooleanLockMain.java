package AmazingJava.HighConcurrency.ThreadCommons.BooleanLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.BooleanLock
 * @description
 * @date 2018/10/12 14:23
 */
public class BooleanLockMain {

    private Lock lock = new BooleanLock();

    public void synMothod() {
        try {
            lock.lock(20000);//这个时间是从线程开始计算等待时间，所有线程时间都是20秒，等待20秒之后，会陆续抛出异常
            int sleepTime = new Random().nextInt(10);
            System.out.println(currentThread().getName() + " get lock " + sleepTime);
            TimeUnit.SECONDS.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BooleanLockMain booleanLock = new BooleanLockMain();
        IntStream.range(1, 10).mapToObj(i -> new Thread(
                booleanLock::synMothod
        )).forEach(Thread::start);
    }

}
