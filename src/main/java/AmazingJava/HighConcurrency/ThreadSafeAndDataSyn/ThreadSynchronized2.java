package AmazingJava.HighConcurrency.ThreadSafeAndDataSyn;


/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadSafeAndDataSyn
 * @description 4-1 Synchronized
Synchronized可以对代码块或者方法进行修饰
Synchronized提供了一种互斥机制，同一时刻，只能有一个线程访问同步资源
 * @date 2018/9/19 13:48
 */
public class ThreadSynchronized2 implements Runnable {
    private static final int MAX = 1000;

    private   int index = 1;//和ThreadSynchronized对比，ThreadSynchronized2只有一个类，用的同一个runnable,没有创建多个Thread

    private final static Object MUTEX=new Object();



    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX) {
                System.out.println("柜台："+Thread.currentThread().getName()+",当前号："+index++);
            }
        }
    }

    public static void main(String args[]){
        ThreadSynchronized2 runnable=new ThreadSynchronized2() ;
        Thread threadSynchronized1=new Thread(runnable,"一号");
        threadSynchronized1.start();
        Thread threadSynchronized2=new Thread(runnable,"二号");
        threadSynchronized2.start();
        Thread threadSynchronized3=new Thread(runnable,"三号");
        threadSynchronized3.start();
        Thread threadSynchronized4=new Thread(runnable,"四号");
        threadSynchronized4.start();
    }
}