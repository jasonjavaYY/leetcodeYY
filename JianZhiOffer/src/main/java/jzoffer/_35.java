package jzoffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
* 按之字形顺序打印二叉树
*
* 请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
* 第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
*
* 和前面一题基本一样，只是增加一个翻转标记，每打印一层就翻转一次
* */
public class _35 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>(); //存放结果数组
        Queue<TreeNode> queue = new LinkedList<>(); //创建队列
        queue.add(pRoot); //将根节点加入队列
        boolean reverse = false;  //标记是否需要翻转该层
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();//构造内部数组存放每层元素，为了区分何时换行
            int cnt = queue.size();//获取队列大小，即当前层元素数
            while (cnt-- > 0) {//依次弹出每个元素
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                //如果不为空，将元素值加入内部数组，左右节点入队
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            //到这里是打印完了一层，如果是翻转层，就将list翻转，如果不允许使用Collections
            //也可以在上面的while增加判断，如果是reverse，就queue.add(right) queue.add(left)
            if (reverse) //如果需要翻转就翻转内部数组
                Collections.reverse(list);
            reverse = !reverse; //更新翻转标记
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
