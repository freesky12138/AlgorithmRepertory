package AmazingJava.HighConcurrency.ClassLoad;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ClassLoad
 * @description 9.3类的加载过程
 * 类的加载过程是将class文件、或者java包，war包加载到内存中，主要分为验证、准备和解析 三个过程
 * 一：加载阶段：查找并且加载类的二进制class文件
 * 二：连接阶段：
 * 1:验证：确保class等文件的字节流包含的内容符合当前jvm的要求，并且不会伤害jvm自身。
 * 2:准备：通过验证后，开始对静态变量分配设置初始值。
 * 3:解析：在常量池寻找类、接口、字段和方法引用。
 * 三：初始化阶段：为类的静态变量富裕正确的初始值。
 * @date 2018/10/18 10:04
 */
public class Singleton {
    //d
    private static int x = 0;

    private static int y;

    private static Singleton instance = new Singleton();

    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }

}
