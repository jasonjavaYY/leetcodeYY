package jzoffer;
/*
* 礼物的最大价值
*
* 在一个 m*n 的棋盘的每一个格都放有一个礼物，每个礼物都有一定价值（大于 0）。
* 从左上角开始拿礼物，每次向右或向下移动一格，直到右下角结束。给定一个棋盘，求拿到礼物的最大价值。
* 例如，对于如下棋盘
1    10   3    8
12   2    9    6
5    7    4    11
3    7    16   5
* 礼物的最大价值为 1+12+5+7+7+16+5=53。
*
* 应该用动态规划求解，而不是深度优先搜索，深度优先搜索过于复杂，不是最优解。
* */
public class _50 {
    public static void main(String[] args) {
        int[][] values = {{1,10,3,8},{12,2,9,6},{5,7,4,11},{3,7,16,5}};
        System.out.println(getMost(values));
    }
    public static int getMost(int[][] values) {
        //如果数组为空，返回0
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int n = values[0].length;  //获取列数
        int[] dp = new int[n];
        for (int[] value : values) {
            //遍历每一行数据，内层每一次循环都能得到第j行的最大值
            dp[0] += value[0];
            //dp[n]代表遍历到value的那一行的某个点的最大值
            //因为只能向右或者向下走，所以某个点的路径只能是其上面点到自己或者其左边点到自己
            //所有到某个点的路径最大值就是上面点或者左面点中的最大值再加上这个点的值
            //第一轮循环dp[n]是1 11 14 22  代表从左上角到1  10  3  8这几个点的最大值
            //第二轮循环dp[n]是13 15 24 30  代表从左上角到12  2  9  6这几个点的最大值
            for (int i = 1; i < n; i++)
                //dp[i]代表到某个点的上面点的最大值，dp[i-1]代表某个点左边点的最大值
                //这两个值取max再加当前点的值，就是到当前点的最大值
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
        }
        return dp[n - 1];
    }
}
