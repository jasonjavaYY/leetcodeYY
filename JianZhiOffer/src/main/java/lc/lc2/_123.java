package lc.lc2;

/*
* 买卖股票的最佳时机 III
*
* 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
示例 1:
输入：prices = [3,3,5,0,0,3,1,4]
输出：6
解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
示例 2：
输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
示例 3：
输入：prices = [7,6,4,3,1]
输出：0
解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
示例 4：
输入：prices = [1]
输出：0
*
* 方法一：动态规划
由于我们最多可以完成两笔交易，因此在任意一天结束之后(注意是之后，所以才会有下面的转移方程)，我们会处于以下五个状态中的一种：
未进行过任何操作；只进行过一次买操作；进行了一次买操作和一次卖操作，即完成了一笔交易；在完成了一笔交易的前提下，进行了第二次买操作；完成了全部两笔交易。
由于第一个状态的利润显然为 0，因此我们可以不用将其记录。对于剩下的四个状态，我们分别将它们的最大利润记为 buy1,sell1,buy2,sell2。
如果我们知道了第 i−1 天结束后的这四个状态，那么如何通过状态转移方程得到第 i 天结束后的这四个状态呢？
对于 buy1而言，在第 i天我们可以不进行任何操作，保持不变，也可以在未进行任何操作的前提下以 prices[i] 的价格买入股票，那么 buy1的状态转移方程即为：
buy1=max{buy1′,−prices[i]},这里我们用 buy1′表示第 i−1 天的状态，以便于和第 i 天的状态buy1进行区分。
对于 sell1而言，在第 i 天我们可以不进行任何操作保持不变，也可以在只进行过一次买操作的前提下以 prices[i] 卖出股票，那么sell1的状态转移方程即为：
sell1=max{sell1′,buy1′+prices[i]}
同理我们可以得到buy2和 sell2对应的状态转移方程：buy2=max{buy2′,sell1′−prices[i]}
sell2=max{sell2′,buy2′+prices[i]}
在考虑边界条件时，无论题目中是否允许「在同一天买入并且卖出」这一操作，最终的答案都不会受到影响，这是因为这一操作带来的收益为零。
因此，在状态转移时，我们可以直接写成：
buy1=max{buy1,−prices[i]}
sell1=max{sell1,buy1+prices[i]}
buy2=max{buy2,sell1−prices[i]}
sell2=max{sell2,buy2+prices[i]}
例如在计算 sell1时，我们直接使用 buy1而不是 buy1′进行转移。buy1比 buy1′多考虑的是在第 i 天买入股票的情况，而转移到 sell1时，
考虑的是在第 i 天卖出股票的情况，这样在同一天买入并且卖出收益为零，不会对答案产生影响。同理对于 buy2以及 sell2，
我们同样可以直接根据第 i 天计算出的值来进行状态转移。那么对于边界条件，我们考虑第 i=0 天时的四个状态：
buy1即为以 prices[0] 的价格买入股票，因此buy1=−prices[0]；sell1即为在同一天买入并且卖出，因此 sell1=0；
buy2即为在同一天买入并且卖出后再以 prices[0] 的价格买入股票，因此 buy2=−prices[0]；同理可得 sell2=0。
我们将这四个状态作为边界条件，从 i=1 开始进行动态规划，即可得到答案。
在动态规划结束后，由于我们可以进行不超过两笔交易，因此最终的答案在 0，sell1，sell2中，且为三者中的最大值。
然而我们可以发现，由于在边界条件中 sell1和 sell2的值已经为 0，并且在状态转移的过程中我们维护的是最大值，
因此 sell1和sell2最终一定大于等于 0。同时，如果最优的情况对应的是恰好一笔交易，那么它也会因为我们在转移时允许同一天买入并且卖出这一条件，
从 sell1转移至 sell2，因此最终的答案即为 sell2
* */
//动态规划 数组第i个元素是股票第i天价格。计算最大利润。最多可完成两笔交易。必须在再购买前出售之前的股票
public class _123 {
    public int maxProfit(int[] prices) {
        int n = prices.length; //计算股票天数
        //只可能有四种状态：
        //①只买了一次；②买一次卖一次；③完成一笔交易下买了第二次；④卖出了第二次。
        //初始值分别如下
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) { //i从1到n
            //buy1在第 i天可以不进行任何操作，也可以在未进行操作下以prices[i]买入股票
            buy1 = Math.max(buy1, -prices[i]);
            //sell1在第 i天可以不进行任何操作，也可以在buy1下以 prices[i]卖出股票
            sell1 = Math.max(sell1, buy1 + prices[i]);
            //buy2在第 i天可以不进行任何操作，也可以在sell1下以prices[i]买入股票
            buy2 = Math.max(buy2, sell1 - prices[i]);
            //sell2在第 i天可以不进行任何操作，也可以在buy2下以 prices[i]卖出股票
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2; //最后返回sell2
    }
}
