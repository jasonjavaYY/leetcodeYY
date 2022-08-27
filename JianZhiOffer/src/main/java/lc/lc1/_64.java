package lc.lc1;

/*
* 最小路径和
*
* 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。每次只能向下或者向右移动一步。
*
* 方法一：动态规划
由于路径的方向只能是向下或向右，因此网格的第一行的每个元素只能从左上角元素开始向右移动到达，
* 网格的第一列的每个元素只能从左上角元素开始向下移动到达，此时路径是唯一的，每个元素对应的最小路径和即为对应的路径上的数字总和。
对于不在第一行和第一列的元素，可以从其上方相邻元素向下移动一步到达，或者从其左方相邻元素向右移动一步到达，
* 元素对应的最小路径和等于其上方相邻元素与其左方相邻元素两者对应的最小路径和中的最小值加上当前元素的值。
* 由于每个元素对应的最小路径和与其相邻元素对应的最小路径和有关，因此可以使用动态规划求解。
创建二维数组 dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到 (i,j) 位置的最小路径和。显然，dp[0][0]=grid[0][0]。
* 对于 dp 中的其余元素，通过以下状态转移方程计算元素值。
当 i>0 且 j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
当 i=0 且 j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]。
当 i>0 且 j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
最后得到 dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。
* */
//包含非负整数的m x n网格，找出从左上到右下路径使路径上数字总和最小。每次只能向下或右移一步
public class _64 {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; //如果路径为空，返回0
        }
        int rows = grid.length, columns = grid[0].length; //获取行列数
        int[][] dp = new int[rows][columns]; //构造dp数组
        dp[0][0] = grid[0][0]; //初始化dp[0][0]就是[0][0]数字大小
        for (int i = 1; i < rows; i++) { //初始化第0列的dp
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }  //初始化第0行的dp
        for (int j = 1; j < columns; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < rows; i++) { //i从1到row，j从1到col
            for (int j = 1; j < columns; j++) {
                //找到dp[i - 1][j]和dp[i][j - 1]较小值，再加grid[i][j]就是到[i][j]位置最短路径dp[i][j]
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[rows - 1][columns - 1]; //返回结果
    }
}
