package AmazingJava.HighConcurrency.SingleModle;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.SingleModle
 * @description
 * @date 2018/10/25 10:30
 */
//不予许被继承
//单例模式的饿汉式
//一开始就新建实例，在最初使用的时候快，但是可能用不上或者很长一段时间才备用，就浪费内存了
    //如果一个实例占用资源少，可以用这种方式
public final class Singleton1 {

    private byte[] data = new byte[1024];

    private static Singleton1 instance = new Singleton1();

    private Singleton1(){

    }
    //不允许被继承，一开始就新建实例
    public static Singleton1 getInstance() {
        return instance;
    }
}
