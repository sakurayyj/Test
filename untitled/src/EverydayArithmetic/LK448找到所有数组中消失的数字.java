package EverydayArithmetic;
/**
 * 题目：给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 *
 * */
/**
 * 题解：用桶排序的思想，新建一个数组arr，将原数组中出现的数对应下标所在的新数组的位置的地方，把值改为1
 * 这样在二次遍历的时候只要出现新数组中为0的地方，就将下标+1添加至list中即可
 * */
import java.util.ArrayList;
import java.util.List;

public class LK448找到所有数组中消失的数字 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int[] arr = new int[nums.length];
        List<Integer> ans = new ArrayList();
        for(int i = 0;i < nums.length; i++){
            arr[nums[i]-1] = 1;
        }
        for(int i = 0;i < nums.length; i++){
            if(arr[i] == 0){
                ans.add(i+1);
            }
        }
        return ans;
    }
}
