package AmazingJava.HighConcurrency.ThreadApi.FightQueryTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:AmazingJava.HighConcurrency.ThreadApi.FightQueryTask
 * @description  3-4
 * @date 2018/9/19 10:44
 */
public class FightQueryMain {

    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {

        List<String> result = search("CD", "BJ");

        result.forEach(System.out::println);
    }

    private static List<String> search(String origin, String destiion) {

        List<String> res = new ArrayList<>();

        List<FightQueryTask> tasks = fightCompany.stream().map(f -> {
            return new FightQueryTask(f, origin, destiion);
        }).collect(Collectors.toList());

        tasks.forEach(Thread::start);

        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        tasks.stream().map(FightQueryTask::get).forEach(ft -> {
            res.addAll(ft);
        });
        return res;
    }
}
