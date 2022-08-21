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
public class _34 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            //说明当前层被添加完了，将list加入ret中
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
