package AmazingJava.HighConcurrency.Volatile;
//可见性
public class x1Volatile {
    public static boolean flag=false;
    public static void main(String [] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("star");
                while (flag==false){
                    //System.out.println(flag);
                }
                System.out.println("suggess");

            }
        }).start();

        Thread.sleep(2333);
        new Thread(new Runnable() {
            @Override
            public void run() {
                flag=true;

            }
        }).start();
    }
}
