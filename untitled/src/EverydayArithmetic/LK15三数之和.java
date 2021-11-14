package EverydayArithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 题目：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 * */

/**
 * 题解：利用两数之和的思想，将后两个数用两数字和选出来，而目标值是第一个数的倒数。
 * 首先外层循环控制第一个数字，当其下标>0判断是否与前一个数重复，因为同组数据只需找出一次。然后就进行两数相加
 * 首先外层循环控制第二个数，当其下标>first+1判断是否与前一个数重复，不重复就控制第三个数从后往前遍历直至到达第二个数，如果还没满足nums[second] + nums[third] = target，
 * 就说明没找到，直接break循环，说明nums[second]已经太大了不可能出现目标数了。如果找到，按照指定格式输入进list重。
 * */
public class LK15三数之和 {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3){
            return null;
        }
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> oList = new ArrayList<>();
        List<Integer> iList;
        for(int first = 0;first < len; first++){
            if(first > 0 && nums[first] == nums[first-1]){
                continue;
            }
            int target = -nums[first];
            int third = len-1;
            for(int second = first+1; second < len; second++){
                if(second > first+1 && nums[second] == nums[second-1]){
                    continue;
                }
                while (second < third && nums[second] + nums[third] > target){
                    third--;
                }

                if(second == third){
                    break;
                }
                if(nums[second]+nums[third] == target){
                    iList = new ArrayList<>();
                    iList.add(nums[first]);
                    iList.add(nums[second]);
                    iList.add(nums[third]);
                    oList.add(iList);
                }
            }
        }
        return oList;
    }
}
