package jzoffer;

/*
 * 二叉树的镜像
 *
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 * */
public class _28 {
    //
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public void Mirror(TreeNode root) {
        //如果要镜像的节点为空，直接退出
        if (root == null)
            return;
        //交换根节点的左右子节点，然后继续镜像根的左右节点
        swap(root);
        Mirror(root.left);
        Mirror(root.right);
    }

    //ok  交换某个根节点的左右节点
    private void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }
}
