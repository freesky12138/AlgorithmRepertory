package AmazingJava.HighConcurrency.DeepUnderstandThread;

import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.DeepUnderstandThread
 * @description 2-1 对于一个线程他是有自己的名字的
 * 如果没有对一个线程命名，
 * @date 2018/8/27 15:02
 */
public class NamedThread {
    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);

        IntStream.range(0, 5).boxed().map(i->
           new Thread(()->System.out.println(Thread.currentThread().getName()))
        ).forEach(Thread::start);



    }
}
