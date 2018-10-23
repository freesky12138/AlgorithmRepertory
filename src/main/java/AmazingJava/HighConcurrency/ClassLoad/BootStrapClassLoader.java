package AmazingJava.HighConcurrency.ClassLoad;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ClassLoad
 * @description
 * @date 2018/10/22 8:36
 */
public class BootStrapClassLoader {
    public static void main(String[] args){
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println("Bootstrap:"+BootStrapClassLoader.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));

    }
}
