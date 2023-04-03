package lc.lc3;

/*
 * 翻转二叉树
 * */
public class _226 {
    //同jzoffer的_28

    public TreeNode invertTree(TreeNode root) {
        //如果要镜像的节点为空，直接退出
        if (root == null)
            return null;
        //交换根节点的左右子节点，然后继续镜像根的左右节点
        swap(root);
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    private void swap(TreeNode root) {
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
