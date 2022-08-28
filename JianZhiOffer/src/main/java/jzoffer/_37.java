package jzoffer;

import java.util.ArrayList;

/*
 * 二叉树中和为某一值的路径
 *
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * 下图的二叉树有两条和为 22 的路径：10, 5, 7 和 10, 12
 * */
//树 输入一颗二叉树和整数，打印二叉树中结点值和为输入整数的所有路径。路径定义为从根结点开始一直到叶结点经过的结点
public class _37 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode left = new TreeNode(5);
        TreeNode right = new TreeNode(12);
        TreeNode leftLeft = new TreeNode(4);
        TreeNode leftRight = new TreeNode(7);
        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        System.out.println(FindPath(root, 22));

    }
    private static ArrayList<ArrayList<Integer>> ret = new ArrayList<>(); //存放结果数组
    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        backtracking(root, target, new ArrayList<Integer>());
        return ret;
    }

    private static void backtracking(TreeNode node, int target, ArrayList<Integer> path) {
        //如果二叉树为空，直接返回空
        if (node == null) return;
        //将根节点值加入路径，目标值减掉根节点值
        path.add(node.val);
        target -= node.val;
        //如果目标值为0并且当前节点是叶子节点，说明找到了一条路径，将path加入ret中
        if (target == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
        } else {  //否则回溯left和right
            backtracking(node.left, target, path);
            backtracking(node.right, target, path);
        }
        //只有当上面的else分支两个方法都失败才能到这里，
        //说明当时的node的左右子树都已经没法找到所需的路径，需要回退
        //因此需要把node的值从path中移除，向node的上一层另一条分支找
        path.remove(path.size() - 1);
    }

    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
