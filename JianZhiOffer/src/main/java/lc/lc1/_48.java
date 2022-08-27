package lc.lc1;

/*
* 旋转图像
*
* 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
*
* 方法三：用翻转代替旋转
我们还可以另辟蹊径，用翻转操作代替旋转操作。先将其通过水平轴翻转,再根据主对角线翻转,就得到了答案。
* */
//n × n二维矩阵matrix。将其顺时针旋转 90 度
public class _48 {
    public void rotate(int[][] matrix) {
        int n = matrix.length; //获取行数
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) { //i从0到n/2行
            for (int j = 0; j < n; ++j) { //列号从0到n，因为是n*n矩阵，行列数相等
                //翻转i,j和n-i-1,j
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) { //i从0到n
            for (int j = 0; j < i; ++j) { //j从0到i
                //ij翻转到ji
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
