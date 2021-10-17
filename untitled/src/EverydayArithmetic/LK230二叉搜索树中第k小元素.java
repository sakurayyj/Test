package EverydayArithmetic;
/**
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * */

import java.util.Stack;

/**
 * 题解：
 * 由于是找第k个最小值，可以使用二叉树的特性：左小又大来进行遍历，这里就可以采用中序遍历。由于需要计数来和k进行比较，这里就采用非递归来遍历。
 * 利用二重循环，外层判断是否遍历到二叉树的最后一个节点，内层循环判断是否到叶子节点
 * 先建立一个栈用于存放节点，先左遍历到叶子节点，每遍历一回root就进栈，然后将root.left赋给root继续往下遍历。
 * 如果到达叶子节点，就将栈顶元素赋给root（相当于往上回溯了一个节点），然后判断--k是否为0即是否为第k个最小值了，
 * 如果是就跳出循环直接return该节点的val；如果还没到k,就进入root右分支的遍历
 * */

public class LK230二叉搜索树中第k小元素 {
    public Stack<TreeNode> stack = new Stack<>();
    public int kthSmallest(TreeNode root, int k) {
        while (root != null || !stack.isEmpty()) {
            //进行中序遍历，因为越小越在左下
            while (root != null) {
                //保证小离栈顶越近
                stack.push(root);
                //先左遍历
                root = root.left;
            }
            //到达叶子节点，判断是否是第k个，不是就进行回溯
            root = stack.pop();
            //从下往上计数，先--是因为计数从1开始
            k--;
            //找到第k个最小值了
            if (k == 0) {
                break;
            }
            //回溯进行右边的遍历
            root = root.right;
        }
        return root.val;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}