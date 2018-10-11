package AmazingJava.HighConcurrency.ThreadCommons.EvenQueue;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadCommons.EvenQueue
 * @description 5-1 wait 和 notify
 * wait会让线程阻塞，直到被notify
 * wait和notify是object中的方法，每个类都有
 * @date 2018/10/11 16:09
 */

/**
 *
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
