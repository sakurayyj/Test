import java.util.HashMap;
import java.util.Map;

public class loki {
    public static void main(String[] args) {
        TreeNode node = new TreeNode();
        node.value = 1;
        TreeNode root = new TreeNode();
        root.value = 2;
        node.left = root;
        TreeNode node2 = new TreeNode();
        node2.value = 3;
        node.right = node2;
        TreeNode node3 = new TreeNode();
        node3.value = 4;
        node2.left = node3;
        Mid(node);
    }

    public static void Mid(TreeNode node){
        if(node == null){
            return;
        }
        Mid(node.left);
        System.out.println(node.value);
        Mid(node.right);
    }
}
class TreeNode{
    public int value;
    public TreeNode left;
    public TreeNode right;
}