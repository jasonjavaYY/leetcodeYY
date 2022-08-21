package jzoffer;

/*
*替换空格
*
* 将一个字符串中的空格替换成 "%20"。

在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。
* 因为一个空格要替换成三个字符（%20），因此当遍历到一个空格时，需要在尾部填充两个任意字符。
令 P1 指向字符串原来的末尾位置，P2 指向字符串现在的末尾位置。
* P1 和 P2 从后向前遍历，当 P1 遍历到一个空格时，就需要令P2指向的位置依次填充 02%，否则就填充上P1指向字符值。
* */
public class _3 {
    public String replaceSpace(StringBuffer str) {
        int P1 = str.length() - 1;
        for (int i = 0; i <= P1; i++)
            if (str.charAt(i) == ' ')
                str.append("  ");

        int P2 = str.length() - 1;
        while (P1 >= 0 && P2 > P1) {
            char c = str.charAt(P1--);
            if (c == ' ') {
                str.setCharAt(P2--, '0');
                str.setCharAt(P2--, '2');
                str.setCharAt(P2--, '%');
            } else {
                str.setCharAt(P2--, c);
            }
        }
        return str.toString();
    }
}
