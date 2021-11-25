package EverydayArithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 题目：给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案
 * */
/**
 * 题解：利用循环和递归一同交换数据，首先外层循环交换第first个（初始化为0）和第i个，内层嵌套递归交换第1个和第i个，一次循环直到first达到第n-1个和第i个交换，所以递归的条件的first+1。
 * 出递归后，用回溯将第first个和第i个交换回来
 * 当first达到n时，将output添加至res中，相当于一个序列交换完毕，然后进行不断回溯
 * */
public class LK46全排列 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            Collections.swap(output, first, i);
            backtrack(n, output, res, first + 1);
            Collections.swap(output, first, i);
        }
    }
}
