package EverydayArithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 题目：给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * */

/**
 * 题解：利用哈希表的特点，将值和出现的次数分别存在哈希表的key和value中
 * 首先遍历数组，利用map.getOrDefault()获取指定key对应对value，如果找不到key，就返回设置的默认值1，如果找到就+1
 * 同时记录数字总数count
 * 最后遍历map将大于count/3的数添加至list中
 * */
public class LK229求众数II {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for(int i = 0;i < nums.length; i++){
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            count++;
        }
        for (int num:map.keySet()) {
            if(map.get(num) > count/3){
                list.add(num);
            }
        }
        return list;
    }
}
