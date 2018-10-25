package AmazingJava.HighConcurrency.SingleModle;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.SingleModle
 * @description
 * @date 2018/10/25 10:37
 * 懒汉式，在用到的时候再去初始化，优点是避免很晚用浪费内存，缺点是初始化的时候影响调用速度
 * 但是缺点是synchronized只能一个线程获取getInstance，性能地下
 */
public final class Singleton2 {
    private byte[] data = new byte[1024];

    private static Singleton2 instance;

    private Singleton2() {

    }

    //在被调用的时候再建实例，加synchronized避免多个线程同时调用
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
