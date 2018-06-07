package datastructure;

import java.util.Arrays;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:datastructure
 * @description  优先队列=小顶堆
 * @date 2018/6/7 13:53
 */

/**
 * test code
 * 对于获取最小值，最大值，很快
 * 也可以用作排序
 PriorityQueue priorityQueue = new PriorityQueue();
 for(int i=0;i<100;i++){
 priorityQueue.offer(new Random().nextInt(1000));
 }
 for(int i=0;i<100;i++){
 System.out.printf(priorityQueue.poll()+"\t");
 }
 */
public class PriorityQueue {
    private int[] queue = new int[0];
    private int size = 0;

    public void offer(int in) {
        size++;
        if (size >= queue.length) {
            queue = Arrays.copyOf(queue, size * 2);
        }
        queue[size] = in;
        selfUp(size);
    }

    private void selfUp(int i) {
        while (i > 0) {
            if (queue[i] < queue[i / 2]) {
                swap(i, i / 2);
                i = i / 2;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int i1) {
        int temp = queue[i];
        queue[i] = queue[i1];
        queue[i1] = temp;
    }

    public int poll() {
        int temp = queue[1];
        queue[1] = queue[size];
        size--;
        selfDown(1);
        return temp;
    }

    private void selfDown(int i) {
        while (i * 2 <= size) {
            int child = i * 2;
            if (child + 1 <= size && queue[child] > queue[child + 1]) {
                child++;
            }

            if (queue[i] < queue[child]) {
                break;
            }
            swap(i,child);
            i=child;
        }
    }

    public int peak() {
        return queue[1];
    }

    public int size() {
        return size;
    }

}
