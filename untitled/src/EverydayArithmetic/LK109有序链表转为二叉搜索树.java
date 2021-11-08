package EverydayArithmetic;
/**
 * 题目：给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

 * */
/**
 * 题解：首先寻找链表中间的节点作为整棵树的根节点，我们可以采取快慢指针，当快指针到达链表最后，慢指针就在链表中间节点
 * 我们将取链表某一闭区间的中间节点封装成一个函数，这样生成二叉树的时候就反复调用该函数找到根节点
 * 在构建左右子树时，利用dfs函数进行递归生成分支，当区间的左右值相等时，就说明已经生成根节点了，就return；如还没到根节点，就new一个TreeNode对象存放此时的根节点，
 * 再将这个根的左区间和有区间分别调用递归生成子树
 * */
public class LK109有序链表转为二叉搜索树 {
    public TreeNode sortedListToBST(ListNode head) {
        return  dfs(head,null);
    }
    public TreeNode dfs(ListNode left,ListNode right){
        if(left==right) return null;
        ListNode middle=getMiddle(left,right);
        TreeNode root=new TreeNode(middle.val);
        root.left=dfs(left,middle);
        root.right=dfs(middle.next,right);

        return root;
    }
    public ListNode getMiddle(ListNode left, ListNode right){
        ListNode fast=left;
        ListNode slow=left;
        while(fast!=right&&fast.next!=right){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

}

