package lc.lc1;

/*
* 实现 strStr()
*
* 实现 strStr() 函数。
给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回-1 。
当 needle 是空字符串时，应当返回 0 。
示例 1：
输入：haystack = "hello", needle = "ll"
输出：2
示例 2：
输入：haystack = "aaaaa", needle = "bba"
输出：-1
*
* 朴素解法
直观的解法的是：枚举原串 s 中的每个字符作为「发起点」，每次从原串的「发起点」和匹配串的「首位」开始尝试匹配：
匹配成功：返回本次匹配的原串「发起点」。匹配失败：枚举原串的下一个「发起点」，重新尝试匹配。
* */
public class _28 {
    public int strStr(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }
}
