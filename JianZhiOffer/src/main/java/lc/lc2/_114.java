package lc.lc2;

/*
* 二叉树展开为链表
*
* 给你二叉树的根结点 root ，请你将它展开为一个单链表：展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，
* 而左子指针始终为 null 。展开后的单链表应该与二叉树 先序遍历 顺序相同。
*
* 解法一
可以发现展开的顺序其实就是二叉树的先序遍历。我们需要两步完成这道题。将左子树插入到右子树的地方，将原来的右子树接到左子树的最右边节点
考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
*
    1
   / \
  2   5
 / \   \
3   4   6

//将 1 的左子树插入到右子树的地方
    1
     \
      2         5
     / \         \
    3   4         6
//将原来的右子树接到左子树的最右边节点
    1
     \
      2
     / \
    3   4
         \
          5
           \
            6

 //将 2 的左子树插入到右子树的地方
    1
     \
      2
       \
        3       4
                 \
                  5
                   \
                    6

 //将原来的右子树接到左子树的最右边节点
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6

  ......
* */
//树 二叉树按先序遍历顺序展为单链表：单链表同样用TreeNode，right指向链表下一结点，左子指针为null。
public class _114 {
    //左子树插入到右子树的地方，将原来的右子树接到左子树最右边节点
    public void flatten(TreeNode root) {
        while (root != null) {
            //左子树为 null，直接考虑下一个节点
            if (root.left == null) {
                root = root.right;
            } else {
                // 找左子树最右边的节点pre
                TreeNode pre = root.left;
                while (pre.right != null) {
                    pre = pre.right;
                }
                //将原来的右子树接到左子树的最右边节点pre
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }
    }

    class TreeNode {
        TreeNode left;
        TreeNode right;
    }
}
