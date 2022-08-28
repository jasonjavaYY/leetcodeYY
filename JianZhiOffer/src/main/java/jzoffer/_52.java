package jzoffer;
/*
* 丑数
*
* 把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。例如 6、8 都是丑数，
* 但 14 不是，因为它包含因子 7。习惯上我们把 1 当做第一个丑数。求从小到大的顺序第N个丑数。
* */
//动态规划 只包含因子 2、3 和 5 的数称作丑数。把 1 当做第一个丑数。求从小到大第N个丑数
public class _52 {
    public static void main(String[] args) {
        System.out.println(GetUglyNumber_Solution(9));
    }

    public static int GetUglyNumber_Solution(int N) {
        if (N <= 6) return N;  //前六个丑数就是本身
        int i2 = 0, i3 = 0, i5 = 0;
        //i2、i3、i5代表已经乘了2、3、5的数字下标，例如i2=2，代表dp[0]，dp[1]、dp[2]都已经乘过2了
        //所以下个丑数如果是通过×2得到，一定是dp[3]*2，所以当dp[3]*2是当前最小的丑数时，
        //下个丑数就是它，i3和i5以此类推
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3] * 3, next5 = dp[i5] * 5;
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2)
                i2++;
            if (dp[i] == next3)
                i3++;
            if (dp[i] == next5)
                i5++;
        }
        return dp[N - 1];
    }
}
