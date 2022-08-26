package lc.lc2;

import java.util.ArrayList;
import java.util.List;

/*
 * 二叉树的后序遍历
 * 给你一棵二叉树的根节点 root ，返回其节点值的 后序遍历 。
 * */
public class _145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>(); //存放结果
        postorder(root, res);
        return res;
    }

    public void postorder(TreeNode root, List<Integer> res) {
        if (root == null) {//如果节点为空返回
            return;
        }//左右根
        postorder(root.left, res);//递归左右
        postorder(root.right, res);
        res.add(root.val);//节点放入res
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
