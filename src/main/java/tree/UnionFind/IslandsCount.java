package tree.UnionFind;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description 并查集
 * @date 2018/6/11 18:40
 */
public class IslandsCount {


    /**
     * 并查集处理水堆问题(leetCode  200 岛屿的个数)
     * <p>
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     * <p>
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * <p>
     * 输出: 3
     */
    class Solution {

        private int[] par;
        private int[] rank;//层数


        public int numIslands(char[][] grid) {
            if (grid.length == 0)
                return 0;
            par = new int[grid.length * grid[0].length];
            rank = new int[grid.length * grid[0].length];

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    par[i * grid[i].length + j] = i * grid[i].length + j;
                    if (grid[i][j] == '1') {
                        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                            union(par[(i - 1) * grid[i].length + j], par[i * grid[i].length + j]);
                        }
                        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                            union(par[(i) * grid[i].length + j - 1], par[i * grid[i].length + j]);
                        }
                    } else {
                        par[i * grid[i].length + j] = -1;
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < par.length; i++) {
                if (par[i] == i) {
                    res++;
                }
            }
            return res;
        }


        /**
         * 合并节点的时候使用rank，层数
         * 两棵树rank相同的时候，把层数少的放在层数多的上面作为子节点，这样两棵树的层数不会变化
         * 只有当两棵树层数相同的时候，才会父节点的层数变化
         *
         * @param x
         * @param y
         */
        void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }
            if (rank[x] < rank[y]) {
                par[x] = y;
            } else if (rank[x] > rank[y]) {
                par[y] = par[x];
            } else {
                par[y] = x;
                rank[x]++;
            }

        }

        /**
         * 使用路径压缩，将中间所以的节点都指向根
         *
         * @param x
         * @return
         */
        int find(int x) {
            if (x == par[x]) {
                return x;
            }
            return par[x] = find(par[x]);
        }

        boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }


}
