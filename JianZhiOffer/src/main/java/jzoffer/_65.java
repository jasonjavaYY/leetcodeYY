package jzoffer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
* n 个骰子的点数
*
* 把 n 个骰子仍在地上，求点数和为 s 的概率。
*
* 动态规划
使用一个二维数组 dp 存储点数出现的次数，其中 dp[i][j] 表示前 i 个骰子产生点数 j 的次数。
* 空间复杂度：O(N<sup>2</sup>)
* */
//动态规划 n个骰子仍在地上，求点数和为 s 的概率
public class _65 {
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        final int face = 6;  //一共有六个面
        final int pointNum = face * n;  //最高可能出现多少点
        long[][] dp = new long[n + 1][pointNum + 1];
        //一个骰子产生各个点数的次数都是1
        for (int i = 1; i <= face; i++)
            dp[1][i] = 1;
        for (int i = 2; i <= n; i++) //骰子个数i从2到n
            for (int j = i; j <= pointNum; j++) //点数j从i到pointNum，因为i个骰子最小点数为i
                for (int k = 1; k <= face && k <= j; k++)
                    //i个骰子j点可能来自i-1个骰子j-k点，其中k可能是1-6，六种情况求和就是dp[i][j]
                    dp[i][j] += dp[i - 1][j - k];
        final double totalNum = Math.pow(6, n); //计算一共的额情况是6的n次方
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        //依次计算出各个点数的概率，n个骰子点数i出现次数是dp[n][i]，用dp[n][i] / totalNum
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / totalNum));
        return ret;
    }
}
