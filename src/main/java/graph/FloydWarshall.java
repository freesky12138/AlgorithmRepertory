package graph;

import java.util.Arrays;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:graph
 * @description
 * @date 2018/6/20 9:24
 * 求 任意 两点之间的最短路问题
 * 带有dp的思想，每加入一个点，就用这个点更新整个路径
 * 在加入这个点之前，是已经求得的最短路,不包含这个点的最短路
 *
 */
public class FloydWarshall {

    public static int v=7;
    public static int[][] cost = new int[v][v];

    public static void main(String[] args){
        for (int i = 0; i < cost.length; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE / 2);
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
        floydWarshall();
        System.out.printf(cost[0][6]+"");
    }

    public static void floydWarshall(){
        for(int i=0;i<v;i++){
            for(int j=0;j<v;j++){
                for(int k=0;k<v;k++){
                    cost[j][k]=Math.min(cost[j][k],cost[j][i]+cost[i][k]);
                }
            }
        }
    }
}
