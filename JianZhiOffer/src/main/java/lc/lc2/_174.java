package lc.lc2;

import java.util.Arrays;

/*
* 地下城游戏
*
* 公主（P）在地下城右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，
* 他必须穿过地下城并通过对抗恶魔来拯救公主。骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
有些房间由恶魔守卫，骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），
* 要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。为了尽快到达公主，骑士决定每次只向右或向下移动一步。
编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
*
* 方法一：动态规划
思路及算法
几个要素：「M×N 的网格」「每次只能向右或者向下移动一步」，让人很容易想到该题使用动态规划的方法。
如果按照从左上往右下的顺序进行动态规划，对于每一条路径，我们需要同时记录两个值。第一个是「从出发点到当前点的路径和」，
* 第二个是「从出发点到当前点所需的最小初始值」。而这两个值的重要程度相同，参看下面的示例：
 0  -2   3
-1   0   0
-3  -3  -2
* 从 (0,0) 到 (1,2) 有多条路径，我们取其中最有代表性的两条
绿色路径(先横向向右2次再向下1次)「从出发点到当前点的路径和」为 1，「从出发点到当前点所需的最小初始值」为 3。
蓝色路径(先向下1此再横向向右2次)「从出发点到当前点的路径和」为 −1，「从出发点到当前点所需的最小初始值」为 2。
我们希望「从出发点到当前点的路径和」尽可能大，而「从出发点到当前点所需的最小初始值」尽可能小。这两条路径各有优劣。
在上图中，我们知道应该选取绿色路径，因为蓝色路径的路径和太小，使得蓝色路径需要增大初始值到 4 才能走到终点，而绿色路径只要 3 点初始值
* 就可以直接走到终点。但是如果把终点的 −2 换为 0，蓝色路径只需要初始值 2，绿色路径仍然需要初始值 3，最优决策就变成蓝色路径了。
因此，如果按照从左上往右下的顺序进行动态规划，我们无法直接确定到达 (1,2) 的方案，因为有两个重要程度相同的参数同时影响后续的决策。
* 也就是说，这样的动态规划是不满足「无后效性」的。于是我们考虑从右下往左上进行动态规划。令 dp[i][j] 表示从坐标(i,j) 到终点所需的最小初始值。
* 换句话说，当我们到达坐标(i,j) 时，如果此时我们的路径和不小于 dp[i][j]，我们就能到达终点。这样一来，我们就无需担心路径和的问题，
* 只需要关注最小初始值。对于dp[i][j]，我们只要关心 dp[i][j+1] 和 dp[i+1][j] 的最小值 minn。记当前格子的值为 dungeon(i,j)，
* 那么在坐标 (i,j) 的初始值只要达到 minn−dungeon(i,j) 即可。同时，初始值还必须大于等于 1。这样我们就可以得到状态转移方程：
dp[i][j]=max(min(dp[i+1][j],dp[i][j+1])−dungeon(i,j),1)
最终答案即为 dp[0][0]。
边界条件，当 i=n−1 或者 j=m−1 时，dp[i][j] 转移需要用到的 dp[i][j+1] 和 dp[i+1][j] 中有无效值，因此代码实现中给无效值赋值为极大值。
* 特别地，dp[n−1][m−1] 转移需要用到的 dp[n−1][m] 和 dp[n][m−1] 均为无效值，因此我们给这两个值赋值为 1。
* */
//动态规划 MxN二维网格从左上到右下，如果健康点降至0或以下立死，房间数代表健康点变化，每次向右或下，计算确保到右下所需最低初始健康点
public class _174 {
    public int calculateMinimumHP(int[][] dungeon) {
        //n行m列
        int n = dungeon.length, m = dungeon[0].length;
        //
        //从右下往左上动态规划，dp[i][j]表示从坐标(i,j)到终点所需最小生命值
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; ++i) {//最开始都设置为MAX
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }//和右下角相邻的两个点值是1，只需保证到这两个点时至少有1点生命
        dp[n][m - 1] = dp[n - 1][m] = 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = m - 1; j >= 0; --j) {//i从n-1到0，j从m-1到0
                //先获取i,j能到的两个点i+1,j和i,j+1的最小生命
                int minn = Math.min(dp[i + 1][j], dp[i][j + 1]);
                //用上面的最小生命减去i,j损耗的生命就是i,j点所需的生命，
                //如果是负值或0代表该点无论多少生命都能通关，但生命最少为1，所以要和1比较取较大值
                dp[i][j] = Math.max(minn - dungeon[i][j], 1);
            }
        }
        return dp[0][0];//返回dp[0][0]
    }
}
