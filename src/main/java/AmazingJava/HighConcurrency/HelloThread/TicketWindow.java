package AmazingJava.HighConcurrency.HelloThread;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.HelloThread
 * @description 模拟业务窗口叫号, 每天总共50个号
 * @date 2018/8/15 15:03
 */
public class TicketWindow extends Thread {
    private final String name;
    private static final int MAX = 1000;
    /**
     * 如果不加static，那么就是new了4个index出来，每个线程访问的都是各自线程里面的index，
     * 加了static就是访问的同一个index
     */
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台："+name+",当前号："+index++);
        }
    }

    public static void main(String args[]){
        TicketWindow ticketWindow1=new TicketWindow("一号");
        ticketWindow1.start();
        TicketWindow ticketWindow2=new TicketWindow("二号");
        ticketWindow2.start();
        TicketWindow ticketWindow3=new TicketWindow("三号");
        ticketWindow3.start();
        TicketWindow ticketWindow4=new TicketWindow("四号");
        ticketWindow4.start();
    }
}
