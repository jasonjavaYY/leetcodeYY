package jzoffer;
/*
* 树的子结构
*
* 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
* */
public class _27 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) { //如果有一颗树为空，返回false
            return false;
        } //返回root1是否是root2的子结构或者递归调用root1的left和right对root2判断
        return isSubtree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }
    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) { //如果root2是空，返回true
            return true;
        }
        if (root1 == null) { //如果root1是空，返回false
            return false;
        }
        if (root1.val == root2.val) { //如果节点值相等，继续判断root1的left和root2的left，以及right
            return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
        } else { //如果值不相等，返回false
            return false;
        }
    }
}
