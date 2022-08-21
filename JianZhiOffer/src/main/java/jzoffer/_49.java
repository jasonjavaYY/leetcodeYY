package jzoffer;
/*
* 把数字翻译成字符串
*
* 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。
* 一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。
* 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
* */
public class _49 {
    public int numDecodings(String s) {
        //如果数字字符串为空，返回0种
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length(); //获取字符串长度
        int[] dp = new int[n + 1];  //构造整形数组
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int one = Integer.parseInt(s.substring(i - 1, i));
            if (one != 0)
                dp[i] += dp[i - 1];
            if (s.charAt(i - 2) == '0')
                continue;
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }
}
