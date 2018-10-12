package AmazingJava.HighConcurrency.ThreadCommons.EvenQueue;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.EvenQueue
 * @description 5-1 wait 和 notify
 * wait会让线程阻塞，直到该object的notify或notifyAll被调用后阻塞才消除,或者使用wait(100),阻塞超时
 * 在调用wait时必须拥有wait的monitor锁
 *
 * notify 是唤醒正在执行wait方法的线程，被唤醒的线程需要回去对象的monitro锁才能继续进行
 * wait和notify是object中的方法，每个类都有这两个方法
 *
 * wait 是sleep的方法，sleep是Thread的方法，均可被中断
 * sleep不会释放monitor锁，wait会释放monitor锁
 * @date 2018/10/11 16:09
 */

/**
 *下面类中建立EvenQueue ，可以向其中添加或者去除Even，当添加满后会等待被取出，单取出为空后会等待添加
 */
public class EvenMain {
    public static void main(String[] args){
        EvenQueue evenQueue=new EvenQueue();

        new Thread(()->{
            while(true){

                evenQueue.offert(new EvenQueue.Even());
            }
        },"Producer").start();

        new Thread(()->{
            while(true){
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                evenQueue.take();
            }
        },"Custom").start();
    }
}
