package EverydayArithmetic;
/**
 * 题解：因为可能出现删除后原本的父节点成为叶子节点而满足条件，所以使用后序遍历
 * 当叶子节点的值为目标值时，就返回null使其成为空节点，当回溯的时候，如果原本的父节点成为叶子节点而满足条件，
 * 也同样可以进行删除
 * */
public class LK1325删除给定值的叶子节点 {
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        root.left = removeLeafNodes(root.left,target);
        root.right = removeLeafNodes(root.right,target);
        if(root.left == null && root.right == null && root.val == target){
            return null;
        }
        return root;
    }
}
