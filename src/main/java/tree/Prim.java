package tree;

import java.util.Arrays;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description 求图的最小生成树 Prim算法
 * 每次挑选没被选过的且最小的边，  然后用最小的边去更新能够到达的边
 * @date 2018/6/20 19:40
 */
public class Prim {
    public static int nodeSize = 7;
    public static int[][] cost = new int[nodeSize][nodeSize];
    public static boolean[] used = new boolean[nodeSize];
    public static int[] mincost = new int[nodeSize];

    public static void main(String[] args) {
        for (int i = 0; i < cost.length; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE / 2);
        }

        cost[0][1] = 10;
        cost[1][0] = 10;
        cost[0][2] = 2;
        cost[2][0] = 2;
        cost[1][3] = 5;
        cost[3][1] = 5;
        cost[2][3] = 7;
        cost[3][2] = 7;
        cost[2][4] = 1;
        cost[4][2] = 1;
        cost[2][5] = 3;
        cost[5][2] = 3;
        cost[3][5] = 1;
        cost[5][3] = 1;
        cost[3][6] = 8;
        cost[6][3] = 8;
        cost[5][6] = 5;
        cost[6][5] = 5;

        System.out.printf(prim()+"");
    }

    private static int prim() {
        for (int i = 0; i < nodeSize; i++) {
            used[i] = false;
            mincost[i] = Integer.MAX_VALUE / 2;
        }
        mincost[0] = 0;
        int res=0;
        while (true) {
            int v = -1;
            for (int i = 0; i < nodeSize; i++) {
                if (used[i] == false && (v == -1 || mincost[i] < mincost[v])) {
                    v = i;
                }
            }

            if (v == -1) {
                break;
            }

            used[v] = true;
            res+=mincost[v];
            for (int i = 0; i < nodeSize; i++) {
                mincost[i]=Math.min(mincost[i],cost[v][i]);
            }
        }
        return res;
    }
}
