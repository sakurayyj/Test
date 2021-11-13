package EverydayArithmetic;
/**
 * 题目：一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 *
 * 问总共有多少条不同的路径？
 * */
/**
 * 题解：典型的动态规划，在f[i][j]的位置，有两种选择可以走到这个位置，f[i-1][j]和f[i][j-1]，
 * 所以动态规划方程就是f[i][j] = f[i - 1][j] + f[i][j - 1]。而需要初始化的是上边界和左边界
 * （因为是由上或左走过来），使两个边界的数据初始化值为1即可
 * */
public class LK62不同路径 {
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
