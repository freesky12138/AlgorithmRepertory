package AmazingJava.HighConcurrency.HookThreadTry;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HookThreadTry
 * @description
 * 当发生UncaughtException,如果有父ThreadGroup就直接调用父Group的UncaughtException
 * 如果设置全局的UncaughtException，就调用全局的
 * 如果都没有，就将异常定向到System.err
 * @date 2018/10/16 9:37
 */
public class CaptureThreadException {
    public static void main(String[] args){
        //设置发生异常的回调
        Thread.setDefaultUncaughtExceptionHandler(((t, e) -> {
            System.out.println(t.getName());
            e.printStackTrace();
        }));

        final Thread thread=new Thread(()->{
           try {
               TimeUnit.SECONDS.sleep(2);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           //抛出异常
            System.out.println(1/0);
        },"exceptionThread");

        thread.start();
    }
}
