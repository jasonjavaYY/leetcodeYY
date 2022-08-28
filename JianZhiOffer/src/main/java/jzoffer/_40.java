package jzoffer;
/*
* 序列化二叉树
*
* 请实现两个函数，分别用来序列化和反序列化二叉树。
* */
//树 两个函数分别序列化和反序列化二叉树
public class _40 {
    private String deserializeStr;

    public String Serialize(TreeNode root) {
        //如果是空，直接返回#
        if (root == null)
            return "#";
        //否则返回值用空格分割，按照根左右顺序
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    public TreeNode Deserialize(String str) {
        //deserializeStr用于迭代不断变化的剩余需要反序列化的字符串
        deserializeStr = str;
        return Deserialize();
    }

    private TreeNode Deserialize() {
        //如果需要反序列化的字符串长度为0，说明反序列化完毕，返回
        if (deserializeStr.length() == 0)
            return null;
        //找到节点之间的分隔符位置
        int index = deserializeStr.indexOf(" ");
        //从deserializeStr中拿出node值，如果index是-1就代表整个deserializeStr是节点值
        String node = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        //更新取出当前node之后的deserializeStr
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        //如果node是#说明是空树，返回空
        if (node.equals("#"))
            return null;
        //转换node为整形值，构造解析出来的值的节点
        int val = Integer.valueOf(node);
        TreeNode t = new TreeNode(val);
        //继续反序列化左右节点
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
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
