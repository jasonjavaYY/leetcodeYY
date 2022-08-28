package lc.lc3;

/*
* 验证二叉树的前序序列化
*
* 当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
保证每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。你可以认为输入格式总是有效的,例如不会包含两个连续的逗号。不允许重建树。
示例 1:
输入: preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#"
输出: true
示例 2:
输入: preorder = "1,#"
输出: false
示例 3:
输入: preorder = "9,#,#,1"
输出: false
*
* 我们定义一个概念，叫做槽位。一个槽位可以被看作「当前二叉树中正在等待被节点填充」的那些位置。二叉树的建立也伴随着槽位数量的变化。
* 每当遇到一个节点时：如果遇到了空节点，则要消耗一个槽位；如果遇到了非空节点，则除了消耗一个槽位外，还要再补充两个槽位。
* 我们使用栈来维护槽位的变化。栈中的每个元素，代表了对应节点处剩余槽位的数量，而栈顶元素就对应着下一步可用的槽位数量。
* 当遇到空节点时，仅将栈顶元素减 1；当遇到非空节点时，将栈顶元素减 1 后，再向栈中压入一个 2。无论何时，如果栈顶元素变为 0，就立刻将栈顶弹出。
* 如果把栈中元素看成一个整体，即所有剩余槽位的数量，也能维护槽位的变化。因此只维护一个计数器，代表栈中所有元素之和，其余的操作逻辑均可以保持不变。
* */
//树 非空节点记录值。空节点用#。逗号分隔的序列，验证是否是正确的二叉树前序序列化
public class _331 {
    public boolean isValidSerialization(String preorder) {
        int n = preorder.length(); //获取字符串长度
        int i = 0;
        int slots = 1;//遇到空节点消耗一个槽；非空节点消耗一个槽再补充两个槽
        while (i < n) {//遍历字符串序列
            if (slots == 0) {//如果槽位数是0，返回false
                return false;
            }
            if (preorder.charAt(i) == ',') {//分隔符直接跳过
                i++;
            } else if (preorder.charAt(i) == '#') {//如果是空位，slot++
                slots--;
                i++;
            } else {//否则是数字，读出到下一个,的所有数字
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                slots++; // slots = slots - 1 + 2
            }
        }
        return slots == 0;//遍历完判断槽是否位0
    }
}
