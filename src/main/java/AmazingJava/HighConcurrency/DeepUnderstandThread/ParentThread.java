package AmazingJava.HighConcurrency.DeepUnderstandThread;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description       2.2 线程的父子关系
 * 一个线程一定是由另一个线程创建，创建它的线程就是新线程的父线程
 * @date 2018/9/17 13:55
 */
public class ParentThread {
    public static void main(String[] args){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        /**
         在Thead new的时候会init
         其中将创建此线程的线程作为父线程
         下面是init中源码

         private void init(MyThreadGroup g, Runnable target, String name,
         long stackSize, AccessControlContext acc,
         boolean inheritThreadLocals) {
         if (name == null) {
         throw new NullPointerException("name cannot be null");
         }

         this.name = name;

         Thread parent = currentThread();
         SecurityManager security = System.getSecurityManager();
         * */

    }
}
