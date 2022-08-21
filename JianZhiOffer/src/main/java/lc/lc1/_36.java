package lc.lc1;

/*
* 有效的数独
*
* 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
数字 1-9 在每一行只能出现一次。数字 1-9 在每一列只能出现一次。数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
注意：一个有效的数独（部分已被填充）不一定是可解的。只需要根据以上规则，验证已经填入的数字是否有效即可。空白格用 '.' 表示。
*
* 方法一：一次遍历
有效的数独满足以下三个条件：同一个数字在每一行只能出现一次；同一个数字在每一列只能出现一次；同一个数字在每一个小九宫格只能出现一次。
可以使用哈希表记录每一行、每一列和每一个小九宫格中，每个数字出现的次数。只需要遍历数独一次，在遍历的过程中更新哈希表中的计数，判断是否满足有效的数独条件即可。
对于数独的第 i 行第 j 列的单元格，其中0≤i,j<9，该单元格所在的行下标和列下标分别为 i 和 j，该单元格所在的小九宫格的行数和列数分别为i/3和j/3，
* 其中0小于等于i/3，j/3小于3。由于数独中的数字范围是 1 到 9，因此可以使用数组代替哈希表进行计数。
创建二维数组rows 和 columns 分别记录数独的每一行和每一列中的每个数字的出现次数，创建三维数组 subboxes 记录每个小九宫格中的每个数字的出现次数，
* 其中 rows[i][index]、columns[j][index] 和 subboxes[⌊i/3⌋][⌊j/3⌋][index] 分别表示数独的第 i 行第 j 列的单元格所在的行、列和小九宫格中，
* 数字 index+1 出现的次数，其中0≤index<9，对应的数字index+1 满足 1≤index+1≤9。如果 board[i][j] 填入了数字n，
* 则将 rows[i][n−1]、columns[j][n−1] 和 subboxes[⌊i/3⌋][⌊j/3⌋][n−1] 各加 1。如果更新后的计数大于 1，则不符合有效的数独的条件，
* 返回false。如果遍历结束之后没有出现计数大于 1 的情况，则符合有效的数独的条件，返回 true。
* */
public class _36 {
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            int[][] rows = new int[9][9];
            int[][] columns = new int[9][9];
            int[][][] subboxes = new int[3][3][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        int index = c - '0' - 1;
                        rows[i][index]++;
                        columns[j][index]++;
                        subboxes[i / 3][j / 3][index]++;
                        if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
    }
}
