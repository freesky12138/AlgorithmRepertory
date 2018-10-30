package AmazingJava.HighConcurrency.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.Observable
 * @description
 * @date 2018/10/30 17:47
 */
public class ObservableMain {
    public static void main(String[] args){
        final  TaskLifecycle<String> lifecycle=new TaskLifecycle.EmptyLifecycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("finish "+ result);
            }

            @Override
            public void onStart(Thread thread) {
                System.out.println("start ");
            }

            @Override
            public void onError(Thread thread, Exception e) {
                super.onError(thread, e);
            }

            @Override
            public void onRunning(Thread thread) {
                System.out.println("run");
            }
        };
        Observable observable=new ObservableThread<String>(lifecycle,()->{
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("over");
            return "done";
        });

        observable.start();
    }
}
