package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 二叉树的中序遍历
*
* 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
示例 1：
输入：root = [1,null,2,3]
输出：[1,3,2]
*
* 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，
* 直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
定义 inorder(root) 表示当前遍历到 root 节点的答案，那么按照定义，我们只要递归调用 inorder(root.left) 来遍历 root 节点的左子树，
* 然后将 root 节点的值加入答案，再递归调用inorder(root.right) 来遍历 root 节点的右子树即可，递归终止的条件为碰到空节点。
* */
//树 给一个二叉树根节点 ，返回它的中序遍历
public class _94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) { //如果树为空返回
            return;
        }
        inorder(root.left, res); //递归左节点
        res.add(root.val); //值放入res
        inorder(root.right, res); //递归右节点
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
