package datastructure;

import java.util.Arrays;
import java.util.Random;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:datastructure
 * @description 优先队列=小顶堆
 * @date 2018/6/7 13:53
 */

/**
 * 对于获取最小值，最大值，很快
 * 也可以用作排序
 * }
 */
public class PriorityQueue {
    public static void main(String[] args) {

        /**
         * java 自带的优先队列 可以使用Comparator来比较在堆顶元素
         PriorityQueue<Note> priorityQueue = new PriorityQueue<Note>(new Comparator<Note>() {
         public int compare(Note o1, Note o2) {
         return o1.value<o2.value?1:0;
         }
         });
         */

        //不断入队和出队，每次取最小值达到排序的目的
        PriorityQueue priorityQueue = new PriorityQueue();
        for(int i=0;i<10000000;i++){
            priorityQueue.offer(new Random().nextInt(1000000));
        }
        for(int i=0;i<10000000;i++){
            priorityQueue.poll();
        }
    }

    private int[] queue = new int[0];
    private int size = 0;


    //新增节点
    public void offer(int in) {
        size++;
        if (size >= queue.length) {
            queue = Arrays.copyOf(queue, size * 2);
        }
        queue[size] = in;
        selfUp(size);
    }

    //二分上升,直到到达合适的位置
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

    //出队,根节点出队，将size节点放在跟节点，然后下滑到合适位置
    public int poll() {
        int temp = queue[1];
        queue[1] = queue[size];
        size--;
        //下滑
        selfDown(1);
        return temp;
    }


    private void selfDown(int i) {
        while (i * 2 <= size) {
            int child = i * 2;
            //判断左右儿子的最小值
            if (child + 1 <= size && queue[child] > queue[child + 1]) {
                child++;
            }

            if (queue[i] < queue[child]) {
                break;
            }
            swap(i, child);
            i = child;
        }
    }

    //获取根节点
    public int peak() {
        return queue[1];
    }

    public int size() {
        return size;
    }

}
