package lc.lc3;

import java.util.HashMap;
import java.util.Map;

/*
* 打家劫舍 III
*
* 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。
除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
* 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。给定二叉树 root 。返回 在不触动警报下能够盗取的最高金额 。
*
* 示例 1:
输入: root = [3,2,3,null,3,null,1]
输出: 7
解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
示例 2:
输入: root = [3,4,5,1,3,null,1]
输出: 9
解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
*
* 想着偷父节点就不能偷子节点，偷子节点就不能偷父节点，那不就是一层一层的偷吗？层序遍历，计算隔层的累和，然后取大的就可以了啊。提交发现WA。
* 题目说直接相连的不能一起偷，没有说一定隔层偷，也可以隔两层啊？然后再试以层为单元的去dp，仍是WA。
* 最终发现，其实同一层可能不是一致的（全偷或者全不偷）。层序的思路就是错误的。还是要回到DP。
* 方法一：动态规划
一棵二叉树，树上的每个点都有对应的权值，每个点有两种状态（选中和不选中），问在不能同时选中有父子关系的点的情况下，能选中的点的最大权值和是多少。
我们可以用 f(o) 表示选择 o 节点的情况下，o 节点的子树上被选择的节点的最大权值和；g(o) 表示不选择 o 节点的情况下，o 节点的子树上被选择的节点
* 的最大权值和；l 和 r 代表 o 的左右孩子。当 o 被选中时，o 的左右孩子都不能被选中，故 o 被选中情况下子树上被选中点的最大权值和为 l 和 r 不
* 被选中的最大权值和相加，即 f(o)=g(l)+g(r)。当 o 不被选中时，o 的左右孩子可以被选中，也可以不被选中。对于 o 的某个具体的孩子 x，
* 它对 o 的贡献是 x 被选中和不被选中情况下权值和的较大值。故 g(o)=max{f(l),g(l)}+max{f(r),g(r)}。至此，我们可以用哈希表来存 f 和 g 的
* 函数值，用深度优先搜索的办法后序遍历这棵二叉树，我们就可以得到每一个节点的 f 和 g。根节点的 f 和 g 的最大值就是我们要找的答案。
* */
//动态规划 如果二叉树直接相连节点同一天被劫将报警。给二叉树，返回不触警报能盗取最高金额
public class _337 {
    //f(o)表示选择o节点下，o节点子树被选择的节点最大权值和；g(o)表示不选择o节点下子树被选择的节点最大权值和
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>();
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>();

    public int rob(TreeNode root) {
        dfs(root);//dfs树               //返回f和g在root对应值的较大值
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node) {
        if (node == null) {//如果节点位空直接返回
            return;
        }
        dfs(node.left);//dfs左右节点
        dfs(node.right);
        //节点被选中下子树被选中的最大权值和为 l 和 r 不被选中的最大权值和相加再加当前值，f(o)=g(l)+g(r)+node(o)，默认值0代表空节点
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));
        //节点不被选中，左右孩子可以选也可不选，对o某个孩子x，它对o的贡献是x被选或不被选的较大值。g(o)=max{f(l),g(l)}+max{f(r),g(r)}
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) +
                Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));
    }
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
