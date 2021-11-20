package EverydayArithmetic;

import java.util.ArrayList;
import java.util.List;
/**
 * 题目：数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 * */

/**
 * 题解:将所有情况都生成，再将生成的情况进行判断是否符合正确顺序闭合，符合就添加至结果中，不符合就回溯再生成
 * 首先是生成的函数，如果添加的括号个数等于2n,进行判断生成括号是否合法，不合法就退出这层函数；如果添加的括号个数不足2n，就递归先添加左括号，再递归添加右括号，
 * 判断合法的函数就是有左括号count++,有右括号就count--，判断最终如果count不为0就是不合法。当然右括号一定是在左括号后生成，所以在循环过程中一旦出现count<0状态就已经不合法了
 * */
public class LK22括号生成 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        generateAll(new char[2 * n], 0, ans);
        return ans;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int count = 0;
        for (char c: current) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
}
