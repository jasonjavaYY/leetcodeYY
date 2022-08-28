package lc.lc4;

/*
* 路径总和 III
*
* 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的路径数。
路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须向下。
示例 1：
输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
输出：3
解释：和等于 8 的路径有 3 条，如图所示。
示例 2：
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：3
*
* 方法一：深度优先搜索
访问每一个节点 node，检测以 node 为起始节点且向下延深的路径有多少种。递归遍历每一个节点的所有可能的路径，然后将这些路径数目加起来即为结果。
首先定义 rootSum(p,val) 表示以节点 p 为起点向下且满足路径总和为 val 的路径数目。对二叉树上每个节点 p 求出 rootSum(p,targetSum)，
* 然后对这些路径数目求和即为返回结果。我们对节点 p 求 rootSum(p,targetSum) 时，以当前节点 p 为目标路径的起点递归向下进行搜索。
* 假设当前的节点 p 的值为 val，我们对左子树和右子树进行递归搜索，对节点 p 的左孩子节点 pl求出 rootSum(pl,targetSum−val)，
* 以及对右孩子节点 pr求出 rootSum(pr,targetSum−val)。节点 p 的 rootSum(p,targetSum) 即等于 rootSum(pl,targetSum−val)
* 与 rootSum(pr,targetSum−val) 之和，同时还要判断当前节点 p 的值是否刚好等于 targetSum。
采用递归遍历二叉树的每个节点 p，对节点 p 求 rootSum(p,val)，然后将每个节点所有求的值进行相加求和返回。
* */
//树 给二叉树和整数targetSum，求二叉树里节点值和等于targetSum的路径数。不需根开始也不需叶结束，路径必须向下
public class _437 {
    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {//如果树为空，返回0
            return 0;
        }
        int ret = rootSum(root, targetSum);//从根节点开始找看有多少路径
        ret += pathSum(root.left, targetSum);//再加上从左右节点找到的路径
        ret += pathSum(root.right, targetSum);
        return ret;//最后返回总路径
    }
    //从root开始找target一共有多少路径
    public int rootSum(TreeNode root, int targetSum) {
        int ret = 0;//记录结果，初始值为0
        if (root == null) {//如果节点为空，返回0
            return 0;
        }
        int val = root.val;//获取节点值，如果值为target，ret++
        if (val == targetSum) {
            ret++;
        }//遍历左右子树，寻找target-val
        ret += rootSum(root.left, targetSum - val);
        ret += rootSum(root.right, targetSum - val);
        return ret;//返回路径数
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
