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
//n叉树返回其节点值前序遍历
public class _589 {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();//构造结果数组
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {//如果根为空直接返回
            return;
        }
        res.add(root.val);//否则将根节点加入res
        for (Node ch : root.children) {//递归所有子节点
            helper(ch, res);
        }
    }

    class Node {
        int val;
        List<Node> children;
    }
}
