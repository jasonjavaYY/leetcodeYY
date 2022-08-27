package lc.lc1;

import java.util.LinkedList;
import java.util.List;

/*
* 不同的二叉搜索树 II
*
* 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按任意顺序返回答案。
示例 1：
输入：n = 3
输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
示例 2：
输入：n = 1
输出：[[1]]
*
* 二叉搜索树关键的性质是根节点的值大于左子树所有节点的值，小于右子树所有节点的值，且左子树和右子树也同样为二叉搜索树。
* 因此在生成所有可行的二叉搜索树的时候，假设当前序列长度为 n，如果我们枚举根节点的值为 i，那么根据二叉搜索树的性质我们可以知道
* 左子树的节点值的集合为 [1…i−1]，右子树的节点值的集合为 [i+1…n]。而左子树和右子树的生成相较于原问题是一个序列长度缩小的子问题，
* 因此可以用回溯的方法来解决这道题目，定义 generateTrees(start, end) 函数表示当前值的集合为 [start,end]，
* 返回序列 [start,end] 生成的所有可行的二叉搜索树。按照上文的思路，我们考虑枚举 [start,end] 中的值 i 为当前二叉搜索树的根，
* 那么序列划分为了 [start,i−1] 和 [i+1,end] 两部分。我们递归调用这两部分，即 generateTrees(start, i - 1) 和
* generateTrees(i + 1, end)，获得所有可行的左子树和可行的右子树，那么最后一步我们只要从可行左子树集合中选一棵，
* 再从可行右子树集合中选一棵拼接到根节点上，并将生成的二叉搜索树放入答案数组即可。递归的入口即为 generateTrees(1, n)，
* 出口为当start>end 的时候，当前二叉搜索树为空，返回空节点即可。

* */
//给一个整数 n ，生成所有n个节点组成且节点值从1到n的不同二叉搜索树
public class _95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) { //如果n=0，返回空链表
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<TreeNode>(); //保存所有结果
        if (start > end) { //如果start超过end，结果加入一个null返回
            allTrees.add(null);
            return allTrees;
        }
        // 枚举可行根节点，i从start到end
        for (int i = start; i <= end; i++) {
            // 获得所有可行的左子树集合
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 获得所有可行的右子树集合
            List<TreeNode> rightTrees = generateTrees(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，拼接到根节点上
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    //遍历左右子树，构造节点，节点的left是左子树节点，right是右子树节点
                    TreeNode currTree = new TreeNode(i);
                    currTree.left = left;
                    currTree.right = right;
                    allTrees.add(currTree); //将当前节点加入结果集
                }
            }
        }
        return allTrees; //返回结果集
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
