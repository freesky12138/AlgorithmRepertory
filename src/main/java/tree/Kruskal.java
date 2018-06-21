package tree;


import java.util.Arrays;
import java.util.Comparator;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description 最小生成树  每次取最小的边
 * @date 2018/6/20 19:40
 */
public class Kruskal {

    public static int nodeSize = 7;
    public static int eSize = 9;
    public static edge[] edges = new edge[eSize];

    public static int[] sortest = new int[nodeSize];

    public static int par[] = new int[nodeSize];

    public static void main(String[] args) {
        edges[0] = (new edge(0, 1, 10));
        edges[1] = (new edge(0, 2, 2));
        edges[2] = (new edge(1, 3, 5));
        edges[3] = (new edge(2, 3, 7));
        edges[4] = (new edge(2, 4, 1));
        edges[5] = (new edge(2, 5, 3));
        edges[6] = (new edge(3, 5, 1));
        edges[7] = (new edge(3, 6, 8));
        edges[8] = (new edge(5, 6, 5));



        System.out.printf(kruskal()+"");
    }


    public static class edge {
        int from, to, cost;

        public edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int kruskal() {

        for (int i = 0; i < nodeSize; i++) {
            par[i] = i;
        }

        Arrays.sort(edges, new Comparator<edge>() {
            public int compare(edge o1, edge o2) {
                return o1.cost > o2.cost ? 1 : -1;
            }
        });

        int res = 0;
        for (int i = 0; i < edges.length; i++) {
            edge e = edges[i];
            if (!same(e.from, e.to)) {
                union(e.from, e.to);
                res += e.cost;
            }
        }
        return res;
    }

    private static void union(int from, int to) {
        if (find(from)==find(to)){
            return;
        }
        par[from]=to;
    }

    public static int find(int x) {
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    public static boolean same(int x, int y) {
        return find(x) == find(y);
    }
}
