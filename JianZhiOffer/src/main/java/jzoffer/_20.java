package jzoffer;
/*
* 正则表达式匹配
*
* 请实现一个函数用来匹配包括 '.' 和 '*' 的正则表达式。
* 模式中的字符 '.' 表示任意一个字符，而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。
*
* 匹配是指字符串的所有字符匹配整个模式。例如，字符串 "aaa" 与模式 "a.a" 和 "ab*ac*a" 匹配，但是与 "aa.a" 和 "ab*a" 不匹配。
* */
//匹配包括 '.' 和 '*' 正则表达式，'.' 表示任一个字符，'*' 表示它前面字符可出现任意次（含0次）
public class _20 {
    //str是目标字符串，pattern是正则字符串
    public boolean match(char[] str, char[] pattern) {
        //获取目标字符串长度m和正则字符串长度n
        int m = str.length, n = pattern.length;
        //构造动态规划数组，长度分别是m+1和n+1，默认都是false
        //dp[i][j]代表长度i的字符串和长度j的模式串是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; //两个字符串都为空肯定匹配，因此00元素为true
        for (int i = 1; i <= n; i++) //如果目标串为空，模式串有长度
            //如果模式串i - 1字符为*，则模式串i和i-2相同，因为*前面字符可以出现0次
            if (pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                //两层循环遍历，如果目标串i和模式串j字符相同，则dp[i][j] = dp[i - 1][j - 1]
                //如果模式串j字符(pattern[j - 1])为.，能匹配任意单字符，dp[i][j] 也= dp[i - 1][j - 1]
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                //如果模式串j字符是*，要分情况
                else if (pattern[j - 1] == '*')
                    //如果模式串j-1字符和目标串i字符相同或者模式串j-1字符是.(也代表相同)
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        //则dp[i][j] |= dp[i][j - 1]，即a*看做a
                        //dp[i][j] |= dp[i - 1][j]，即a*看做aa
                        //dp[i][j] |= dp[i][j - 2]，即a*看做空
                        dp[i][j] |= dp[i][j - 1]; // a* counts as single a
                        dp[i][j] |= dp[i - 1][j]; // a* counts as multiple a
                        dp[i][j] |= dp[i][j - 2]; // a* counts as empty
                    } else //如果模式串j-1字符和目标串i字符不相同，只能把a*看做空
                        dp[i][j] = dp[i][j - 2];   // a* only counts as empty
        return dp[m][n]; //返回结果
    }
}
