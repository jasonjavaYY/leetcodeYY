package jzoffer;

/*
*二维数组中的查找
*
* 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。
* 给定一个数，判断这个数是否在该二维数组中。

要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。
该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。
* 因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，
* 当前元素的查找区间为左下角的所有元素。
* */
//数组 二维数组每行从左到右递增，从上到下递增。判断某个数是否在该二维数组
public class _2 {
    public boolean Find(int target, int[][] matrix) {
        //如果数组为空，返回false
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int rows = matrix.length, cols = matrix[0].length;
        int r = 0, c = cols - 1; // 从右上角开始
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c]) //如果找到了目标，返回true
                return true;
            else if (target > matrix[r][c]) //如果当前值小于目标值，行++
                r++;
            else   //如果当前值大于目标值，列--
                c--;
        }
        return false; //如果最后找不到返回false
    }
}
