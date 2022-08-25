package lc.lc1;

import java.util.*;

/*
* N 皇后
*
* 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
*
示例 1：
输入：n = 4
输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
解释：如上图所示，4 皇后问题存在两个不同的解法。
示例 2：
输入：n = 1
输出：[["Q"]]
*
* 回溯：使用一个数组记录每行放置的皇后的列下标，依次在每一行放置一个皇后。每次新放置的皇后都不能和已经放置的皇后之间有攻击：
* 即新放置的皇后不能和任何一个已经放置的皇后在同一列以及同一条斜线上，并更新数组中的当前行的皇后列下标。当 N 个皇后都放置完毕，则找到一个可能的解。
* 当找到一个可能的解之后，将数组转换成表示棋盘状态的列表，并将该棋盘状态的列表加入返回列表。由于每个皇后必须位于不同列，
* 因此已经放置的皇后所在的列不能放置别的皇后。第一个皇后有 N 列选择，第二个皇后最多有 N−1 列可以选择，第三个皇后最多有 N−2 列可以选择
* ,因此所有可能的情况不会超过 N! 种，时间复杂度是 O(N!)。为了降低总时间复杂度，每次放置皇后时需要快速判断每个位置是否可以放置皇后，
* 用集合对皇后的放置位置进行判断，都可以在 O(1) 的时间内判断一个位置是否可以放置皇后，算法的总时间复杂度是 O(N!)。
*
* 为了判断一个位置所在的列和两条斜线上是否已经有皇后，使用三个集合 columns、diagonals1和 diagonals2分别记录每一列以及两个方向的每条斜线上是否有皇后。
* 列的表示法很直观，一共有 N 列，每一列的下标范围从 0 到 N−1，使用列的下标即可明确表示每一列。对于每个方向的斜线，需要找到斜线上的每个位置的
* 行下标与列下标之间的关系。方向一的斜线为从左上到右下，同一条斜线上的每个位置满足行下标与列下标之差相等。
* 方向二的斜线为从右上到左下方向，同一条斜线上的每个位置满足行下标与列下标之和相等.每次放置皇后时，对于每个位置判断其是否在三个集合中，
* 如果三个集合都不包含当前位置，则当前位置是可以放置皇后的位置。
* */
public class _51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>(); //保存结果，双层数组
        int[] queens = new int[n]; //queens[i]代表第i行皇后防止的位置，因为每一行只一个皇后
        Arrays.fill(queens, -1); //先把queens填充为-1
        Set<Integer> columns = new HashSet<Integer>(); //列和对角线Set
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        //回溯，传入结果、queens、n、row、三个set
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns,
                          Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) { //如果行号=n代表回溯结束，利用queens产生一种结果，将结果放入结果集
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else { //否则i从0到n
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) { //如果列包含i就下一轮循环
                    continue;
                } //如果对角线包含i，也下一轮循环
                int diagonal1 = row - i; //对角线1是row-i
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;//对角线2是row+i
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i; //通过了上面，就把queens[row] = i
                columns.add(i);  //更新列和对角线set
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                //继续回溯row+1行
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1; //回退时将queens[row] = -1，列和对角线中移除对应值
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    //根据queens和n产生字符串代表二维矩阵
    // board = [".Q..","...Q","Q...","..Q."]
    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n]; //每一行是大小n的字符数组
            Arrays.fill(row, '.'); //先都填充为.
            row[queens[i]] = 'Q'; //该行的queens[i]位置设置为皇后Q
            board.add(new String(row)); //将每一行的字符串加入board
        }
        return board;
    }
}
