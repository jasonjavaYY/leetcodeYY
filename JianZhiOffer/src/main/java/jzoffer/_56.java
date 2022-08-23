package jzoffer;
/*
* 二叉查找树的第 K 个结点
*
* 利用二叉查找树中序遍历有序的特点。
* */
public class _56 {
    private TreeNode ret;
    private int cnt = 0;

    public TreeNode KthNode(TreeNode pRoot, int k) {
        inOrder(pRoot, k); //中序遍历
        return ret;
    }

    private void inOrder(TreeNode root, int k) {
        if (root == null || cnt >= k) //如果根为空或者cnt到了k就返回
            return;
        //左根右顺序遍历，每遍历一个节点，就cnt++
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) //找到第k个节点，就把该节点赋值给ret
            ret = root;
        inOrder(root.right, k);
    }
    class TreeNode{
        TreeNode left;
        TreeNode right;
    }
}
