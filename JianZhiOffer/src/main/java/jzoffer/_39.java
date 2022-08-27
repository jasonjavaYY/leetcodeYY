package jzoffer;
/*
* 二叉搜索树与双向链表
*
* 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
* 要求不能创建任何新的结点，只能调整树中结点指针的指向。
* */
//一棵二叉搜索树，将树转换成排序的双向链表
public class _39 {
    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode Convert(TreeNode root) {
        inOrder(root);
        return head;
    }

    private void inOrder(TreeNode node) {
        if (node == null) //如树为空，直接返回
            return;
        inOrder(node.left); //否则递归左节点
        node.left = pre; //节点的左节点比它小，放在pre
        if (pre != null) //将node设置为pre的右节点
            pre.right = node;
        pre = node; //更新pre指向node
        if (head == null) //初始化头结点
            head = node;
        inOrder(node.right); //递归右节点
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
