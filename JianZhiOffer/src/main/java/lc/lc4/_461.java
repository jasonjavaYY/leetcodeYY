package lc.lc4;

/*
* 汉明距离
*
* 两个整数之间的汉明距离 指的是这两个数字对应二进制位不同的位置的数目。给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
示例 1：
输入：x = 1, y = 4
输出：2
解释：
1   (0 0 0 1)
4   (0 1 0 0)
       ↑   ↑
上面的箭头指出了对应二进制位不同的位置。
示例 2：
输入：x = 3, y = 1
输出：1
*
* 记 s =x^y，不断检查 s 最低位，如果最低位为 1，令计数器加一，然后令 s 右移一位，重复这个过程直到 s=0 。计数器中累计了 s 二进制表示中 1 的数量。
* */
//数学 两个整数间的汉明距离指两个数对应二进制位不同的位置数。给两个整数x和y返回之间的汉明距离
public class _461 {//本质相当于找一个二进制数s中1的个数
    public int hammingDistance(int x, int y) {
        int s = x ^ y, ret = 0;//让x和y亦或得到s，然后找s的1的个数
        while (s != 0) {//循环判断s是否到0
            ret += s & 1;//判断s最后一位如果是1，ret++
            s >>= 1; //s右移1位，继续判断下一位
        }
        return ret;//返回结果
    }
}
