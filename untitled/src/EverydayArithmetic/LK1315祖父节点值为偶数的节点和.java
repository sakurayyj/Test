package EverydayArithmetic;

/**
 * 题目：给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 *
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0 。

 * */

/**
 * 题解：因为要找祖父节点，所以采用先序遍历先查找值为偶数的节点，再找它的孙子。
 * 在判断的过程中，不可以跳过判断有无孩子节点直接判断孙子节点是否存在，可能会出现越界的异常
 * 找到就加至全局变量sum中即可
 * */

public class LK1315祖父节点值为偶数的节点和 {
    public int sum;
    public int sumEvenGrandparent(TreeNode root) {
        sum = 0;
        traverse(root);
        return sum;
    }

    private void traverse(TreeNode root) {
        if(root == null){
            return;
        }
        traverse(root.left);
        traverse(root.right);
        if(root.val % 2 == 0){
            if(root.left != null){
                if(root.left.left != null){
                    sum+=root.left.left.val;
                }
                if(root.left.right != null){
                    sum+=root.left.right.val;
                }
            }
            if(root.right != null){
                if(root.right.left != null){
                    sum+=root.right.left.val;
                }
                if(root.right.right != null){
                    sum+=root.right.right.val;
                }
            }

        }
    }
}
