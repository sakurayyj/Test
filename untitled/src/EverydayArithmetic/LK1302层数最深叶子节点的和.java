package EverydayArithmetic;
/**
 * 题目：给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和 。
 * */

/**
 * 题解：因为要挑选出层数最深的，所以不可以用后序遍历直接找节点，因为不知道层数
 * 应该先先序遍历找到层数的最大值，在此过程中将这个层数的叶子节点
 * 定义全局变量Deep存储最深的深度，sum存储这个深度的叶子节点的和，当先序遍历到叶子节点时，判断该层的深度和Deep哪个大，
 * Deep小就更新Deep和sum为新节点的层数和值，等于就让sum加上这个节点的值
 * */
public class LK1302层数最深叶子节点的和 {
    public int Deep = 0;
    public int sum = 0;
    public int deepestLeavesSum(TreeNode root) {
        traverse(root,0);
        return sum;
    }
    public void traverse(TreeNode node,int deep){
        if(node == null){
            return;
        }
        traverse(node.left,deep+1);
        traverse(node.right,deep+1);
        if(node.left == null && node.right == null){
            if(deep > Deep){
                Deep = deep;
                sum=node.val;
            }else if(deep == Deep){
                sum+=node.val;
            }
        }
    }
}
