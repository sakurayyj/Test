package EverydayArithmetic;

import java.util.Arrays;

/**
 * 题解：首先利用排序将二维数组中的所有一维数组的第一个元素进行升序排序，这里参数的意义是：假设传来两个值，v1 与 v2，那么他们的先后顺序以 v1[0] 比 v2[0] 的结果为准，
 * 即：若 v1[0] < v2[0] 则 v1 < v2，若 = 则 =，若 > 则 >
 *然后遍历原来的二维数组。如果结果数组是空的，或者当前区间的起始位置 > 结果数组中最后区间的终止位置，则不合并，直接将当前区间加入结果数组。
 *反之将当前区间合并至结果数组的最后区间。最终需要截取数组中index+1到res的部分
 * */
public class LK56合并区间 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] res = new int[intervals.length][2];
        int idx = -1;
        for (int[] interval: intervals) {
            if (idx == -1 || interval[0] > res[idx][1]) {
                res[++idx] = interval;
            } else {
                res[idx][1] = Math.max(res[idx][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, idx + 1);
    }
}

