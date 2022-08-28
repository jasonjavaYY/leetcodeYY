package jzoffer;
/*
* 平衡二叉树
* 平衡二叉树左右子树高度差不超过 1。
*
* */
//树 输入一棵二叉树，判断该树是否是平衡二叉树，左右子树高度差不超1
public class _58 {
     public class TreeNode {
         int val = 0;
         TreeNode left = null;
         TreeNode right = null;
         public TreeNode(int val) {
             this.val = val;
         }
     }
    boolean isBalance = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        lengthOfTree(root);
        return isBalance;
    }
    private int lengthOfTree(TreeNode root) {
        //如果树为空，返回高度0
        if (root == null)
            return 0;
        //求左子树和右子树高度
        int left = lengthOfTree(root.left);
        int right = lengthOfTree(root.right);
        //如果高度差超过1，设置false
        if (Math.abs(left - right) > 1)
            isBalance = false;
        //最后返回左右子树的高度最大值+1
        return Math.max(left, right) + 1;
    }
}
