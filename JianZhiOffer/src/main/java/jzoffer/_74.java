package jzoffer;

import java.util.HashMap;
import java.util.Map;
/*
* 二进制中 1 的个数
*
* 输入一个整数，输出该数二进制表示中 1 的个数
* */
public class _74 {
//  输出数二进制表示中1的个数。负数用补码表示。
    public static int NumberOf1(int n) {
        int count = 0;
        while(n!=0) {
            n = n&(n-1);
            count++;
        }
        return count;
    }
}
