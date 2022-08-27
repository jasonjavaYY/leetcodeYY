package lc.lc1;

/*
* 验证二叉搜索树
*
* 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
有效 二叉搜索树定义如下：
节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
*
* 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
* */
//给一个二叉树根节点，判断其是否是有效的二叉搜索树
public class _98 {
    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) { //如果树为空，返回true
            return true;
        }//递归判断左子树
        if (!isValidBST(root.left)) {
            return false;
        } //如果当前节点小于等于中序遍历的前一节点，返回false
        if (root.val <= pre) {
            return false;
        }//更新前一个节点
        pre = root.val;
        //递归判断右子树
        return isValidBST(root.right);
    }
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }
}
