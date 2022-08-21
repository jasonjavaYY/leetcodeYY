package lc.lc1;

/*
* 解码方法
*
* 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：'A' -> "1"  'B' -> "2"  ...  'Z' -> "26"
要解码已编码的消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）。例如，"11106" 可以映射为：
"AAJF" ，将消息分组为 (1 1 10 6)
"KJF" ，将消息分组为 (11 10 6)
注意，消息不能分组为  (1 11 06) ，因为 "06" 不能映射为 "F" ，这是由于 "6" 和 "06" 在映射中并不等价。
给你一个只含数字的 非空 字符串 s ，请计算并返回解码方法的总数 。题目数据保证答案肯定是一个 32 位 的整数。
示例 1：
输入：s = "12"
输出：2
解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
示例 2：
输入：s = "226"
输出：3
解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
示例 3：
输入：s = "0"
输出：0
解释：没有字符映射到以 0 开头的数字。
含有 0 的有效映射是 'J' -> "10" 和 'T'-> "20" 。
由于没有字符，因此没有有效的方法对此进行解码，因为所有数字都需要映射。
*
* 方法一：动态规划
对于给定的字符串 s，设它的长度为 n，其中的字符从左到右依次为s[1],s[2],⋯,s[n]。我们可以使用动态规划的方法计算出字符串 s 的解码方法数。
设 f_i表示字符串 s 的前 i 个字符s[1..i] 的解码方法数。在进行状态转移时，我们可以考虑最后一次解码使用了 s 中的哪些字符，会有下面两种情况：
第一种情况是我们使用了一个字符，即 s[i] 进行解码，那么只要 s[i]!=0，它就可以被解码成 A∼I 中的某个字母。由于前 i−1 个字符的解码方法数为 f_{i-1},
因此我们可以写出状态转移方程：f_i = f_{i-1}, 其中 s[i]!=0
第二种情况是我们使用了两个字符，即 s[i−1] 和 s[i] 进行编码。s[i−1] 不能等于 0，并且 s[i−1] 和s[i] 组成的整数必须小于等于 26，
* 这样它们就可以被解码成 J∼Z 中的某个字母。由于剩余的前 i−2 个字符的解码方法数为 f_{i-2},因此我们可以写出状态转移方程：
f_i = f_{i-2},其中 s[i−1]!=0 并且 10<s[i−1]+s[i]≤26
只有当 i>1 时才能进行转移，否则 s[i−1]不存在。将上面两种状态转移方程在对应的条件满足时进行累加，即可得到 f_i值。动态规划完成后，最终的答案即为 f_n
动态规划的边界条件为：f_0 = 1,即空字符串可以有 1 种解码方法，解码出一个空字符串。
* */
public class _91 {
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
