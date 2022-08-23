package jzoffer;
/*
* 树中两个节点的最低公共祖先
*
* 二叉查找树中，两个节点 p, q 的公共祖先 root 满足 root.val >= p.val && root.val <= q.val。
*
* 普通二叉树，在左右子树中查找是否存在 p 或者 q，如果 p 和 q 分别在两个子树中，那么就说明根节点就是最低公共祖先。
* */
public class _73 {
    //针对二叉查找树
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)  //如果树位空，返回该节点
            return root;
        //如果节点的值比p和q都大，就找该节点的left，如果都小，就找right，否则返回该节点
        if (root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        if (root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //如果节点为p或者q或空，就返回该节点
        if (root == null || root == p || root == q)
            return root;
        //否则继续找该节点的左和右
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        //如果left是空就返回right，right为空就返回left，否则返回root
        return left == null ? right : right == null ? left : root;
    }

    class TreeNode{
        private int val;
        TreeNode left;
        TreeNode right;
    }
}
