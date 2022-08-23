package jzoffer;

import java.util.Arrays;

/*
* 构建乘积数组
*
* 给定一个数组 A[0, 1,..., n-1]，请构建一个数组 B[0, 1,..., n-1]，
* 其中 B 中的元素 B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。要求不能使用除法。
* */
public class _71 {
    public static void main(String[] args) {
        int[] A = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(multiply(A)));
    }
    public static int[] multiply(int[] A) {
        int n = A.length; //获取A数组长度
        int[] B = new int[n];  //构造B数组
        //因为product *= A[i]写在循环的第三部分，所以每一轮都会product都在乘前一轮的A[i]
        for (int i = 0, product = 1; i < n; product *= A[i], i++)  //这一轮循环得到的B[i]都乘了i左边的数
            B[i] = product;
        for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--)//这一轮循环得到的B[i]继续乘了i右边的数
            B[i] *= product;
        //所以最后得到的B就是没乘i本身，其余数都乘过了的数组
        return B;
    }
}
