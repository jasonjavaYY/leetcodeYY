package jzoffer;

/*
 * 对称的二叉树
 *
 * 判断一颗二叉树是不是对称的
 * */
//树 判断一颗二叉树是不是对称
public class _29 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    boolean isSymmetrical(TreeNode pRoot) {
        //如果树为空，返回true
        if (pRoot == null)
            return true;
        //否则判断树左右节点是否对称
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        //如果左右节点都是空，返回true
        if (t1 == null && t2 == null)
            return true;
        //如果只有一个为空，返回false
        if (t1 == null || t2 == null)
            return false;
        //能到这里说明两个都不为空，如果值不相等，返回false
        if (t1.val != t2.val)
            return false;
        //递归判断是否对称，因为对称要求最左侧和最右侧相同，所以判断t1.left和t2.right
        //&& t1.right和t2.left
        return isSymmetrical(t1.left, t2.right) && isSymmetrical(t1.right, t2.left);
    }
}
