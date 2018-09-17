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

        Thread t1 = new Thread("t1");

        ThreadGroup group1 = new ThreadGroup("testGroup");
        ThreadGroup group2 = new ThreadGroup("testGroup");

        Thread t2 = new Thread(group1, "t2");

        ThreadGroup curGroup = Thread.currentThread().getThreadGroup();

        System.out.println(t1.getThreadGroup().getName());
        System.out.println(t2.getThreadGroup().getName());
        System.out.println(group2.getName());
        System.out.println(t1.getThreadGroup() == t2.getThreadGroup());
        System.out.println(t1.getThreadGroup() == group1);
        System.out.println(t1.getThreadGroup() == curGroup);
        System.out.println(t2.getThreadGroup() == group1);
        System.out.println(t2.getThreadGroup() == curGroup);
    }
}
