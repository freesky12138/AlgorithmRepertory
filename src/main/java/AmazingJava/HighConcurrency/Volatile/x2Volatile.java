package AmazingJava.HighConcurrency.Volatile;
//原子性
public class x2Volatile {
    public static int num=0;
    public static void main(String [] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<50000;i++){
                    num++;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<50000;i++){
                    num++;
                }

            }
        }).start();

        Thread.sleep(2333);
        System.out.println(num+"");
    }
}
