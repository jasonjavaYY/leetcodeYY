package lc.lc4;

import java.util.ArrayList;
import java.util.List;

/*
* N 叉树的前序遍历
*
* 给定一个 n 叉树的根节点 root ，返回其节点值的前序遍历 。
*
* 方法一：递归
递归思路比较简单，N 叉树前序遍历与二叉树前序遍历的方法基本一致，递归时先访问根节点，然后依次递归访问每个孩子节点即可。
* */
public class _589 {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node ch : root.children) {
            helper(ch, res);
        }
    }

    class Node {
        int val;
        List<Node> children;
    }
}
