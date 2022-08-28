package jzoffer;

import java.util.HashMap;
import java.util.Map;

/*
*重建二叉树
*
* 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。
* 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
*
* 前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，
* 左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
* */
//树 根据二叉树前序和中序遍历重建该二叉树，假设前序和中序遍历中不含重复数字
public class _5 {
    // 缓存中序遍历数组每个值对应的索引
    private Map<Integer, Integer> indexForInOrders = new HashMap<>();

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        //将中序遍历的值和下标放入map
        for (int i = 0; i < in.length; i++)
            indexForInOrders.put(in[i], i);
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) //前序左右边界
            return null;
        TreeNode root = new TreeNode(pre[preL]); //前序第一个节点是根节点
        int inIndex = indexForInOrders.get(root.val); //获取根节点在中序遍历的下标
        int leftTreeSize = inIndex - inL; //计算左子树大小
        //左子树的前序左右下标分别是preL+1, preL+leftTreeSize，inL不变
        root.left = reConstructBinaryTree(pre, preL + 1, preL + leftTreeSize, inL);
        //右子树的前序左右下标分别是preL+leftTreeSize+1, preR，inL+ leftTreeSize+1
        root.right = reConstructBinaryTree(pre, preL + leftTreeSize + 1, preR, inL + leftTreeSize + 1);
        return root;
    }

    static class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
