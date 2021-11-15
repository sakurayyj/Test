package EverydayArithmetic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 *题目：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * */

/**
 * 题解：利用滑动窗口算法。用哈希集合来记录每个字符是否出现过.i作为做指针，right作为右指针，初始化值为-1，代表还没有开始进行移动
 *双层循环，外层控制左边界移动，内层左边界开始的找到无重复子串。在内层循环开始前，将上一次外层循环的左边界删除，
 * 内层循环只要没有在set中重复的数就将右边界往右边移动
 * */
public class LK3无重复字符的最长子串 {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<Character>();
        int n = s.length();
        int right = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (right + 1 < n && !set.contains(s.charAt(right + 1))) {
                set.add(s.charAt(right + 1));
                right++;
            }
            ans = Math.max(ans, right - i + 1);
        }
        return ans;
    }
}
