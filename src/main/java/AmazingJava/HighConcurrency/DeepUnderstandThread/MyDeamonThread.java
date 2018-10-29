package AmazingJava.HighConcurrency.DeepUnderstandThread;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description 2.6守护线程 会跟随程序终止而结束的线程
当所有的非守护线程结束时，程序也就终止了，同时会杀死进程中的所有守护线程
如果是非守护线程，程序结束，非守护线程还在

守护线程经常执行一些后台任务，会随着程序的结束而结束
 * @date 2018/9/17 17:38
 */
public class MyDeamonThread {



    public static void main(String[] args){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("life");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //加了就会跟着main线程一起stop，不加依旧会运行
        thread.setDaemon(true);
        thread.start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread finish lifecycle");
    }
}
