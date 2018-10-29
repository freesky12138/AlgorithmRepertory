import datastructure.PriorityQueue;
import search.Recursion;

import java.util.Random;


/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title: creat my AlgorithmRepertory
 * @description
 * @date 2018/6/6 19:11
 */
public class Main {
    public static void main(String[] args) {

        //优先队列
        PriorityQueue priorityQueue = new PriorityQueue();
        for(int i=0;i<10000000;i++){
            priorityQueue.offer(new Random().nextInt(1000000));
        }
        for(int i=0;i<10000000;i++){
            priorityQueue.poll();
        }
    }
}
