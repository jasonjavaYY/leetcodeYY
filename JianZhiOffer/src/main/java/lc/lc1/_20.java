package lc.lc1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
* 有效的括号
*
* 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。有效字符串需满足：
* 左括号必须用相同类型的右括号闭合。左括号必须以正确的顺序闭合。
示例 1：
输入：s = "()"
输出：true
示例 2：
输入：s = "()[]{}"
输出：true
示例 3：
输入：s = "(]"
输出：false
示例 4：
输入：s = "([)]"
输出：false
示例 5：
输入：s = "{[]}"
输出：true
*
* 解题思路：
若遇到左括号入栈，遇到右括号时将对应栈顶左括号出栈，则遍历完所有括号后 stack 仍然为空；
建立哈希表 dic 构建左右括号对应关系：key 左括号，value 右括号；
* 建立栈 stack，遍历字符串 s 并按照算法流程一一判断。
如果 c 是左括号，则入栈 push；否则通过哈希表判断括号对应关系，若 stack 栈顶出栈括号stack.pop与当前遍历括号 c 不对应，则提前返回 false。
解决边界问题：
栈 stack 为空： 此时 stack.pop 操作会报错；因此，给 stack 赋初值 ? ，并在哈希表 dic 中建立 key: '?'，value:'?
* 的对应关系予以配合。此时当 stack 为空且 c 为右括号时，可以正常提前返回 false；字符串 s 以左括号结尾：
* 此情况下可以正常遍历完整个 s，但 stack 中遗留未出栈的左括号；因此，最后需返回 len(stack) == 1，以判断是否是有效的括号组合。
复杂度分析
时间复杂度 O(N)：正确的括号组合需要遍历 1 遍 s；
空间复杂度 O(N)：哈希表和栈使用线性的空间大小。
* */
//栈 给一个只包括 '('，')'，'{'，'}'，'['，']' 字符串s ，判断括号是否有效
public class _20 {
    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');put('[', ']');put('(', ')');put('?', '?');
    }}; //保存各个括号对应关系

    public boolean isValid(String s) {
        //如果字符串不为空但第0个元素不是左括号，直接返回false
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        //构建一个栈，放入一个"?"
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for (Character c : s.toCharArray()) { //遍历字符串每个字符
            //如果是左括号，就入栈
            if (map.containsKey(c)) stack.addLast(c);
            //如果不是左括号，就移除栈顶的左括号，再map中找到对应的右括号，如果c不是对应右括号，返回false
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1; //如果遍历完了，栈大小为1，即只剩下一个?，就返回true
    }
}
