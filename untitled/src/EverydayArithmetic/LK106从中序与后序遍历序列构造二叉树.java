package EverydayArithmetic;

import java.util.HashMap;
import java.util.Map;
/**
 *根据一棵树的中序遍历与后序遍历构造二叉树。
 * */
/**
 * 题解：根据后序遍历的顺序，遍历结果为【【左子树】，【右子树】，根】，如果要找到根节点，就可以从右至左遍历，然后先创建右子树再创建左子树
 * 首先还是将中序遍历结果存入hashMap当中，键为中序遍历的值，值为其在中序遍历中的结果。
 * 由于要将后序遍历的结果从后往前遍历，所以定义一个全局变量post用于遍历，初始化值为postorder.length-1
 * 在递归创建右子树的时候，左边界为该层根节点在中序遍历结果中的位置+1，有边界为postorder.length-1；创建左子树的时候，左边界为0，右边界为该层根节点在中序遍历结果中的位置-1
 * */
public class LK106从中序与后序遍历序列构造二叉树 {
    private Map<Integer,Integer> map;
    private int post;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        map = new HashMap<>();
        int len = postorder.length;
        for(int i = 0;i <len; i++){
            map.put(inorder[i],i);
        }
        post = len-1;
        return myBuildTree(inorder,postorder,0,len-1);
    }
    private TreeNode myBuildTree(int[] inorder,int[] postorder, int in_left, int in_right) {
        if(in_left > in_right){
            return null;
        }
        int root_val =  postorder[post];
        TreeNode node = new TreeNode(root_val);
        int index = map.get(root_val);
        post--;
        node.right = myBuildTree(inorder,postorder,index+1,in_right);
        node.left = myBuildTree(inorder,postorder,in_left,index-1);
        return node;
    }
}
