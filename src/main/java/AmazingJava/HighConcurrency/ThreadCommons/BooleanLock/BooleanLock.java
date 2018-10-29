package AmazingJava.HighConcurrency.ThreadCommons.BooleanLock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.BooleanLock
 * @description 5.4 显示锁 BooleanLock  具备是synchronized功能，并且有超时功能
 * @date 2018/10/12 11:24
 */
public class BooleanLock implements Lock {

    private Thread currentThread;//存当前正在使用的Thread
    private boolean locked = false;//false代表该锁没有任何线程，true代表锁已经被某个线程获得，该线程就是currentThread
    private final List<Thread> blockedList = new ArrayList<>();//阻塞队列，被阻塞的Thread list


    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {//如果当前锁被其他线程占用，就将当前线程放入阻塞队列
                if (!blockedList.contains(currentThread())) {
                    blockedList.add(currentThread());
                }
                try {
                    this.wait();
                }catch (InterruptedException e){
                    //如果被中断就从列表中删除
                    blockedList.remove(currentThread());
                    throw e;
                }

            }
            //当能执行到这里的时候，说明阻塞被消除，当前线程从阻塞队列消除，locked=true，并且this.currentThread=currentThread();
            blockedList.remove(currentThread());
            this.locked = true;
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills <= 0) {
                this.lock();
            } else {

                //使用remainingMills，超时时间，如果 remainingMills
                long remainingMills = mills;

                long endMills = currentTimeMillis() + remainingMills;//结束时间

                while (locked) {
                    //如果remainingMills小于0 抛异常
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get lock");
                    }
                    //如果blockedList不包括currentThread就
                    if (!blockedList.contains(currentThread())) {
                        blockedList.add(currentThread());
                    }
                    try {
                        this.wait(remainingMills);
                    }catch (InterruptedException e){
                        //如果被中断就从列表中删除
                        blockedList.remove(currentThread());
                        throw e;
                    }

                    remainingMills = endMills - currentTimeMillis();
                }
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }

        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread()) {
                this.locked = false;

                //如果currentThread不为null就输出
                Optional.of(currentThread.getName() + " release the lock").ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
