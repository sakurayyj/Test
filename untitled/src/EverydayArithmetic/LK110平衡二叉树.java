package EverydayArithmetic;
/**
 * 题目：给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 *     一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 * */

/**
 * 题解：使用后序遍历，如果其子树的高度差符合要求，那这一层的值就+1，如果不符合要求，即子树有不符合的或者这一个节点不符合，
 * 那么就返回-1，也就是说只要返回有-1就直接判定不符合。
 * 比较高度差的时候，首先计算出其左子树的高度和右子树的高度，且其左子树或右子树的高度不符合的时候返回值为-1，
 * 再判断求出这一给节点的高度
 * */
public class LK110平衡二叉树 {
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

}
