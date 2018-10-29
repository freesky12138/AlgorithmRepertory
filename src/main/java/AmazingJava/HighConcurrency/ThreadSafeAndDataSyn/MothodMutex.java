package AmazingJava.HighConcurrency.ThreadSafeAndDataSyn;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadSafeAndDataSyn
 * @description 4-4 类中方法的monitor锁
 * 在一个类中只有一个monitor锁，当mothod1取得的monitor锁后
 * mothod2就不能获取这个唯一的monitor锁，所以方法2会进入等待状态
 * 一个类中只有一个monitor锁
 *
 * 如果给mothod1和mothod2加static修饰，结果也相同
 * 说明就算给类中方法加静态，这个方法也属于此类，一个类中只有一个monitor锁
 * @date 2018/10/11 15:10
 */
public class MothodMutex {

    public synchronized void mothod1(){
        System.out.println("mothod1");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void mothod2(){
        System.out.println("mothod2");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        MothodMutex mothodMutex=new MothodMutex();
        new Thread(mothodMutex::mothod1).start();
        new Thread(mothodMutex::mothod2).start();
    }
}
