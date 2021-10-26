package EverydayArithmetic;
/**
 * 题目:给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 * */

/**
 *题解：定义一个ans存放叶子节点的值的总和。通过带值遍历树，在每一次递归的时候将节点的值更新为cur * 2 + root.val，
 * 也就是自身值转换为的二进制。当判断为叶子节点时，将ans更新也就是加上叶子节点的值
 * */
public class LK1022从根到叶的二进制数之和 {
    public int ans;
    public int sumRootToLeaf(TreeNode root) {
        traverse(root, 0);
        return ans;
    }

    public void traverse(TreeNode root, int cur){
        if(root == null){
            return;
        }
        if(root.left == null && root.right == null){
            ans += cur * 2 + root.val;
            return;
        }
        traverse(root.left, cur * 2 + root.val);
        traverse(root.right, cur * 2 + root.val);
    }
}
