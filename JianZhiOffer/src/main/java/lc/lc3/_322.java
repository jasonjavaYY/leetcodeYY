package lc.lc3;

import java.util.Arrays;

/*
* 零钱兑换
*
* 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
你可以认为每种硬币的数量是无限的。
示例 1：
输入：coins = [1, 2, 5], amount = 11
输出：3
解释：11 = 5 + 5 + 1
示例 2：
输入：coins = [2], amount = 3
输出：-1
示例 3：
输入：coins = [1], amount = 0
输出：0
*
* 方法二：动态规划
定义 F(i) 为组成金额 i 所需最少的硬币数量，假设在计算 F(i) 之前，我们已经计算出 F(0)−F(i−1) 的答案。 则 F(i) 对应的转移方程应为
F(i)= j=0…n−1 | min F(i−cj)+1 ，其中 c_j代表的是第 j 枚硬币的面值，即我们枚举最后一枚硬币面额是 c_j，那么需要从 i−cj这个金额
* 的状态F(i−cj) 转移过来，再算上枚举的这枚硬币数量 1 的贡献，由于要硬币数量最少，所以 F(i) 为前面能转移过来的状态的最小值加上枚举的硬币数量 1 。
* */
//整数数组coins表示不同面额硬币；amount表示总金额。返回可凑成总金额最少硬币数。如不能返回-1，每种硬币数量无限
public class _322 {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];//F(i)为组成金额 i 所需最少硬币数
        Arrays.fill(dp, max);//首先将dp所有元素赋值为mount+1代表非法数
        dp[0] = 0;//组成0元需要0个硬币
        for (int i = 1; i <= amount; i++) {//i从1到amount
            for (int j = 0; j < coins.length; j++) {//遍历所有面值硬币
                //F(i)=j从0到n-1下式的最小值F(i−c_j)+1，c_j代表第 j 枚硬币面值
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }//如果dp[amount]是非法数代表无法组成，返回-1，否则返回dp[amount]
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
