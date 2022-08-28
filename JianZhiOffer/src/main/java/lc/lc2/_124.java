package lc.lc2;

/*
* 二叉树中的最大路径和
*
* 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
* 该路径 至少包含一个 节点，且不一定经过根节点。路径和是路径中各节点值的总和。给你一个二叉树的根节点 root ，返回最大路径和。
*
* 示例 1：
输入：root = [1,2,3]
输出：6
解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
*
示例 2：
输入：root = [-10,9,20,null,null,15,7]
输出：42
解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
*
* 方法一：递归
考虑实现一个简化的函数 maxGain(node)，该函数计算二叉树中的一个节点的最大贡献值，就是在以该节点为根节点的子树中寻找以该节点为起点的一条路径，
使得该路径上的节点值之和最大。该函数的计算如下:空节点的最大贡献值等于 0。非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和
（对于叶节点而言，最大贡献值等于节点值）。
例如，考虑如下二叉树。
   -10
   / \
  9  20
    /  \
   15   7
叶节点 9、15、7 的最大贡献值分别为 9、15、7。得到叶节点的最大贡献值之后，再计算非叶节点的最大贡献值。节点 20 的最大贡献值等于 20+max(15,7)=35，
节点 −10 的最大贡献值等于 −10+max(9,35)=25。上述计算过程是递归的过程，因此，对根节点调用函数 maxGain，即可得到每个节点的最大贡献值。
根据函数 maxGain 得到每个节点的最大贡献值之后，如何得到二叉树的最大路径和？对于二叉树中的一个节点，该节点的最大路径和取决于该节点的值
与该节点的左右子节点的最大贡献值，如果子节点最大贡献值为正，则计入该节点的最大路径和，否则不计入。维护全局变量 maxSum 存储最大路径和，
在递归过程中更新 maxSum 的值，最后得到的 maxSum 的值即为二叉树中的最大路径和。
* */
//路径为从树中任意节点出发沿父-子连接到任意节点的序列。路径至少含一节点不一定过根。返回二叉树最大路径和
public class _124 {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) { //如果树为空，返回0
            return 0;
        }
        // 递归计算左右子节点的最大贡献值，只有在贡献值大于 0 时才选取
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);
        // 节点的最大路径和等于该节点值与左右子节点的最大贡献值之和
        int priceNewpath = node.val + leftGain + rightGain;
        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);
        //返回节点最大贡献值，是节点值+左右中的较大值，因为只能取左右中一条
        return node.val + Math.max(leftGain, rightGain);
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
