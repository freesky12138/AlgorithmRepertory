package AmazingJava.HighConcurrency.SingleModle;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.SingleModle
 * @description
 * @date 2018/10/25 10:37
 * 懒汉式，在用到的时候再去初始化，优点是避免很晚用浪费内存，缺点是初始化的时候影响调用速度
 *Holer在调用的时候才会初始化，而在holder中已经初始化了又能保持原子性，可见性
 */
public final class Singleton4 {
    private byte[] data = new byte[1024];


    private Singleton4() {

    }

    public static class Holder {
        private static Singleton4 instance = new Singleton4();
    }

    public static Singleton4 getInstance() {

        return Holder.instance;
    }
}
