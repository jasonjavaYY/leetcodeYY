package jzoffer;
/*
* 机器人的运动范围
*
* 地上有一个 m 行和 n 列的方格。一个机器人从坐标 (0, 0) 的格子开始移动，
* 每一次只能向左右上下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
* 例如，当 k 为 18 时，机器人能够进入方格 (35,37)，因为 3+5+3+7=18。
* 但是，它不能进入方格 (35,38)，因为 3+5+3+8=19。请问该机器人能够达到多少个格子？
*
* 使用深度优先搜索（Depth First Search，DFS）方法进行求解。
* 回溯是深度优先搜索的一种特例，它在一次搜索过程中需要设置一些本次搜索过程的局部状态，
* 并在本次搜索结束之后清除状态。而普通的深度优先搜索并不需要使用这些局部状态
*
* 回溯算法都要有一个marked数组用于表示某个点是否被用过
* */
//m*n方格。机器人从(0, 0)开始动，每次能向左右上下移动一格，不能进入行和列坐标的数位之和大于k的格子
//* 例如，当k为18时，机器人能进入格(35,37)，因为 3+5+3+7=18。但不能进入(35,38)。问能达到多少格子
public class _14 {
    private static final int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int cnt = 0;  //能到达多少格子
    private int rows;  //行数
    private int cols;  //列数
    private int threshold;  //传入的阈值18
    private int[][] digitSum;  //存储列坐标数位之和的二维数组

    //ok  该方法最后返回能到达多少格子
    public int movingCount(int threshold, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.threshold = threshold;
        initDigitSum();
        boolean[][] marked = new boolean[rows][cols];
        dfs(marked, 0, 0);
        return cnt;
    }

    //回溯一定是递归，所以要设置好返回
    private void dfs(boolean[][] marked, int r, int c) {
        //如果出界了或者rc位置被用过了，直接返回
        if (r < 0 || r >= rows || c < 0 || c >= cols || marked[r][c])
            return;
        //否则说明这个格子即将被使用，标记为被使用，
        marked[r][c] = true;
        //然后判断该格子如果超出限制，直接返回
        if (this.digitSum[r][c] > this.threshold)
            return;
        //否则说明这个格子可以达到，格子数增加
        cnt++;
        //向四个方向继续找下一个格子能否达到
        for (int[] n : next)
            dfs(marked, r + n[0], c + n[1]);
    }

    //ok  该方法根据二维数组的值计算出行列坐标数位之和的二维数组，该数组一定是对称的
    private void initDigitSum() {
        //构造一维坐标和数组，数组长度是行列中的较大值
        int[] digitSumOne = new int[Math.max(rows, cols)];
        //计算一维坐标和数组各元素值
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        //构造二维坐标和数组，元素ij是一维i+一维j的值
        this.digitSum = new int[rows][cols];
        for (int i = 0; i < this.rows; i++)
            for (int j = 0; j < this.cols; j++)
                this.digitSum[i][j] = digitSumOne[i] + digitSumOne[j];
    }
}
