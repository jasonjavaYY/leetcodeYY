package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 解数独
*
* 编写一个程序，通过填充空格来解决数独问题。数独的解法需 遵循如下规则：数字 1-9 在每一行只能出现一次。数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）数独部分空格内已填入了数字，空白格用 '.' 表示。
*
* 算法
首先对整个数独数组进行遍历，当我们遍历到第 i行第 j 列的位置：如果该位置是一个空白格，那么我们将其加入一个用来存储空白格位置的列表中，方便后续的递归操作；
如果该位置是一个数字 x，那么我们需要将 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1] 均置为 True。
当我们结束了遍历过程之后，就可以开始递归枚举。当递归到第 i 行第 j 列的位置时，我们枚举填入的数字 x。根据题目的要求，
* 数字 x 不能和当前行、列、九宫格中已经填入的数字相同，因此 line[i][x−1]，column[j][x−1] 以及 block[⌊i/3⌋][⌊j/3⌋][x−1]必须均为False。
当我们填入了数字 x 之后，我们要将上述的三个值都置为True，并且继续对下一个空白格位置进行递归。在回溯到当前递归层时，我们还要将上述的三个值重新置为False。
* */
public class _37 {
    //line和columns记录每一行列每个数字出现次数，subboxes记录每个小九宫格中每个数字出现次数
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>(); //用于记录空白位置

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') { //遍历原数独，遇到.就把ij放入空白数组
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1; //否则遇到数字，指定位置记录数字-1出现过
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }
        dfs(board, 0); //然后解数独
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) { //如果位置达到了空白空间大小，说明解完了，返回
            valid = true; //用于判断是否解完标志位
            return;
        }

        int[] space = spaces.get(pos); //否则解pos位置应该填的数
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            //遍历digit从0到8，并且当前还没解完，判断行、列、对角线都不能出现过该数字
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                //将该位置填充为digit，更新行、列、对角线记录情况
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1); //继续回溯pos+1位置
                //回退时需要更新行、列、对角线记录情况为false
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}
