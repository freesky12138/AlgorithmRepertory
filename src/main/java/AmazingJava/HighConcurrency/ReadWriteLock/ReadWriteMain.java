package AmazingJava.HighConcurrency.ReadWriteLock;

import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ReadWriteLock
 * @description
 * @date 2018/11/5 14:55
 */
public class ReadWriteMain {


    private final static String text = "@Title:AmazingJava.HighConcurrency.ReadWriteLock=";

    public static void main(String[] args) {
        final ShareData shareData = new ShareData(50);

        IntStream.range(1, 3).mapToObj((i) -> {
            return new Thread(() -> {
                for (int i1 = 0; i1 < text.length(); i1++) {
                    try {
                        shareData.write(text.charAt(i1));
                        System.out.println(Thread.currentThread().getName() + " write " + text.charAt(i1));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }).forEach(Thread::start);


        IntStream.range(1, 10).mapToObj((i) -> {
            return new Thread(() -> {
                while (true) {
                    try {
                        String data=new String(shareData.read());
                        System.out.println(Thread.currentThread().getName() + " read " + data);
                        if(data.contains("==")){
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }).forEach(Thread::start);
    }
}
