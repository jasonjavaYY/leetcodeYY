package lc.lc1;

/*
* 编辑距离
*
* 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。
你可以对一个单词进行如下三种操作：
插入一个字符   删除一个字符   替换一个字符
*
* 示例 1：
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')+
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
*
示例 2：
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
*
* 动态规划：
dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数,当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。针对第一行，第一列要单独考虑，引入 ''
第一行，是 word1 为空变成 word2 最少步数，就是插入操作,第一列，是 word2 为空，需要的最少步数，就是删除操作
* */
//动态规划 两个单词word1和word2，返回word1转换成word2使用最少操作数，可以插一字符、删字符、换字符
public class _72 {
    public int minDistance(String word1, String word2) {
        int n1 = word1.length(); //计算两个单词各自长度
        int n2 = word2.length();
        int[][] dp = new int[n1 + 1][n2 + 1]; //构造dp数组
        // 第0行，word1为空变成word2最少步数，就是插入操作，因此依次+1
        for (int j = 1; j <= n2; j++) dp[0][j] = dp[0][j - 1] + 1;
        // 第0列，是word2为空，需要的最少步数就是删除操作，也依次+1
        for (int i = 1; i <= n1; i++) dp[i][0] = dp[i - 1][0] + 1;
        for (int i = 1; i <= n1; i++) { //i从1到n1，j从1到n2
            for (int j = 1; j <= n2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))//如果i-1和j-1位置字符相同
                    dp[i][j] = dp[i - 1][j - 1];
                else //如果字符不同，就是dp[i-1][j-1], dp[i][j-1]), dp[i-1][j]的最小值再+1，分别对应替换、删除、插入
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;
            }
        }
        return dp[n1][n2]; //返回结果
    }
}
