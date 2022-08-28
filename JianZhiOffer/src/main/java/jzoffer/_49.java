package jzoffer;
/*
* 把数字翻译成字符串
*
* 给定一个数字，按照如下规则翻译成字符串：1 翻译成“a”，2 翻译成“b”... 26 翻译成“z”。
* 一个数字有多种翻译可能，例如 12258 一共有 5 种，分别是 abbeh，lbeh，aveh，abyh，lyh。
* 实现一个函数，用来计算一个数字有多少种不同的翻译方法。
* */
//动态规划 数字翻译字符串：1译“a”，2译“b”..26译“z”。可能有多种如12258共5种:abbeh，lbeh，aveh，abyh，lyh，计算有多少种译法
public class _49 {
    public int numDecodings(String s) {
        //如果数字字符串为空，返回0种
        if (s == null || s.length() == 0)
            return 0;
        int n = s.length(); //获取字符串长度
        int[] dp = new int[n + 1];  //构造dp数组，dp[i]代表前i个字符有多少种
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1; //如果第一个字符是0，则有0种，否则就是1种
        for (int i = 2; i <= n; i++) {
            int one = Integer.parseInt(s.substring(i - 1, i)); //获取第i-1个字符，转换整形
            if (one != 0) //如果这个字符不是0，就先把i-1的值增到i上
                dp[i] += dp[i - 1];
            if (s.charAt(i - 2) == '0') //如果i-2字符是0，则不可能通过i-2和i-1这两个字符组装，继续循环
                continue;
            int two = Integer.parseInt(s.substring(i - 2, i)); //否则解析出两个字符，如果再26以内，就把i-2值增到i上
            if (two <= 26)
                dp[i] += dp[i - 2];
        }
        return dp[n];
    }
}
