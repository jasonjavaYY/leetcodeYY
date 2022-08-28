package lc.lc4;

/*
* 把二叉搜索树转换为累加树
*
* 给出二叉搜索树的根节点，该树的节点值各不相同，请你将其转换为累加树，使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
二叉搜索树满足下列约束条件：节点的左子树仅包含键小于节点键的节点。节点的右子树仅包含键大于节点键的节点。左右子树也必须是二叉搜索树。
*
* 示例 1：
输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
示例 2：
输入：root = [0,null,1]
输出：[1,null,1]
示例 3：
输入：root = [1,0,2]
输出：[3,3,2]
*
* 二叉搜索树的中序遍历是一个单调递增的有序序列。如果我们反序地中序遍历该二叉搜索树，即可得到一个单调递减的有序序列。
方法一：反序中序遍历
要求我们将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。这样我们只需要反序中序遍历该二叉搜索树，记录过程中的节点值之和，
* 并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。
* */
//树 给二叉搜索树，节点值各不相同，转换为累加树使每个节点新值等于原树中大于等于node.val的值之和
public class _538 {
    int sum = 0;//记录节点值的和
    //反序的中序遍历
    public TreeNode convertBST(TreeNode root) {
        if (root != null) {//
            //右根左
            convertBST(root.right);//遍历右节点
            sum += root.val;//值加入sum
            root.val = sum;//更新节点值为sum
            convertBST(root.left);//遍历左节点
        }
        return root;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
