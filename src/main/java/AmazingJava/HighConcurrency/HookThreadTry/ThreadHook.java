package AmazingJava.HighConcurrency.HookThreadTry;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HookThreadTry
 * @description  hookThread 本例子中添加两个勾子线程，会在mainThread结束后(JVM进程退出)启动这两个线程
 * @date 2018/10/16 9:58
 */
public class ThreadHook {

    public static void main(String[] args){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("thread 1 start");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 1 end");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("thread 2 start");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread 2 end");
            }
        });
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");

    }
}
