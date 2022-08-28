package jzoffer;
/*
* 股票的最大利润
*
* 可以有一次买入和一次卖出，买入必须在前。求最大收益。
*
* 使用贪心策略，假设第 i 轮进行卖出操作，买入操作价格应该在 i 之前并且价格最低。
* */
//数组 股票最大利润，可以有一次买入和一次卖出，买入必须在前求最大收益
public class _68 {
    public int maxProfit(int[] prices) {
        //如果数组为空，返回0
        if (prices == null || prices.length == 0)
            return 0;
        int soFarMin = prices[0]; //至此为止的最小值
        int maxProfit = 0;  //最大价值
        for (int i = 1; i < prices.length; i++) {
            //计算至此为止的最小值
            soFarMin = Math.min(soFarMin, prices[i]);
            //计算当前的最大收益，计算当前值-至此为止最小值，如果这个值比当前最大收益大，就更新当前最大收益
            maxProfit = Math.max(maxProfit, prices[i] - soFarMin);
        }
        return maxProfit;
    }
}
