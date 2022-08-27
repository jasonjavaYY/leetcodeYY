package lc.lc1;

/*
* 搜索二维矩阵
*
* 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
每行中的整数从左到右按升序排列。每行的第一个整数大于前一行的最后一个整数。
*
* 方法一：两次二分查找
由于每行的第一个元素大于前一行的最后一个元素，且每行元素是升序的，所以每行的第一个元素大于前一行的第一个元素，因此矩阵第一列的元素是升序的。
我们可以对矩阵的第一列的元素二分查找，找到最后一个不大于目标值的元素，然后在该元素所在行中二分查找目标值是否存在。
* */
//判断 m x n 矩阵中是否存在目标值。矩阵每行从左到右升序。每行第一个整数大于前一行最后一个整数
public class _74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowIndex = binarySearchFirstColumn(matrix, target);
        if (rowIndex < 0) { //如果行号小于0，说明左上角元素都比target大，返回false
            return false;
        }
        return binarySearchRow(matrix[rowIndex], target); //否则再二分查找这一行
    }
    //二分查找第一列元素小于等于target的最大值
    public int binarySearchFirstColumn(int[][] matrix, int target) {
        //低和高边界分别是0和m-1，因为有m行，所以一列有m个元素，就是matrix.length-1
        int low = -1, high = matrix.length - 1;
        while (low < high) { //二分循环
            int mid = (high - low + 1) / 2 + low; //计算mid
            //因为找第一列，所以matrix[mid][0]
            if (matrix[mid][0] <= target) {
                low = mid; //如果mid[0]小于等于target，更新low
            } else { //否则更新high
                high = mid - 1;
            }
        }
        return low; //返回找到的行号
    }
    //标准的二分查找
    public boolean binarySearchRow(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            if (row[mid] == target) {
                return true;
            } else if (row[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }
}
