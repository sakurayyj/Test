package EverydayArithmetic;

import java.util.*;
/**
 * 题目：
 * */
/**
 * 题解：利用广度优先搜索和拓扑排序，遍历循环找出入度为0的节点添加至队列中，然后判断队列是否为空，不为空就将元素出队，
 * 出队的时候判断与它邻接的节点的入度-1后是否为0，是的话就依次入栈，然后再次循环队列直至队列为空。当有元素出队的时候，就将计数器++，
 * 如果最后遍历完毕而出队元素的个数小于总元素个数，就说明图中有环，返回false，否则返回true
 * */
public class LK207课程表 {
    List<List<Integer>> edges;
    int[] indeg;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; ++i) {
            edges.add(new ArrayList<Integer>());
        }
        indeg = new int[numCourses];
        for (int[] info : prerequisites) {
            edges.get(info[1]).add(info[0]);
            ++indeg[info[0]];
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; ++i) {
            if (indeg[i] == 0) {
                queue.offer(i);
            }
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            ++visited;
            int u = queue.poll();
            for (int v: edges.get(u)) {
                --indeg[v];
                if (indeg[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return visited == numCourses;
    }
}
