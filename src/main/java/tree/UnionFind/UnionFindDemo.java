package tree.UnionFind;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree.UnionFind
 * @description
 * @date 2018/6/12 9:50
 */
public class UnionFindDemo {


    private int[] par;
    private int[] rank;//层数

    public static void main(String[] args){

    }

    class Solution {
        public int longestConsecutive(int[] nums) {
            //[100, 4, 200, 1, 3, 2]
            //[2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645]
            //[1,3,5,2,4]
            if(nums.length==0)
                return 0;
            HashMap<Integer,Integer> hashMap=new HashMap<Integer, Integer>();
            int max=1;

            for(int i=0;i<nums.length;i++){
                hashMap.put(nums[i],1);
                if(hashMap.get(nums[i]+1)!=null){
                    hashMap.put(nums[i],hashMap.get(nums[i]+1)+1);
                    max=Math.max(max,hashMap.get(nums[i]));
                }
                if(hashMap.get(nums[i]-1)!=null){
                    hashMap.put(nums[i]-1, Math.max(hashMap.get(nums[i]-1),hashMap.get(nums[i])+1));
                    max=Math.max(max,hashMap.get(nums[i]-1));
                }

            }
            return max;
        }
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
