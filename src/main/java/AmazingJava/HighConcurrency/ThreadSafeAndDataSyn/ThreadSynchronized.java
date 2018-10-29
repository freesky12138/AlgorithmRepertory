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
public class ThreadSynchronized extends Thread {
    private final String name;
    private static final int MAX = 1000;

    private  static int index = 1;//这里因为new 了 4个线程，如果不加static，那么每个线程里面有各自的index，所以必须加static
    //可以将ThreadSynchronized 和ThreadSynchronized2对比

    private final static Object MUTEX=new Object();

    public ThreadSynchronized(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX) {
                System.out.println("柜台："+name+",当前号："+index++);
            }
        }
    }

    public static void main(String args[]){
        ThreadSynchronized threadSynchronized1=new ThreadSynchronized("一号");
        threadSynchronized1.start();
        ThreadSynchronized threadSynchronized2=new ThreadSynchronized("二号");
        threadSynchronized2.start();
        ThreadSynchronized threadSynchronized3=new ThreadSynchronized("三号");
        threadSynchronized3.start();
        ThreadSynchronized threadSynchronized4=new ThreadSynchronized("四号");
        threadSynchronized4.start();
    }
}