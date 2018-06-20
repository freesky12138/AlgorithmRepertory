package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:graph
 * @description
 * @date 2018/6/13 19:00
 * Bellman-Ford算法
 * 有较高实用性，适用于负权图。
 * 不断循环所有的边，将记录可到达这条边的最小cost
 * 当无法更新的时候就说明已经获取到了从s点到所有点的最短路径
 * <p>
 * 负圈(负权环)，一个环所有cose加起来为负数
 */
public class BellmanFord {

    public static List<edge> edges = new ArrayList<edge>();
    public static int nodeSize = 7;
    public static int[] sortest = new int[nodeSize];

    public static void main(String[] args) {
        edges.add(new edge(0, 1, 2));
        edges.add(new edge(0, 2, 5));
        edges.add(new edge(1, 2, 4));
        edges.add(new edge(1, 3, 6));
        edges.add(new edge(1, 4, 10));
        edges.add(new edge(2, 3, 2));
        edges.add(new edge(3, 5, 1));
        edges.add(new edge(4, 5, 3));
        edges.add(new edge(4, 6, 5));
        edges.add(new edge(5, 6, 9));

        shortestPath(0);

        //检查负权环
        nodeSize = 10;
        sortest = new int[nodeSize];
        edges.add(new edge(6, 7, -9));
        edges.add(new edge(8, 6, -2));
        edges.add(new edge(7, 8, -1));

        System.out.printf(checkNegativeRing(0) + "");
    }

    //检查有没有负环  每次更新都至少有一个点是最短的，如果size个点都遍历完都还可以更新，就说明有负环
    private static boolean checkNegativeRing(int s) {

        for (int i = 0; i < nodeSize; i++) {
            sortest[i] = Integer.MAX_VALUE;
        }
        sortest[s] = 0;

        int i;
        for (i = 0; i < nodeSize; i++) {
            boolean isUpdate = false;
            for (int j = 0; j < edges.size(); j++) {
                if (sortest[edges.get(j).to] > sortest[edges.get(j).from] + edges.get(j).cost && sortest[edges.get(j).from] != Integer.MAX_VALUE) {
                    sortest[edges.get(j).to] = sortest[edges.get(j).from] + edges.get(j).cost;
                    isUpdate=true;
                }

            }
            if(isUpdate==false){
                break;
            }
        }

        if(i>=nodeSize){
            return true;
        }
        return false;
    }

    static class edge {
        int from, to, cost;

        public edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }


    //最短路
    public static void shortestPath(int s) {

        for (int i = 0; i < nodeSize; i++) {
            sortest[i] = Integer.MAX_VALUE;
        }
        sortest[s] = 0;

        while (true) {
            boolean isUpdate = false;
            //遍历所有的边，如果当前边可达并且  当前边加上费用小于目标边的最短值就更新，当无法更新所有边的时候说明已经没有更短的了时间 复杂度为v*size
            for (int i = 0; i < edges.size(); i++) {
                if (sortest[edges.get(i).from] != Integer.MAX_VALUE && sortest[edges.get(i).to] > sortest[edges.get(i).from] + edges.get(i).cost) {
                    sortest[edges.get(i).to] = sortest[edges.get(i).from] + edges.get(i).cost;
                    isUpdate = true;
                }
            }
            if (isUpdate == false)
                break;
        }
    }
}
