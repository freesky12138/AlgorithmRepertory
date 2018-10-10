package AmazingJava.HighConcurrency.DeepUnderstandThread;

import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description 2-1 对于一个线程他是有自己的名字的
 * 如果没有对一个线程命名，那么系统就会给这个线程取一个名字Thread-0,1,2,3
 * @date 2018/8/27 15:02
 */
public class NamedThread {
    public static void main(String[] args) {

        //下面三种写法是相同的
        //1
        IntStream.range(0, 5).boxed().map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);

        //2
        IntStream.range(0, 5).boxed().map(i->
           new Thread(()->{
               System.out.println(Thread.currentThread().getName());
           })
        ).forEach(Thread::start);

        //3
        IntStream.range(0, 5).mapToObj(
                i->
                {
                    return new Thread(()->{
                    System.out.println(Thread.currentThread().getName());
                    });
                }
        ).forEach(Thread::start);

        IntStream.range(0,5).mapToObj(NamedThread::createThread).forEach(Thread::start);

        //线程在启动前有一个机会可以对线程名字进行更改，一旦线程启动字后，就不能更改名字

    }
    private static Thread createThread(final int i){
        return new Thread(()->System.out.println(Thread.currentThread().getName()),"PRE"+i);
    }
}
