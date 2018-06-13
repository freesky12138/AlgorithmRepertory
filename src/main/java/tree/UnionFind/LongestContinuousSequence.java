package tree.UnionFind;

import java.util.HashMap;

/**
 * @author hyp 1774549483@qq.com
 * @version v1.0
 * @Title:tree.UnionFind
 * @description 通过hashMap优化并查集, 处理大数量
 * @date 2018/6/13 9:27
 * <p>
 * <p>
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * [0,3,7,2,5,8,4,6,0,1]
 */
public class LongestContinuousSequence {

    public static void main(String[] args){
        int[] temp={1,0,-1};
        longestConsecutive(temp);
    }

    public static HashMap<Integer, Integer> par = new HashMap<Integer, Integer>();
    public static  HashMap<Integer, Integer> value = new HashMap<Integer, Integer>();
    private int max = 0;

    public static int longestConsecutive(int[] nums) {
        //[0,3,7,2,5,8,4,6,0,1]
        // 1 4 2 2 4 2 4 7
        //[2147483646,-2147483647,0,2,2147483644,-2147483645,2147483645]
        //[-1,0,1]
        if (nums.length == 0)
            return 0;

        int max = 1;

        for (int i = 0; i < nums.length; i++) {
            int index = nums[i];
            if (value.get(index) == null) {
                value.put(index, 1);
                par.put(index,index);
                if (value.get(index - 1) != null && value.get(index + 1) != null) {
                    int temp=value.get(find(index - 1)) + value.get(find(index + 1)) + 1;
                    ution(index, index - 1);
                    ution(index,index + 1);
                    value.put(find(index), temp);
                } else if (value.get(index - 1) != null) {
                    ution(index, index - 1);
                    value.put(find(index), value.get(find(index)) + 1);
                } else if (value.get(index + 1) != null) {
                    ution(index,index + 1 );
                    value.put(find(index), value.get(find(index)) + 1);
                }
                max=Math.max(max, value.get(find(index)));
            }
        }
        return max;
    }

    public static int find(int x) {
        if (par.get(x) == x) {
            return x;
        }
        par.put(x, find(par.get(x)));
        return par.get(x);
    }

    public static void ution(int x, int y) {
        if (find(x) == find(y)) {
            return;
        }
        par.put(find(x), find(y));
    }
}
