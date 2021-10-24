package EverydayArithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 题目：给你二叉搜索树的根节点 root ，该树中的两个节点被错误地交换。请在不改变其结构的情况下，恢复这棵树。
 * */

/**
 * 题解：根据二叉搜索树有序性，其中序遍历结果应该是一个递增序列、而此时有两个错误节点待交换，所以将二叉树进行中序遍历并将结果存储在两个list中，
 * 遍历完毕后将一个list进行排序，再将两个list中的数依次进行比较（这时不能用==因为这样比较的时地址值而不是真正的数值），不一样的就将两个值分别存储在cnt1和cnt2中。
 * 最后进行遍历，因为错位的缘由，所以首先遍历到的值一定是较大的那一个，所以将cnt1的值赋给它；等到遍历到原始值为cnt1的节点时，将cnt2赋给它并直接return退出函数。
 * */
public class LK99恢复二叉搜索树 {
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    public int cnt1,cnt2;
    public void recoverTree(TreeNode root) {
        traverse(root,list1,list2);
        Collections.sort(list1);
        for(int k = 0;k < list1.size(); k++){
            if(list1.get(k) - list2.get(k) != 0){
                cnt1 = list1.get(k);
                cnt2 = list2.get(k);
                break;
            }
        }

        traverse2(root,cnt1,cnt2,0);
    }
    public void traverse(TreeNode node,List<Integer> list1,List<Integer> list2) {
        if(node == null){
            return;
        }
        traverse(node.left,list1,list2);
        list1.add(node.val);
        list2.add(node.val);
        traverse(node.right,list1,list2);
    }
    public void traverse2(TreeNode node,int cnt1,int cnt2,int flag){
        if(node == null){
            return;
        }
        traverse2(node.left,cnt1,cnt2,flag);

        if(node.val == cnt1 ){
            node.val = cnt2;
            return;
        }
        if(node.val == cnt2 ){
            node.val = cnt1;
            flag = 1;
        }
        traverse2(node.right,cnt1,cnt2,flag);
    }
}
