package main.java.dp;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Huppert on 2018/6/10.
 * 线性DP
 */
public class linearDP {

    public static void main(String[] args) {
        int[] temp = {1,1,1,1,1};
        System.out.printf(findTargetSumWays(temp, 3) + "");
    }

    /**
     * 判断字符串  s是否是字符串t的子串
     * s = "abc", t = "ahbgdc"
     * 返回 true.
     * 可以优化当t.length-j  已经没有现在需要验证长度那么不需要继续了
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0)
            return true;
        int index = 0;
        for (int j = 0; j < t.length(); j++) {
            if (t.charAt(j) == s.charAt(index)) {
                index++;
            }
            if (index > s.length() - 1) {
                return true;
            }
        }
        return false;
    }


    /**
     * 给定一个字符串，找出不含有重复字符的最长子串的长度。
     * <p>
     * 示例：
     * <p>
     * 给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。
     * <p>
     * 给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。
     * <p>
     * 给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列  而不是子串。
     * <p>
     * <p>
     * 使用charCount存有没有重复的，如果有就回退
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] charCount = new int[256];
        int ans = 0;
        int maxNum = 0;
        int outIndex = 0;//outIndex为字符串前面的标志，如果出现重复就从outIndex开始往后删除，同时控制charCount


        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            charCount[c]++;
            if (charCount[c] > 1) {
                charCount[c]--;
                charCount[s.charAt(outIndex)]--;
                maxNum--;
                outIndex++;
                i--;
                continue;
            }
            maxNum++;
            if (maxNum > ans) {
                ans = maxNum;
            }
        }
        return ans;
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
     * 示例 1：
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba"也是一个有效答案。
     * 输入: "abcda"
     * 输出:
     * <p>
     * 确定一个位置，往两边寻找回文
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j < s.length(); j++) {
                if (i + j < s.length() && i - j >= 0 && s.charAt(i + j) == s.charAt(i - j)) {
                    if (j * 2 > right - left) {
                        left = i - j;
                        right = i + j;
                    }
                } else {
                    break;
                }

            }
            for (int j = 0; j < s.length(); j++) {
                if (i + j + 1 < s.length() && i - j >= 0 && s.charAt(i + j + 1) == s.charAt(i - j)) {
                    if (j * 2 + 1 > right - left) {
                        left = i - j;
                        right = i + j + 1;
                    }
                } else {
                    break;
                }

            }
        }
        return s.substring(left, right + 1);
    }


    /**
     * 最长上升序列
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxLis[] = new int[nums.length];
        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            maxLis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (maxLis[i] < maxLis[j] + 1) {
                        maxLis[i] = maxLis[j] + 1;
                        ans = maxLis[i] > ans ? maxLis[i] : ans;
                    }
                }
            }
        }
        return ans;
    }

    /**
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * <p>
     * 说明：每次只能向下或者向右移动一步。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     */
    class Solution {
        public int minPathSum(int[][] grid) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[0][j] += grid[0][j - 1];
            }

            for (int i = 1; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (j == 0) {
                        grid[i][j] += grid[i - 1][j];
                    } else {
                        grid[i][j] += grid[i][j - 1] < grid[i - 1][j] ? grid[i][j - 1] : grid[i - 1][j];
                    }
                }
            }
            return grid[grid.length - 1][grid[0].length - 1];

        }
    }

    /**
     给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。

     返回可以使最终数组和为目标数 S 的所有添加符号的方法数。


     数组的长度不会超过20，并且数组中的值全为正数。
     初始的数组的和不会超过1000。
     保证返回的最终结果为32位整数。

     */
    public  static int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0)return 0;
        int sum = 0;
        for(int num:nums)sum+=num;
        if(sum < S||(sum+S)%2 == 1)return 0;
        sum = (sum+S)/2;
        int[] dp = new int[sum+1];
        dp[0] = 1;
        for(int num:nums){
            for(int j = sum;j>=num;j--){
                dp[j] += dp[j-num];
            }
        }
        return dp[sum];
    }
}
