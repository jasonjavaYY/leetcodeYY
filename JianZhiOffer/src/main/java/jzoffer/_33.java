package jzoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
* 从上往下打印二叉树
*
* 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
* 例如，以下二叉树层次遍历的结果为：1,2,3,4,5,6,7
*
* 使用队列来进行层次遍历。
* 不需要使用两个队列分别存储当前层的节点和下一层的节点，因为在开始遍历一层的节点时，
* 当前队列中的节点数就是当前层的节点数，只要控制遍历这么多节点数，就能保证这次遍历的都是当前层的节点
* */
//树 从上往下打印二叉树每个节点，同层从左至右
public class _33 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>(); //辅助队列
        ArrayList<Integer> ret = new ArrayList<>(); //存放结果的数组
        queue.add(root); //先把根节点加入队列
        while (!queue.isEmpty()) { //循环，当队列不为空时
            int cnt = queue.size(); //计算队列大小
            while (cnt-- > 0) { //队列元素每次弹出一个
                TreeNode t = queue.poll();
                if (t == null)
                    continue;
                //如果弹出元素不为空，将值放在结果数组，将左右节点入队列
                ret.add(t.val);
                queue.add(t.left);
                queue.add(t.right);
            }
        }
        return ret;
    }
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
