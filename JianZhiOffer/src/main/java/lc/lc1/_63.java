package lc.lc1;

/*
* 不同路径 II
*
* 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
网格中的障碍物和空位置分别用 1 和 0 来表示。
*
* 我们用f(i,j) 来表示从坐标 (0,0) 到坐标(i,j) 的路径总数，u(i,j) 表示坐标 (i,j) 是否可行，如果坐标(i,j)有障碍物u(i,j)=0，否则u(i,j)=1。

* */
public class _63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length; //n行m列
        int[] f = new int[m]; //用一维数组f代表到当前行某列的位置的最多种路径
        //初始化f[0]，如果obstacleGrid[0][0]不是障碍，f[0]就是1
        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) { //i从0到n
            for (int j = 0; j < m; ++j) { //j从0到m
                if (obstacleGrid[i][j] == 1) { //如果遇到障碍，f[j] = 0，继续
                    f[j] = 0;
                    continue;
                } //第0行的f[j]都是1，因此j从第一行开始迭代，没有障碍f[j] += f[j - 1]
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        return f[m - 1]; //返回最后一行遍历完的f[m - 1]
    }
}
