package AmazingJava.HighConcurrency.ThreadApi.FightQueryTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi
 * @description
 * @date 2018/9/19 10:13
 */
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;

    private final String destination;

    private final List<String> fightList = new ArrayList<>();

    public FightQueryTask(String name, String origin, String destination) {
        super("[" + name + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.println(String.format("query " + getName() + ", from %s to %s", origin, destination));

        Random random = new Random();
        try {
            int time = random.nextInt(10);
            TimeUnit.SECONDS.sleep(time);
            System.out.println("query " + getName() + " success");
            fightList.add(getName() + " " + time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return fightList;
    }
}
