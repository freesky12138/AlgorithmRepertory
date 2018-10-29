package AmazingJava.HighConcurrency.ThreadGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadGroup
 * @description 6-3 ThreadGroup的创建和  线程组下Thread的复制
 * 在线程A中创建线程组B，线程的A的线程组就是线程组B的父线程组
 * 在创建线程组时可以指定父线程组
 * <p>
 * enumerate 只是预估值，调用复制时，如果有线程进出，会导致不正确
 *
 * interrupt 一个线程组会导致整个线程组的线程都被中断
 *
 * distory() 可以销毁一个线程组，不过前提是这个线程组里面没有活跃的线程
 *
 * 可以设置一个ThreadGroup为守护进程组，设置之后不会影响其中的Thread，但是当其中没有活跃的Thread后，这个线程组会被distory()
 * @date 2018/10/12 15:29
 */
public class ThreadGroupCopy {

    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("MyGroup");
        //创建threadGroup的线程
        new Thread(threadGroup, () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //创建mainGroup的线程
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        int size = threadGroup.enumerate(threads);
        System.out.println(size);

        //当前 main线程组的线程数量
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads2 = new Thread[mainThreadGroup.activeCount()];
        size = mainThreadGroup.enumerate(threads2, false);
        System.out.println(size);

        //当前 main线程组的线程数量和子线程组的数量，带递归
        size = mainThreadGroup.enumerate(threads2, true);
        System.out.println(size);

        //复制 main线程组并获取其数量
        ThreadGroup[] groupList = new ThreadGroup[mainThreadGroup.activeGroupCount()];
        size = mainThreadGroup.enumerate(groupList);
        System.out.println(size);
    }
}
