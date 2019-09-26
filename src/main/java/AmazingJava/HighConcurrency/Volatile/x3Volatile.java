package AmazingJava.HighConcurrency.Volatile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//可见性
public class x3Volatile {
    public static int x=0,y=0;
    public static Set<String> map=new HashSet<>();
    public static void main(String [] args) throws InterruptedException {
        while (true){
            x=0;
            y=0;
            new Thread(new Runnable() {
            @Override
            public void run() {
                    int a=x;
                    y=1;
                    map.add(a+","+x);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int a=y;
                x=1;
                map.add(a+","+y);

            }
        }).start();
            System.out.println(map);

        }
    }
}
