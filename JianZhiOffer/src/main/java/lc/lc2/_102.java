package lc.lc2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
* 二叉树的层序遍历
*
* 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
*
* 广度优先遍历是按层层推进的方式，遍历每一层的节点。题目要求的是返回每一层的节点值，所以这题用广度优先来做非常合适。
广度优先需要用队列作为辅助结构，我们先将根节点放到队列中，然后不断遍历队列。
* 首先拿出根节点，如果左子树/右子树不为空，就将他们放入队列中。第一遍处理完后，根节点已经从队列中拿走了，
* 而根节点的两个孩子已放入队列中了，现在队列中就有两个节点 2 和 5。
* 第二次处理，会将 2 和 5 这两个节点从队列中拿走，然后再将 2 和 5 的子节点放入队列中，现在队列中就有三个节点 3，4，6。
* 我们把每层遍历到的节点都放入到一个结果集中，最后返回这个结果集就可以了。
时间复杂度： O(n)     空间复杂度：O(n)
* */
public class _102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) { //如果树为空，返回空数组
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> res = new ArrayList<List<Integer>>(); //存放结果
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);  //根节点放入队列，然后不断遍历队列
        while (queue.size() > 0) {
            //获取当前队列长度，这相当于这一层的节点数
            int size = queue.size();
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时list中
            //如果节点的左/右子树不为空，放入队列中
            for (int i = 0; i < size; ++i) {
                TreeNode t = queue.remove();
                tmp.add(t.val);
                if (t.left != null) {
                    queue.add(t.left);
                }
                if (t.right != null) {
                    queue.add(t.right);
                }
            }
            //将临时list(每层)加入结果
            res.add(tmp);
        }
        return res; //返回结果
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

}
