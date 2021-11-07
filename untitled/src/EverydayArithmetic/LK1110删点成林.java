package EverydayArithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 题目：给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * */

/**
 * 题解：因为如果删除就要把这个节点的左右子树（如果不为null）都添加至list中，所以我们采用后序遍历，当遍历到要删除的节点时，先把左右子树处理完再添加
 * 所以这里采用后序遍历，遍历删除的时候是定位在要删除节点的双亲结点，以为需要把待删除结点置为空所以一定要定义在前一个节点。在删除之前，将待删节点的左右子树存入list中，再将其置空即可
 * 最后出递归后，因为根节点的特殊性，所以要另加判断，如果根节点要删除就将其左右子树加入List,不删就将root加入list
 * */
public class LK1110删点成林 {
    public List<TreeNode> list;
    public Map<Integer,Integer> map;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        list = new ArrayList<>();
        map = new HashMap<>();
        for(int i = 0;i < to_delete.length; i++){
            map.put(i,to_delete[i]);
        }
        traverse(root);
        if(map.containsValue(root.val)){
            if(root.left != null)
                list.add(root.left);
            if(root.right != null)
                list.add(root.right);
        }else{
            list.add(root);
        }
        return list;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        traverse(root.right);
        if (root.left != null && map.containsValue(root.left.val)) {
            if (root.left.left != null) {
                list.add(root.left.left);
            }
            if (root.left.right != null) {
                list.add(root.left.right);
            }
            root.left = null;
        }
        if (root.right != null && map.containsValue(root.right.val)) {
            if (root.right.left != null) {
                list.add(root.right.left);
            }
            if (root.right.right != null) {
                list.add(root.right.right);
            }
            root.right = null;
        }
    }
}
