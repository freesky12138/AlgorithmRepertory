package AmazingJava.HighConcurrency.SingleModle;

import AmazingJava.HighConcurrency.ClassLoad.Singleton;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.SingleModle
 * @description
 * @date 2018/10/25 10:37
 * 懒汉式，在用到的时候再去初始化，优点是避免很晚用浪费内存，缺点是初始化的时候影响调用速度
 * 此例子相对于Singleton只同步初始化的操作，可以多个线程来getInstance
 */
public final class Singleton3 {
    private byte[] data = new byte[1024];

    private volatile static Singleton3 instance=null;

    private Singleton3() {

    }

    //只对instance = new Singleton3();做同步操作
    public static  Singleton3 getInstance() {
        if (instance == null) {
            synchronized (Singleton3.class){
                if(null==instance){
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}
