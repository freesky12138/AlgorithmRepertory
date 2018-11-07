package search;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:search
 * @description 二分查找
 * @date 2018/7/20 11:33
 */
public class BinarySearch {
    private static int LENGTH = 100000000;
    private static int[] ints = new int[LENGTH];
    private static Random random = new Random();

    public static void main(String[] args) {
        //1向10亿的数据放数据
        //2排序
        //3二分查找
        Long startTime = new Date().getTime();
        //1332 IntStream.对效率有影响，影响不大
        IntStream.range(0, LENGTH).forEach((i) -> {
            ints[i] = random.nextInt(LENGTH);
        });
        //1260
        /*
        for(int i=0;i<LENGTH;i++){
            ints[i]=random.nextInt(LENGTH);
        }*/
        Long endTime = new Date().getTime();
        System.out.println(endTime - startTime);

        //排序
        Arrays.sort(ints);

        startTime = new Date().getTime();

        System.out.println(startTime - endTime);

        int x = 345545;

        //二分
        int index = BSearch(0, LENGTH - 1, x);
        System.out.println(index);
        System.out.println(count);
        System.out.println(ints[index]);

    }

    private static int count = 0;

    private static int BSearch(int left, int right, int x) {
        int now = (left + right) / 2;
        while (now > left && now < right) {

            count++;
            if (left >= right)
                break;
            if (ints[now] == x) {
                return now;
            } else if (ints[now] > x) {
                right = now;
            } else {
                left = now;
            }

            now = (left + right) / 2 + 1;
        }
        return 0;
    }
}
