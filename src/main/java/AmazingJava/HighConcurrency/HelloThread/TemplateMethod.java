package AmazingJava.HighConcurrency.HelloThread;

import java.util.*;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HelloThread
 * @description
 * @date 2018/8/15 14:52
 *
 * 1-2
 * 使用下面方法模拟线程的执行，wrapPring相当于是run方法，print相当于start方法
 */
public class TemplateMethod {

    public final void printf(String msg){
        System.out.println("----------------------------");
        wrapPring(msg);
        System.out.println("----------------------------");
    }

    protected void wrapPring(String msg) {

    }

    public static void main(String[] args){
        TemplateMethod t1 =new TemplateMethod(){
            @Override
            protected void wrapPring(String msg) {
                System.out.println("t1 "+msg);
            }
        };
        t1.printf("hello");

        TemplateMethod t2 =new TemplateMethod(){
            @Override
            protected void wrapPring(String msg) {
                System.out.println("t2 "+msg);
            }
        };
        t2.printf("hello");
    }
}
