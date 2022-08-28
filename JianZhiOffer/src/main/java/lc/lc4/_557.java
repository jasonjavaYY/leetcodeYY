package lc.lc4;

/*
* 反转字符串中的单词 III
*
* 给定一个字符串 s ，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
示例 1：
输入：s = "Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"
示例 2:
输入： s = "God Ding"
输出："doG gniD"
*
* 方法一：使用额外空间
开辟一个新字符串。从头到尾遍历原字符串，直到找到空格，此时找到一个单词，并得到单词起止位置。根据单词的起止位置，将单词逆序放到新字符串中。
* */
//字符串 一个字符串s，反转每个单词字符顺序，保留空格和单词初始顺序
public class _557 {
    public String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();//构造stringbuffer(sb)
        int length = s.length();//计算初始字符串长度
        int i = 0;
        while (i < length) {//遍历字符串
            int start = i; //标记单词起始位置
            while (i < length && s.charAt(i) != ' ') {
                i++;//让i递增到单词后的空格位置
            }//指针从start到i就是一个单词的所有下标
            for (int p = start; p < i; p++) {
                //逆序将单词每个字符拼接到ret
                ret.append(s.charAt(start + i - 1 - p));
            }//将所有空格顺序拼接ret
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();//返回ret
    }
}
