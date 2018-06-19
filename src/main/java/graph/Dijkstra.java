package graph;

import java.util.Arrays;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:graph
 * @description
 * @date 2018/6/14 11:20
 *
 * 用已经确定最短路径的点，去更新其他相连的边
 * 更新了后再从未确定的点中选取最小的点作为已确定的点（因为最小的点不可能再找出其他点到他的距离更小，因为其他点当前就已经距离比他大了）
 * 然后将这个已确定的点再去更新和他相连的点
 */
public class Dijkstra {
    public static int nodeSize = 7;
    public static int[][] cost = new int[nodeSize][nodeSize];
    public static boolean[] used = new boolean[nodeSize];
    public static int[] d = new int[nodeSize];

    public static void main(String[] args) {
        for(int i=0;i<cost.length;i++){
            Arrays.fill(cost[i],Integer.MAX_VALUE/2);
        }

        cost[0][1] = 2;
        cost[1][0] = 2;
        cost[0][2] = 5;
        cost[2][0] = 5;
        cost[1][2] = 4;
        cost[2][1] = 4;
        cost[1][3] = 6;
        cost[3][1] = 6;
        cost[1][4] = 10;
        cost[4][1] = 10;
        cost[2][3] = 2;
        cost[3][2] = 2;
        cost[3][5] = 1;
        cost[5][3] = 1;
        cost[4][5] = 3;
        cost[5][4] = 3;
        cost[4][6] = 5;
        cost[6][4] = 5;
        cost[5][6] = 9;
        cost[6][5] = 9;
        dijkstra(0);
        System.out.printf("");
    }

    public static void dijkstra(int s) {
        Arrays.fill(used, false);
        Arrays.fill(d, Integer.MAX_VALUE);
        d[s] = 0;

        while (true) {
            int v = -1;
            for (int u = 0; u < nodeSize; u++) {
                if (!used[u] && (v == -1 || d[u]<d[v])) {
                    v=u;
                }
            }
            if(v==-1)
                break;
            used[v]=true;

            for(int u=0;u<nodeSize;u++){
                d[u]=Math.min(d[u],d[v]+cost[v][u]);
            }
        }
    }
}
