package lc.lc1;

/*
* 外观数列
*
* 给定一个正整数 n ，输出外观数列的第 n 项。「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
* 你可以将其视作是由递归公式定义的数字字符串序列：
countAndSay(1) = "1"
countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
前五项如下：
1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1
描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
要 描述 一个数字字符串，首先要将字符串分割为 最小 数量的组，每个组都由连续的最多 相同字符组成。然后对于每个组，先描述字符的数量，
* 然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。
*
* 算法
题目中给定的递归公式定义的数字字符串序列如下：countAndSay(1) = "1"；countAndSay(n) 是对countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
我们定义字符串Si表示countAndSay(i)，我们如果要求得 Sn，则我们需先求出 Sn−1，然后按照上述描述的方法生成，即从左到右依次扫描字符串Sn−1中连续
* 相同的字符的最大数目，然后将字符的统计数目转化为数字字符串再连接对应的字符。我们已知S1为 "1"，按照上述方法依次生成 S_{2},S_{3},S_{n-1},S_{n}即可。
* */
public class _38 {
    public String countAndSay(int n) {
        String str = "1";
        for (int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;

            while (pos < str.length()) {
                while (pos < str.length() && str.charAt(pos) == str.charAt(start)) {
                    pos++;
                }
                sb.append(Integer.toString(pos - start)).append(str.charAt(start));
                start = pos;
            }
            str = sb.toString();
        }

        return str;
    }
}
