package EverydayArithmetic;
/**
 *给你二叉树的根结点root，此外树的每个结点的值要么是 0 ，要么是 1 。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * */

/**
 * 题解：既然是删除全为0的分支，那就可以采取后序遍历，从叶子节点直接进行操作即可
 * 如果是叶子节点并且值为0，那就将这个节点置为null即可，这样回溯的时候直接就把这个节点删除了不用遍历完再删除
 * */
public class LK814二叉树剪枝 {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        //是叶子节点并且值为0
        if(root.val == 0 && root.left == null && root.right == null){
            return null;
        }
        return root;
    }
}
