package EverydayArithmetic;

import java.util.HashMap;
import java.util.Map;
/**
 * 题目：给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * */

/**
 * 题解：由于先序遍历是【根，【左子树】，【右子树】】，而中序遍历是【【左子树】，根，【右子树】】，所以我们可以用哈希表将中序遍历的数组存起来，
 * 然后根据先序遍历提供的根节点数据查找根所在的位置，这样就能算出左子树个数和右子树个数了。
 * 在用递归创建左右子树的时候，首先分别找到根节点所在的先序遍历当中的位置以及在中序遍历当中的位置，然后建立左子树：先序左边界为原先先序左边界+1,先序右边界为原先先序左边界+左子树个数，
 * 中序左边界为原先中序左边界，中序有边界为中序根节点-1；
 * 右子树：先序左边界为原先先序边界+左子树个数+1,先序右边界为原先先序右边界，中序左边界为中序根节点+1，中序有边界为原先中序右边界
 * */
public class LK105从前序与中序遍历序列构造二叉树 {
    private Map<Integer,Integer> map;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        int len = preorder.length;
        for(int i = 0;i < len; i++){
            map.put(inorder[i],i);
        }
        return myBuildTree(preorder, inorder, 0, len-1, 0, len-1);
    }

    private TreeNode myBuildTree(int[] preorder, int[] inorder, int pre_left, int pre_right, int in_left, int in_right) {
        if(pre_left > pre_right){
            return null;
        }
        int pre_root = pre_left;
        int in_root = map.get(preorder[pre_root]);
        TreeNode node = new TreeNode(preorder[pre_root]);
        int num = in_root-in_left;
        node.left = myBuildTree(preorder,inorder,pre_left+1,pre_left+num,in_left,in_root+1);
        node.right = myBuildTree(preorder, inorder, pre_left+num+1, pre_right, in_root+1, in_right);
        return node;
    }
}
