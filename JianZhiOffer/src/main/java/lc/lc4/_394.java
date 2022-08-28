package lc.lc4;

import java.util.Collections;
import java.util.LinkedList;

/*
* 字符串解码
*
* 给定一个经过编码的字符串，返回它解码后的字符串。
编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
输入字符串中没有额外空格，且输入的方括号总是符合格式要求的。
你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
示例 1：
输入：s = "3[a]2[bc]"
输出："aaabcbc"
示例 2：
输入：s = "3[a2[c]]"
输出："accaccacc"
示例 3：
输入：s = "2[abc]3[cd]ef"
输出："abcabccdcdcdef"
示例 4：
输入：s = "abc3[cd]xyz"
输出："abccdcdcdxyz"
*
* 方法一：栈操作
本题中可能出现括号嵌套的情况，比如 2[a2[bc]]，这种情况下我们可以先转化成 2[abcbc]，在转化成 abcbcabcbc。
* 我们可以把字母、数字和括号看成是独立的 TOKEN，并用栈来维护这些 TOKEN。具体的做法是，遍历这个栈：
如果当前字符为数，解析出一个数字（连续的多个数位）并进栈,如果当前字符为字母或左括号，直接进栈,如果当前的字符为右括号，开始出栈，一直到左括号出栈，
* 出栈序列反转后拼接成一个字符串，此时取出栈顶的数字，就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈
重复如上操作，最终将栈中的元素按照从栈底到栈顶的顺序拼接起来，就得到了答案。注意：这里可以用不定长数组来模拟栈操作，方便从栈底向栈顶遍历。
* */
//栈 返回解码后的字符串。编码规则:k[str]表示str重复k次。字符串没有空格，原始数据不含数字，数字只表示重复次数k
public class _394 {
    int ptr;//全局维护指针
    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();//构造栈维护token
        ptr = 0;//指针初始指向字符串头
        while (ptr < s.length()) {//遍历传入字符串
            char cur = s.charAt(ptr);//获取字符
            if (Character.isDigit(cur)) {//如果字符是数字，将连续数字都取出
                String digits = getDigits(s);
                stk.addLast(digits);//加入最后
            } else if (Character.isLetter(cur) || cur == '[') {
                //如果是字母或[，加入最后
                stk.addLast(String.valueOf(s.charAt(ptr++)));
            } else {//否则一定是]
                ++ptr;//跳过右括号
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {//将栈内元素非[全部出栈加入sub
                    sub.addLast(stk.removeLast());
                }//出栈序列翻转
                Collections.reverse(sub);
                stk.removeLast();// 左括号出栈
                // 此时栈顶为当前sub对应字符串应出现次数，出栈并解析为整数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);//将sub构造成字符串
                while (repTime-- > 0) {//重复构造repTime次sub
                    t.append(o);//形成结果t
                }
                stk.addLast(t.toString());//最后将构造好的字符串t入栈
            }
        }
        return getString(stk);//拼接链表内所有字符串返回
    }
    //从s中提取数字
    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            //从ptr开始取出所有连续数字加入buffer返回
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }
    //拼接链表内所有字符串
    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}
