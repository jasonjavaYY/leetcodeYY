package lc.lc4;

/*
* 二叉树的直径
*
* 一棵二叉树，计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
示例 :
给定二叉树

          1
         / \
        2   3
       / \
      4   5
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
注意：两结点之间的路径长度是以它们之间边的数目表示。
*
* 方法一：深度优先搜索
一条路径长度为该路径经过的节点数减一，求直径等效于求路径经过节点数最大值减一。一条路径可看作某个节点为起点，从其左儿子和右儿子向下遍历的路径拼接。
* 如图我们知道路径 [9, 4, 2, 5, 7, 8] 可被看作以 2 为起点，从其左儿子向下遍历的路径 [2, 4, 9] 和从其右儿子向下遍历的路径 [2, 5, 7, 8]
* 拼接得到。假设我们知道对于该节点的左儿子向下遍历经过最多的节点数 L  和其右儿子向下遍历经过最多的节点数 R ，那么以该节点为起点的路径经过节点数
* 的最大值即为 L+R+1 。我们记节点 node 为起点的路径经过节点数的最大值为 d_node，那么二叉树直径就是所有节点 d_node 的最大值减一。
最后的算法流程为：我们定义一个递归函数 depth(node) 计算 d_node，函数返回该节点为根的子树的深度。先递归调用左儿子和右儿子求得它们为根的子树
* 的深度 L 和 R ，则该节点为根的子树的深度即为max(L,R)+1,该节点的 d_node值为L+R+1,递归搜索每个节点并设一个全局变量 ans 记录d_node最大值，
* 最后返回 ans-1 即为树的直径。
* */
public class _543 {
    int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }

    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }
}
