package AmazingJava.HighConcurrency.DeepUnderstandThread;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description 2.3 thread和threadGroup
 * @date 2018/9/17 15:43
 */
public class MyThreadGroup {
    public static void main(String[] args) {
        /**
         init 源码中
         如果传入了threadGroup
         就会使用传入的threadGroup，
         如果没传入，就会用父线程的threadGroup

         在main中thread的名字叫main
         在springboot中application的主线程name也是main

         两个线程group的name可以相同

         子线程和和父线程拥有相同的优先级，相同的daemon（守护进程）

         */

        ThreadGroup group1 = new ThreadGroup("testGroup");
        ThreadGroup group2 = new ThreadGroup("testGroup");
        ThreadGroup curGroup = Thread.currentThread().getThreadGroup();
        Thread t1 = new Thread("t1");
        Thread t2 = new Thread(group1, "t2");

        System.out.println(t1.getThreadGroup().getName());//main
        System.out.println(t2.getThreadGroup().getName());//testGroup
        System.out.println(group2.getName());//testGroup
        System.out.println(t1.getThreadGroup() == t2.getThreadGroup());//false
        System.out.println(t1.getThreadGroup() == group1);//false
        System.out.println(t1.getThreadGroup() == curGroup);//true
        System.out.println(t2.getThreadGroup() == group1);//true
        System.out.println(t2.getThreadGroup() == curGroup);//false
        System.out.println(group2 == group1);//false  就算两个threadgroup 名字相同，不同的 threadgroup 用==也不相同
    }
}
