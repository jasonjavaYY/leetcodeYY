package jzoffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
/*
* 把二叉树打印成多行
*
* 和上题几乎一样
*
* 用一个List<List>存储，每一个内层list存储一行
* */
//树 二叉树打印成多行，和上题几乎一样
public class _34 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>(); //存放结果数组
        Queue<TreeNode> queue = new LinkedList<>(); //辅助队列
        queue.add(pRoot); //根节点加入队列
        while (!queue.isEmpty()) { //循环判断队列是否不为空
            ArrayList<Integer> list = new ArrayList<>(); //构造内部数组存放每层元素，为了区分何时换行
            int cnt = queue.size(); //获取队列大小，即当前层元素数
            while (cnt-- > 0) { //依次弹出每个元素
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                //如果不为空，将元素值加入内部数组，左右节点入队
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            //当前层被添加完，将非空内部数组加入ret中
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int val) {
            this.val = val;
        }
    }
}
