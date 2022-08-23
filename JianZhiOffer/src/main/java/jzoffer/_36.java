package jzoffer;
/*
* 二叉搜索树的后序遍历序列。前序是根左右；中序是左跟右；后序是左右根
*
* 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
* 假设输入的数组的任意两个数字都互不相同。
* 例如，下图是后序遍历序列 1,3,2 所对应的二叉搜索树
* */
public class _36 {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) //如果序列是空返回false
            return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private boolean verify(int[] sequence, int first, int last) {
        //如果只有一个节点，一定是真
        if (last - first <= 1)
            return true;
        //后序遍历最后一个节点一定是根
        int rootVal = sequence[last];
        //cutIndex是左右子树分界点，二叉搜索树左子树一定比根值小，右子树一定比根的值大
        int cutIndex = first;
        while (cutIndex < last && sequence[cutIndex] <= rootVal)
            cutIndex++;
        //找到右子树第一个节点，判断右子树如果有一个值比根小，就不是二叉搜索树，返回false
        for (int i = cutIndex; i < last; i++)
            if (sequence[i] < rootVal)
                return false;
            //如果从根的左右子树满足规则，继续找根节点的下一层的根节点，也就是根节点的左右子节点
        //因为后序遍历是左右根，所以左子树的根一定是cutIndex-1节点，右子树的根是last-1的节点
        //递归判断这两个根节点是否满足条件，要求同时满足，因此用&&
        return verify(sequence, first, cutIndex - 1) && verify(sequence, cutIndex, last - 1);
    }
}
