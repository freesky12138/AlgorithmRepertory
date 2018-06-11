package tree;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree
 * @description 并查集
 * @date 2018/6/11 18:40
 */
public class UnionFind {
    private int[] par = new int[300];
    private int[] rank = new int[300];//层数

    public static void main(String[] args) {

    }

    /**
     * 合并节点的时候使用rank，层数
     * 两棵树rank相同的时候，把层数少的放在层数多的上面作为子节点，这样两棵树的层数不会变化
     * 只有当两棵树层数相同的时候，才会父节点的层数变化
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
        } else if (rank[x] > rank[y]){
            par[y]=par[x];
        }else
        {
            par[y]=x;
            rank[x]++;
        }

    }

    /**
     * 使用路径压缩，将中间所以的节点都指向根
     * @param x
     * @return
     */
    int find(int x) {
        if (x == find(x)) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    boolean same(int x, int y) {
        return find(x) == find(y);
    }
}
