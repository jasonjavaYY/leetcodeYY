package jzoffer;
/*
* 二叉树的下一个结点
*
* 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
* 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
*
* ① 如果一个节点的右子树不为空，那么该节点的下一个节点是右子树的最左节点
* ② 否则，向上找第一个左链接指向的树包含该节点的祖先节点
* */
public class _6 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {//如果右子树不为空
            //获取右节点，然后不断找这个节点的左节点，直至为null，返回
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {//如果右子树为空
            while (pNode.next != null) { //不断想上找next不为空的节点
                TreeLinkNode parent = pNode.next;
                //如果该节点的left是pNode就返回，否则继续找next
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }

    static class TreeLinkNode{
        private int val;
        private TreeLinkNode left;
        private TreeLinkNode right;
        private TreeLinkNode next;
    }
}
