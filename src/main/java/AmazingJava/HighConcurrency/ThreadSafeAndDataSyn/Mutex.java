package AmazingJava.HighConcurrency.ThreadSafeAndDataSyn;

import com.sun.prism.MaskTextureGraphics;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadSafeAndDataSyn
 * @description 4-3-1
 * 使用各种工具或者命令可以对线程状态进行监控
 * 1.可以使用jdk自带的jconsole.exe查看线程阻塞，运行状态
 * 2.在编译器的命令行使用jstack可以查看线程堆栈使用情况
 * 3.在命令行使用 javap -c AmazingJava.HighConcurrency.ThreadSafeAndDataSyn.Mutex 可以查看类编译后的汇编代码
 * <p>
 * 本代码中使用MUTEX作为 monitor锁
 * 每一个类都有对应的monitor锁，
 * synchronized (MUTEX)
 * 后即获取了monitor锁，只有一个线程可以获取类的monitor锁
 * @date 2018/10/11 11:41
 */
public class Mutex {
    //synchronized 作用域不能太大，否则会影响代码的效率
    //所有线程必须串行的通用synchronized ，作用域太大影响其他线程
    private final static Object MUTEX = new Object();


    public void accessResource() {
        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        IntStream.range(1, 10).mapToObj(i -> new Thread(mutex::accessResource)).forEach(Thread::start);
    }
}
