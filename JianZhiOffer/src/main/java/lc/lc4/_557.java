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
public class _557 {
    public String reverseWords(String s) {
        StringBuffer ret = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            int start = i;
            while (i < length && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ret.append(s.charAt(start + i - 1 - p));
            }
            while (i < length && s.charAt(i) == ' ') {
                i++;
                ret.append(' ');
            }
        }
        return ret.toString();
    }

}
