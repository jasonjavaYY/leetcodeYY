package lc.lc5;

import java.util.Arrays;

/*
* 赛车
*
* 你的赛车可以从位置 0 开始，并且速度为 +1 ，在一条无限长数轴上行驶。赛车也可以向负方向行驶。赛车可以按照由加速指令 'A' 和倒车指令 'R'
* 组成的指令序列自动行驶。当收到 'A' 时，赛车这样行驶：position += speed   speed *= 2
当收到指令 'R' 时，赛车这样行驶：如果速度为正数，那么speed = -1 否则 speed = 1 当前所处位置不变。
例如，在执行指令 "AAR" 后，赛车位置变化为 0 --> 1 --> 3 --> 3 ，速度变化为 1 --> 2 --> 4 --> -1 。
给你一个目标位置 target ，返回能到达目标位置的最短指令序列的长度。
示例 1：
输入：target = 3
输出：2
解释：
最短指令序列是 "AA" 。
位置变化 0 --> 1 --> 3 。
示例 2：
输入：target = 6
输出：5
解释：
最短指令序列是 "AAARA" 。
位置变化 0 --> 1 --> 3 --> 7 --> 7 --> 6 。
*
* 方法二：动态规划
假设我们需要到达位置 x，且 2^{k-1} ≤ x < 2^k，我们用 dp[x] 表示到达位置 x 的最短指令长度。如果 t = 2^{k-1}，那么我们只需要用 A^k即可。
* 否则我们需要考虑两种情况：首先用 A^{k-1}到达位置 2^{k-1} - 1，随后折返并使用 A^j，这样我们到达了位置 2^{k-1} - 2^j，使用的指令为
*  A^{k-1} R A^j R，长度为 k−1+j+2，剩余的距离为 x-(2^{k-1}-2^j) < x；我们首先用 A^k到达位置 2^k - 1，随后仅使用折返指令，
* 此时我们已经超过了终点并且速度方向朝向终点，使用的指令为 A^k R，长度为 k+1，剩余的距离为 x-(2^k)-1 < x。
* */
//动态规划 车从位置0开始速度为+1，车也可负向行驶。按加速'A'和倒车'R'组的序列行驶。'A'时position+=speed speed*=2
//'R'时：如果速度为正，那么speed = -1 否则 speed = 1 位置不变，给目标target，返回到达目标的最短指令序列长度
public class _818 {
    public int racecar(int target) {
        //dp[x] 表示到位置 x 最短指令长度
        int[] dp = new int[target + 3];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;//到位置0指令数为0
        dp[1] = 1;//到位置1指令数为1，A即可
        dp[2] = 4;//到位置2指令数为4，AARA:0->1->3->3->2
        //目标值t从3到target
        for (int t = 3; t <= target; ++t) {
            //找到t左边连续0个数，再用32减掉，就是高位1的位置
            int k = 32 - Integer.numberOfLeadingZeros(t);
            if (t == (1 << k) - 1) {//如果t恰好是2^k-1，就通过k次A即可到达
                dp[t] = k;//因此dp[t] = k
                continue;
            }//否则t比2^k-1小但肯定大于2^{k-1}，先向右到2^{k-1}，然后R再j次A到2^{k-1}-2^j，到
            //dp[t - (1 << (k - 1)) + (1 << j)]位置
            for (int j = 0; j < k - 1; ++j)//因此j从0到k-2遍历求出dp[t-(1 << (k - 1))+(1 << j)]+k-1+j+2最小值
                dp[t] = Math.min(dp[t], dp[t - (1 << (k - 1)) + (1 << j)] + k - 1 + j + 2);
            if ((1 << k) - 1 - t < t)//如果2t大于2^{k}-1，使用指令A^kR，长度为 k+1，求dp[(1 << k)-1 - t]+k+1的较小值
                dp[t] = Math.min(dp[t], dp[(1 << k) - 1 - t] + k + 1);
        }
        return dp[target];//返回dp[target]
    }
}
