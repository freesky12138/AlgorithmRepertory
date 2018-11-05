package AmazingJava.HighConcurrency.ThreadSafeAndDataSyn;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadSafeAndDataSyn
 * @description 4-3-2死锁的产生
 * 造成死锁，write和read拿着对方需要的锁相互等待
 * <p>
 * 程序死锁种类：
 * 1本代码中的交叉锁，互相拿着对方的锁等待
 * 2内存不足，一直等待
 * 3数据库锁，执行update语句退出了事物
 * 4文件锁，获取了文件锁后意外退出
 * 5死循环导致的死锁
 * @date 2018/10/11 14:41
 */
public class DiedMutex {

    private static final Object Write = new Object();
    private static final Object Read = new Object();

    private void write() {
        synchronized (Write) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Read) {
                System.out.println("write");
            }
        }
    }

    private void read() {
        synchronized (Read) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (Write) {
                System.out.println("read");
            }
        }
    }

    public static void main(String[] args) {
        DiedMutex read = new DiedMutex();
        DiedMutex write = new DiedMutex();
        new Thread(read::read).start();
        new Thread(write::write).start();
    }


}
