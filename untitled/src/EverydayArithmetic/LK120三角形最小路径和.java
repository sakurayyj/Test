package EverydayArithmetic;

import java.util.List;
/**
 * 题目：给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 * 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 * 也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。
 * */

/**
 * 题解：这题使用动态规划，每次遍历一个list的时候，将dp更新为在这一行处每个点的最小可能值，从下往上、从左往右遍历，最终dp[0]就是最小值
 * 首先初始化dp数组的值为triangle最后一行的所有数据，然后将triangle一行一行往上遍历，每次将遍历的那个数据对应的dp位置的值更新为改点数据加上下一行正下方和下一行右下方的数据的最小值
 *
 * */
public class LK120三角形最小路径和 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int dp[] = new int[n];
        for(int i = 0;i < n; i++){
            dp[i] = triangle.get(n-1).get(i);
        }
        for(int i = n-2; i >= 0; i--){
            for(int j = 0;j <= i; j++){
                dp[j] = Math.min(dp[j],dp[j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}

