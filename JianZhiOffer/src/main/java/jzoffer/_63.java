package jzoffer;
/*
* 左旋转字符串
*
Input:
S="abcXYZdef"
K=3

Output:
"XYZdefabc"
*
* 先将 "abc" 和 "XYZdef" 分别翻转，得到 "cbafedZYX"，然后再把整个字符串翻转得到 "XYZdefabc"。
* 思想根前一道题一样
* */
//字符串 把字符串前K位放到最后，例如S="abcXYZdef"，K=3；输出"XYZdefabc"
public class _63 {
    public String LeftRotateString(String str, int n) {
        //如果n超出了字符串长度，直接返回原字符串
        if (n >= str.length())
            return str;
        char[] chars = str.toCharArray(); //字符串转换为字符数组
        reverse(chars, 0, n - 1);  //翻转前K个字符的单词
        reverse(chars, n, chars.length - 1); //翻转后面字符的单词
        reverse(chars, 0, chars.length - 1); //整体翻转
        return new String(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j)
            swap(chars, i++, j--);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}
