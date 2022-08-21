package lc.lc1;

/*
* 螺旋矩阵 II
*
* 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
*
示例 1：
输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：
输入：n = 1
输出：[[1]]
*
* 方法二：按层模拟
可以将矩阵看成若干层，首先填入矩阵最外层的元素，其次填入矩阵次外层的元素，直到填入矩阵最内层的元素。
定义矩阵的第 k 层是到最近边界距离为 k 的所有顶点。例如，下图矩阵最外层元素都是第 1 层，次外层元素都是第 2 层，最内层元素都是第 3 层。
[[1, 1, 1, 1, 1, 1],
 [1, 2, 2, 2, 2, 1],
 [1, 2, 3, 3, 2, 1],
 [1, 2, 3, 3, 2, 1],
 [1, 2, 2, 2, 2, 1],
 [1, 1, 1, 1, 1, 1]]
对于每层，从左上方开始以顺时针的顺序填入所有元素。假设当前层的左上角位于 (top,left)，右下角位于 (bottom,right)，按照如下顺序填入当前层的元素。
从左到右填入上侧元素，依次为 (top,left) 到 (top,right)。从上到下填入右侧元素，依次为 (top+1,right) 到 (bottom,right)。
如果left<right 且 top<bottom，则从右到左填入下侧元素，依次为(bottom,right−1) 到 (bottom,left+1)，
* 以及从下到上填入左侧元素，依次为(bottom,left) 到(top+1,left)。填完当前层的元素之后，将 left 和top增加1，将right和bottom减少1，继续填入元素
* */
public class _59 {
    //和jzoffer的_30操作相反
    public int[][] generateMatrix(int n) {
        int num = 1;
        int[][] matrix = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int column = left; column <= right; column++) {
                matrix[top][column] = num;
                num++;
            }
            for (int row = top + 1; row <= bottom; row++) {
                matrix[row][right] = num;
                num++;
            }
            if (left < right && top < bottom) {
                for (int column = right - 1; column > left; column--) {
                    matrix[bottom][column] = num;
                    num++;
                }
                for (int row = bottom; row > top; row--) {
                    matrix[row][left] = num;
                    num++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;
    }
}
