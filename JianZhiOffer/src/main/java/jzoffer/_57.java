package jzoffer;
/*
* 二叉树的深度
*
* 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
* */
//树 求二叉树深度，从根到叶依次经过的结点（含根、叶）形成一条路径，最长路径长度为树深度
public class _57 {
    public int TreeDepth(TreeNode root) {
        //如果树为空，返回0，否则就是左右子树深度最大值+1
        return root == null ? 0 : 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }

    class TreeNode{
        TreeNode left;
        TreeNode right;
    }
}
