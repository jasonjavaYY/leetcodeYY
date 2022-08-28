package lc.lc2;

/*
* 买卖股票的最佳时机 II
*
* 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有一股股票。也可以先购买，然后在同一天出售。返回你能获得的最大利润。
*
* 方法一：动态规划
考虑到「不能同时参与多笔交易」，因此每天交易结束后只可能存在手里有一支股票或者没有股票的状态。
定义状态 dp[i][0] 表示第 i 天交易完后手里没有股票的最大利润，dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润（i 从 0 开始）。
考虑 dp[i][0] 的转移方程，如果这一天交易完后手里没有股票，那么可能的转移状态为前一天已经没有股票，即 dp[i−1][0]，
* 或者前一天结束的时候手里持有一支股票，即 dp[i−1][1]，这时候我们要将其卖出，并获得 prices[i] 的收益。因此为了收益最大化，我们列出如下的转移方程：
dp[i][0]=max{dp[i−1][0],dp[i−1][1]+prices[i]}
再来考虑 dp[i][1]，按照同样的方式考虑转移状态，那么可能的转移状态为前一天已经持有一支股票，即dp[i−1][1]，
* 或者前一天结束时还没有股票，即dp[i−1][0]，这时候我们要将其买入，并减少 prices[i] 的收益。可以列出如下的转移方程：
dp[i][1]=max{dp[i−1][1],dp[i−1][0]−prices[i]}
对于初始状态，根据状态定义我们可以知道第 0 天交易结束的时候 dp[0][0]=0，dp[0][1]=−prices[0]。
因此，我们只要从前往后依次计算状态即可。由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，
* 因此这时候 dp[n−1][0]的收益必然大于dp[n−1][1]的，最后的答案即为 dp[n−1][0]。
* */
//整数数组prices，prices[i]表示股票第i天价。每天可以购买或出售股票。最多只持有一股。返回最大利润
public class _122 {
    public int maxProfit(int[] prices) {
        int n = prices.length; //计算股票天数
        //dp[i][0]表示第 i 天交易完手里没有股票的最大利润
        //dp[i][1]表示第 i 天交易完手里持有一支股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = 0; //第0天没有股票利润为0
        dp[0][1] = -prices[0]; //第0天有股票利润为-prices[0]
        for (int i = 1; i < n; ++i) { //i从1到n
            //dp[i][0]可能状态为前一天已经没有股票，即 dp[i−1][0]
            //或前一天有股票但将其卖出，即dp[i−1][1]+prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //dp[i][1]可能状态前一天已经有股票，即dp[i−1][1]
            //或者前一天没股票要将其买入，即dp[i−1][0]-prices[i] 取最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0]; //返回最后一天手里没有股票的值
    }
}
