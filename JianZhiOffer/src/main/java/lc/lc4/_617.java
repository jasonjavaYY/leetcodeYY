package lc.lc4;

/*
* 合并二叉树
*
* 给你两棵二叉树：root1 和 root2 。将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠。你需要将这两棵树合并成一棵新二叉树。
* 合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。
返回合并后的二叉树。
注意: 合并过程必须从两个树的根节点开始。
示例 1：
输入：root1 = [1,3,2,5], root2 = [2,1,3,null,4,null,7]
输出：[3,4,5,5,4,null,7]
示例 2：
输入：root1 = [1], root2 = [1,2]
输出：[2,2]
*
* 方法一：深度优先搜索
深度优先搜索合并两个二叉树。从根节点开始同时遍历两个二叉树，将对应的节点合并。两个二叉树对应节点可能存在以下三种情况，每种情况用不同合并方式。
如果两个树节点都为空，则合并后对应节点也为空；如果只有一个为空，合并后对应节点为非空节点；如果节点都不为空，合并后对应节点值为对应节点值之和
对一个节点进行合并之后，还要对该节点的左右子树分别进行合并。这是一个递归的过程。
* */
public class _617 {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
