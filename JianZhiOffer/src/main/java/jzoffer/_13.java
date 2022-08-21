package jzoffer;

/*
* 矩阵中的路径
*
* 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
* 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向上下左右移动一个格子。
* 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。

例如下面的矩阵包含了一条 bfce 路径。
*
* 使用回溯法进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。
* 回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，
* 从而开始一次新的搜索过程。例如下图示例中，从 f 开始，下一步有 4 种搜索可能，
* 如果先搜索 b，需要将 b 标记为已经使用，防止重复使用。
* 在这一次搜索结束之后，需要将 b 的已经使用状态清除，并搜索 c。
* 本题的输入是数组而不是矩阵（二维数组），因此需要先将数组转换成矩阵
* */
public class _13 {
    public static void main(String[] args) {
        char[] array = new char[]{'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'};
        char[] str = new char[]{'f', 'c', 's', 'h'};
        System.out.println(hasPath(array, 3, 4, str));
    }

    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int rows;
    private static int cols;

    //array为原数组 rows和cols为行列数  str为要找的目标字符串
    public static boolean hasPath(char[] array, int rows, int cols, char[] str) {
        if (rows == 0 || cols == 0) return false;
        _13.rows = rows;
        _13.cols = cols;
        boolean[][] marked = new boolean[rows][cols];
        char[][] matrix = buildMatrix(array);
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (backtracking(matrix, str, marked, 0, i, j))
                    return true;
        return false;
    }

    private static boolean backtracking(char[][] matrix, char[] str, boolean[][] marked, int pathLen, int r, int c) {
        if (pathLen == str.length) return true;  //如果pathLen已经等于str.length，代表全部字符都找到了，返回true
        //这一行能通过表明目标字符串的第pathLen个字符找到了
        if (r < 0 || r >= rows || c < 0 || c >= cols || matrix[r][c] != str[pathLen] || marked[r][c]) {
            return false;
        }
        //将该位置标记为已使用，找目标字符串下一个字符，因此pathLen + 1，可以向四个方向找，因此遍历next
        //0 -1; 0 1; -1 0; 1 0分别代表行不动，列-1，也就是向下找，以此类推，向上找，向左找，向右找
        marked[r][c] = true;
        for (int[] n : next)
            if (backtracking(matrix, str, marked, pathLen + 1, r + n[0], c + n[1]))
                return true;
        marked[r][c] = false;
        return false;
    }

    private static char[][] buildMatrix(char[] array) {
        char[][] matrix = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                matrix[r][c] = array[idx++];
        return matrix;
    }
}
