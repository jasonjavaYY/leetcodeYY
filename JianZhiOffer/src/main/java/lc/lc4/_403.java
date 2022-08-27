package lc.lc4;

/*
* 青蛙过河
*
* 一只青蛙想要过河。假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。青蛙可以跳上石子，但是不可以跳入水中。
给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。开始青蛙默认站在第一块石子，
* 并可以假定它第一步只能跳跃 1 个单位（即只能从单元格 1 跳至单元格 2 ）。如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 
* k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
*
* 方法二：动态规划
令 dp[i][k] 表示青蛙能否达到「现在所处的石子编号」为 i 且「上一次跳跃距离」为 k 的状态。状态转移方程：
dp[i][k]=dp[j][k−1]⋁dp[j][k]⋁dp[j][k+1]
式中 j 代表青蛙的「上一次所在的石子编号」，满足 stones[i]−stones[j]=k。
状态转移的初始条件为 dp[0][0]=true，表示：「现在所处的石子编号」为 0（石子从 0 开始编号），「上一次跳跃距离」为 0（保证青蛙第一次跳跃距离为1）。
* 当我们找到一个 dp[n−1][k] 为真时，我们就知道青蛙可以到达终点（第 n−1 个石子）。具体地，对于第 i 个石子，我们首先枚举所有的 j
* （即上一次所在的石子编号），那么「上一次跳跃距离」k 即为 stones[i]−stones[j]。如果在第 j 个石子上，青蛙的「上一次跳跃距离」可以为
*  k−1,k,k+1 三者之一，那么我们此时的方案即为合法方案。因此只需检查 dp[j][k−1],dp[j][k],dp[j][k+1] 是否有至少一个为真即可。
*
* 两个结论，并做出优化：
「现在所处的石子编号」为 i 时，「上一次跳跃距离」k 必定满足 k≤i。
当青蛙位于第 0 个石子时，青蛙的上一次跳跃距离限定为 0，之后每次跳跃，青蛙所在的石子编号至少增加 1，而每次跳跃距离至多增加 1。
跳跃 m 次后，青蛙「现在所处的石子编号」i≥m，「上一次跳跃距离」k≤m，因此 k≤i。
可以从后向前枚举「上一次所在的石子编号」j，当「上一次跳跃距离」k 超过了 j+1 时，我们即可以停止跳跃，因为在第 j 个石子上我们至多只能跳出 j+1 距离。
* 当第 i 个石子与第 i−1 个石子距离超过 i 时，青蛙必定无法到达终点。
因为青蛙到达第 i−1 个石子时，它的「上一次跳跃距离」至多为 i−1，而距离第 i−1 个石子最近的石子即为第 i 个石子，它们的距离超过了当前能跳的最远距离，
* 因此青蛙无路可跳。因此可以提前检查是否有相邻的石子不满足条件，如果有，我们可以提前返回 false。
* */
//河流被等分为若干格，每格可能有石子可能没。青蛙可跳上石子。石子位置列表stones，判断青蛙能否在最后一步跳至最后一块石子，
//开始青蛙在第一块石子，假定第一步只能跳1单位。如果上一步跳k单位，接下来只能选k - 1、k 或 k + 1单位
public class _403 {
    public boolean canCross(int[] stones) {
        int n = stones.length;//求石子数组长度
        //dp[i][k]表示青蛙能否到石子编号i且上次跳距离k的状态
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;//第0个石子能到达，为true
        //遍历判断当第i石子与i−1石子距离超过i时，必定无法到终点，返回false
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) {
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {//i从1到n
            //对于第i个石子，枚举所有j（即上次在的石子编号），因此j从i-1到0
            for (int j = i - 1; j >= 0; --j) {
                //计算两个石子间的距离k
                int k = stones[i] - stones[j];
                if (k > j + 1) { //如果距离大于j+1，肯定没法跳break
                    break;
                }//从j石头过来跳了k距离，这次可以跳k-1、k或k+1，所以
                //dp[j][k - 1]、dp[j][k]、dp[j][k + 1]有一个为真即可
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                //当找到一个dp[n−1][k]为真时，就知道青蛙可以到达终点
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;//没找到dp[n−1][k]为真，代表无法跳到终点
    }
}
