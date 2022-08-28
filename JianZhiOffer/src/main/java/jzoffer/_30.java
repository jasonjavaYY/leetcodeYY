package jzoffer;

import java.util.ArrayList;

/*
 * 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
 * */
//数组 输入一个矩阵，从外向里顺时针依次打印每个数字
public class _30 {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        //r1是行的上边界，r2是行下边界，c1是列左边界，c2是列右边界
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        //不能超界
        while (r1 <= r2 && c1 <= c2) {
            //从左到右打印上边界
            for (int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);
            //从上到下打印右边界，注意初始条件是r1+1，否则右上角数字打印重复
            for (int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]);
            //如果上下边界没重合，就从右往左打印下边界，注意初始条件c2-1，否则右下角数字重复打印
            if (r1 != r2)
                for (int i = c2 - 1; i >= c1; i--)
                    ret.add(matrix[r2][i]);
                //如果左右边界没重合，就从下往上打印左边界，注意初始条件r2-1，否则左下角数字重复
            if (c1 != c2)
                for (int i = r2 - 1; i > r1; i--)
                    ret.add(matrix[i][c1]);
                //上边界++，下边界--，左边界++，右边界--，打印里面一圈
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ret;
    }
}
