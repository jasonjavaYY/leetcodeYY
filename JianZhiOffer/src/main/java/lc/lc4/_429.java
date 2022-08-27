package lc.lc4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
* N 叉树的层序遍历
*
* 定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
示例 1：
输入：root = [1,null,3,2,4,null,5,6]
输出：[[1],[3,2,4],[5,6]]
示例 2：
输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]
*
* 方法一：广度优先搜索
对于「层序遍历」的题目，我们一般使用广度优先搜索。在广度优先搜索的每一轮中，我们会遍历同一层的所有节点。
我们首先把根节点 root 放入队列，随后在广度优先搜索的每一轮中，我们首先记录下当前队列中包含的节点个数（记为 cnt），表示上一层节点个数。
* 在这之后，我们从队列中依次取出节点，直到取出了上一层的全部 cnt 个节点为止。当取出节点 cur 时，我们将 cur 的值放入一个临时列表，
* 再将 cur 的所有子节点全部放入队列中。当这一轮遍历完成后，临时列表中就存放了当前层所有节点值。整个广度优先搜索完后，就可以得到层序遍历结果。
* */
//定一个N叉树，返回层序遍历
public class _429 {
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) {//如果树为空，返回空数组
            return new ArrayList<List<Integer>>();
        }//构造答案数组，是双层整数数组
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        Queue<Node> queue = new ArrayDeque<Node>();//构造队列
        queue.offer(root);//根节点入队
        while (!queue.isEmpty()) {//循环判断队列不为空
            int cnt = queue.size();//计算队列大小，就是某一层的节点数
            List<Integer> level = new ArrayList<Integer>();//构造存放一层节点的数组
            for (int i = 0; i < cnt; ++i) {//遍历层内cnt个节点
                Node cur = queue.poll();//出队一个节点，值加入level
                level.add(cur.val);//还要将出队节点所有子节点入队
                for (Node child : cur.children) {
                    queue.offer(child);
                }
            }//遍历完一层，将这层的level加入ans
            ans.add(level);
        }
        return ans;//返回ans
    }

    class Node {
        int val;
        List<Node> children;

    }
}
