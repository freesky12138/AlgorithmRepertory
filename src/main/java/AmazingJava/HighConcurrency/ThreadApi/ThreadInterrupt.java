package AmazingJava.HighConcurrency.ThreadApi;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi
 * @description 3-3 线程的interrupt  中断阻塞
    如果线程进入阻塞状态  类似sleep，wait等，可以使用interrupt打破这种阻塞状态
    线程的阻塞如果被打破就会配出InterruptedException异常
 * @date 2018/9/18 10:49
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                }
                while (true){

                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.isInterrupted()); //false 无中断
        thread.interrupt();
        System.out.println(thread.isInterrupted()); //true or false  中断后看选输出这里还是中断信息先被复位
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.isInterrupted());  //false 中断过后，thread中的sleep会把中断信息复位，中断只用一次，防止后面的阻塞被中断
        thread.interrupt();
        System.out.println(thread.isInterrupted());  //true   中断信息写入后没有被复位
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(thread.isInterrupted());  //true   中断信息写入后没有被复位
        TimeUnit.MILLISECONDS.sleep(100);

        System.out.println("----------------------------------------------");

        //interrupted 是Thread的静态方法
        //其作用是获取当前中断状态，并且把中断状态复位到false
        Thread interrupedTrread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println(Thread.interrupted());
                }
            }
        });
        interrupedTrread.setDaemon(true);
        interrupedTrread.start();

        TimeUnit.MILLISECONDS.sleep(2);
        interrupedTrread.interrupt();

    }
}
