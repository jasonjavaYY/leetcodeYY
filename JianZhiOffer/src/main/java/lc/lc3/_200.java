package lc.lc3;

/*
* 岛屿数量
*
* 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
此外，你可以假设该网格的四条边均被水包围。
*
示例 1：
输入：grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
输出：1
示例 2：
输入：grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
输出：3
*
* 方法一：深度优先搜索
将二维网格看成一个无向图，竖直或水平相邻的 1 之间有边相连。可以扫描整个二维网格。如果一个位置为 1，则以其为起点开始进行深度优先搜索。
* 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0。最终岛屿数量就是进行深度优先搜索的次数。
* */
public class _200 {
    //类似于lc的_130，只不过那道题边界不算被包围
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length; //nr行nc列
        int nc = grid[0].length;
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return; //如果rc下标越界或者rc位置是0就返回
        } //否则将rc位置设为0，继续找相邻上下左右四个点
        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {//如果数组为空，返回0
            return 0;
        }//网格看成无向图，扫描图以1为起点dfs。搜到的1被重标记为0。岛屿数就是dfs次数
        int nr = grid.length; //nr行nc列
        int nc = grid[0].length;
        int num_islands = 0; //岛屿数
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                //遍历数组，如果rc位置是1，将岛屿数++并从rc开始dfs相邻的1
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands; //返回岛屿数
    }
}
