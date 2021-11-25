package EverydayArithmetic;
/**
 * 题目：给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 **/
/**
 * 题解：用递归遍历树，遍历左子树的深度L和右子树的深度R，计算d_node即L+R+1 并更新ans，
 * 最后计算d_node即L+R+1 并更新ans
 * */
public class LK543二叉树的直径 {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = depth(node.left);
        int R = depth(node.right);
        ans = Math.max(ans, L+R+1);
        return Math.max(L, R) + 1;
    }
}
