package lc.lc5;

/*
* 最长公共子序列
*
* 给定两个字符串 text1 和 text2，返回两个字符串的最长公共子序列长度。如果不存在公共子序列 ，返回0 。
一个字符串的子序列由原字符串不改变字符相对顺序情况下删除某些字符（也可以不删除字符）后组成的新字符串。
例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是。两个字符串的公共子序列是这两个字符串所共同拥有的子序列。
示例 1：
输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。
示例 2：
输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
示例 3：
输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。
*
* 方法一：动态规划
最长公共子序列问题是典型的二维动态规划问题。假设字符串text1和 text2长度分别为 m 和 n，创建 m+1 行 n+1 列二维数组 dp，其中 dp[i][j]
* 表示 text1[0:i] 和 text2[0:j] 的最长公共子序列长度。text1[0:i] 表示 text1的长度 i 的前缀，text2[0:j] 表示 text2长度为 j 前缀。
边界情况：
当 i=0 时，text1[0:i] 为空，空字符串和任何字符串的最长公共子序列的长度是 0，因此对任意 0≤j≤n，有 dp[0][j]=0；
当 j=0 时text 2[0:j] 为空，同理对任意 0≤i≤m，有 dp[i][0]=0。因此当 i=0 或 j=0 时，dp[i][j]=0。
当 i>0 且j>0 时，考虑 dp[i][j] 计算：
当 text1[i−1]=text2[j−1] 时，将这两个相同的字符称为公共字符，考虑 text1[0:i−1] 和 text2[0:j−1] 最长公共子序列，
* 再增加一个字符（即公共字符）即可得到 text1[0:i] 和 text2[0:j] 的最长公共子序列，因此 dp[i][j]=dp[i−1][j−1]+1。
当 text1[i−1] !=text2[j−1] 时，考虑以下两项：text1[0:i−1] 和 text2[0:j] 最长公共子序列；text1[0:i] 和 text2[0:j−1] 最长公共子序列。
要得到 text1[0:i] 和 text2[0:j] 的最长公共子序列，应取两项中的长度较大的一项，因此 dp[i][j]=max(dp[i−1][j],dp[i][j−1])。
可以得到如下状态转移方程：
dp[i][j]={   dp[i−1][j−1]+1,                 text1[i−1]=text2[j−1]
             max(dp[i−1][j],dp[i][j−1]),     text1[i−1]=text2[j−1]
最终计算得到 dp[m][n] 即为 text1和 text2的最长公共子序列的长度。
* */
//动态规划 两字符串text1和text2，返回最长公共子序列长。不存在返回0。子序列由原串不变字符相对顺序下删某些字符也可不删除后组成
public class _1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();//计算两个字符串长度m和n
        //dp[i][j]表示text1[0:i]和text2[0:j]最长公共子序列长度
        int[][] dp = new int[m + 1][n + 1];
        //下标从1开始算，i从1到m，j从1到n
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);//获取串1的字符c1
            for (int j = 1; j <= n; j++) {
                //获取串2字符c2
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {//如果字符相同，从dp[i-1][j-1]值+1得到
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {//字符不相同，dp[i-1][j]或dp[i][j-1]中的较大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];//返回dp[m][n]
    }
}
