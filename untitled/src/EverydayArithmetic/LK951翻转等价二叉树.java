package EverydayArithmetic;
/**
 * 题目：我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 * */

/**
 * 题解：true的条件有:两个节点的值相等，或者root1和root2都为 null
 *因为存在翻转问题，所以会判断root1的左子树和root2的左子树或右子树的返回值是否一致，root2的左子树也是同样状况
 * */
public class LK951翻转等价二叉树 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;
        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}
