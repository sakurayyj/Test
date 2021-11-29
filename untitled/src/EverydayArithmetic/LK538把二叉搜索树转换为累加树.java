package EverydayArithmetic;
/**
 * 题目：给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * */
/**
 * 题解：反向中序遍历，当遍历到根节点时，更新sum值，将该节点的下一个节点的值改为sum，一直向左遍历至结束
 * */
public class LK538把二叉搜索树转换为累加树 {
    public int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {
            root.right = convertBST(root.right);
            sum += root.val;
            root.val = sum;
            root.left = convertBST(root.left);
        }
        return root;
    }
}
