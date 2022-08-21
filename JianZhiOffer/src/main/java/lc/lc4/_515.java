package lc.lc4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
* 在每个树行中找最大值
*
* 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
示例1：
输入: root = [1,3,2,5,3,null,9]
输出: [1,3,9]
示例2：
输入: root = [1,2,3]
输出: [1,3]
*
* 方法二：广度优先搜索
「广度优先搜索」中的队列里存放的是「当前层的所有节点」。每次拓展下一层时，把当前队列中全部节点拿出来拓展，保证每次拓展完队列里放的是下一层所有节点，
* 即一层一层地拓展，然后每一层我们用maxVal 标记该层节点的最大值。当该层全部节点都处理完后，maxVal 就是该层全部节点中的最大值。
* */
public class _515 {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int maxVal = Integer.MIN_VALUE;
            while (len > 0) {
                len--;
                TreeNode t = queue.poll();
                maxVal = Math.max(maxVal, t.val);
                if (t.left != null) {
                    queue.offer(t.left);
                }
                if (t.right != null) {
                    queue.offer(t.right);
                }
            }
            res.add(maxVal);
        }
        return res;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
