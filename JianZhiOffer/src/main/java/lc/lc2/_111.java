package lc.lc2;

/*
* 二叉树的最小深度
*
* 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。说明：叶子节点是指没有子节点的节点
*
* 方法一：深度优先搜索
首先可以想到使用深度优先搜索的方法，遍历整棵树，记录最小深度。
对于每一个非叶子节点，我们只需要分别计算其左右子树的最小叶子节点深度。这样就将一个大问题转化为了小问题，可以递归地解决该问题。
* */
public class _111 {
    public int minDepth(TreeNode root) {
        if (root == null) { //如果树为空，返回0
            return 0;
        }//如果左或右子树为空，返回1
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE; //初始值最小深度为最大整数
        if (root.left != null) { //递归求左子树深度并更新最小深度
            min_depth = Math.min(minDepth(root.left), min_depth);
        } //递归求右子树深度并更新最小深度
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1; //返回最小深度+1，因为根节点深度没统计
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}
