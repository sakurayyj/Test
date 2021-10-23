package EverydayArithmetic;

import java.util.ArrayList;
import java.util.List;
/**
 * 题目：给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树
 * /

/**
 * 题解：有效的二叉搜索树，就是节点的左子树只包含小于当前节点的数，节点的右子树只包含大于当前节点的数，并且所有左子树和右子树自身必须也是二叉搜索树。
 * 所以可以采用递归实现中序遍历，这样如果是有效二叉搜索树的话存进去的就是按从小到大顺序存储的，再将遍历结果添加至List中进行进一步判断即可
 * 如果list中存在前面的数大于等于后面的数，那么就不是；否则就是。
 */

public class LK98验证二叉搜索树 {
    public List<Integer> list = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        traverse(root);
        for(int i = 1;i < list.size(); i++){
            if(list.get(i-1) >= list.get(i)){
                return false;
            }
        }
        return true;
    }
    public void traverse(TreeNode root){
        if(root == null){
            return;
        }
        traverse(root.left);
        list.add(root.val);
        traverse(root.right);
    }
}
