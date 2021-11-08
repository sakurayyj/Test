package EverydayArithmetic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。

 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 * */

/**
 * 题解：首先看到层序遍历就想到利用非递归和队列的操作，但是看到返回值类型就知道需要将结果一层一层添加至List中
 * 首先建立泛型为List<Integer>的List取名result，然后判断root是否为空要是空就直接返回这个List
 * 然后建立队列，先将根节点root压入队列，然后以判断条件为队列空的循环进行逐层添加元素：首先建立一个泛型为Integer的List用于存放这一层的数据，
 * 然后将此时队列的长度取出来存到int型的size中，这就是这一层要进入List的元素个数。然后For循环遍历，将节点一个一个从队列中poll出来然后存入list中，再将这个节点的孩子全部押进队列中
 * 当for循环结束，下一层的所有节点也就都在队列中了，然后将循环加入的List加入result中，到重复上述操作直至队列全空，也就是上一层为叶子节点时，结束循环。
 * 最后返回result即可
 * */
public class LK429N叉树的层序遍历 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            result.add(level);
        }
        return result;
    }
}
;