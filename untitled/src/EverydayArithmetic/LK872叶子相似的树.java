package EverydayArithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 *请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列 。
 * */

/**
 * 将两个树进行先序遍历，这样在判断节点的叶子节点是否为空时直接添加至list里面
 * 也就是说，将叶子节点存入两个不同的List里面，然后判断list1.equals(list2)看两个list是否相等，并将结果直接返回即可
 * */
public class LK872叶子相似的树 {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //把叶子节点的值全部存在list当中
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        //先序遍历
        traverse(root1,list1);
        traverse(root2,list2);
        //将两个list比较的结果直接返回
        return list1.equals(list2);
    }

    public void traverse(TreeNode root,List<Integer> list){
        //当root为叶子节点时，将其值添加到list中，并开始回溯
        if(root.left == null && root.right == null){
            list.add(root.val);
            return;
        }
        //先左后右进行递归遍历
        if(root.left != null){
            traverse(root.left,list);
        }
        if(root.right != null){
            traverse(root.right,list);
        }
    }
}
