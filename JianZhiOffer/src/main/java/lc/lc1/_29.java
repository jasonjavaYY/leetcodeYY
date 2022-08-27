package lc.lc1;

/*
* 两数相除
*
* 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
返回被除数 dividend 除以除数 divisor 得到的商。
整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
示例
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
示例 2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
*
* 举个例子：11 除以 3 。
首先11比3大，结果至少是1，然后我让3翻倍，就是6，发现11比3翻倍后还要大，那么结果就至少是2了，
* 那我让这个6再翻倍，得12，11不比12大，我知道最终结果肯定在2和4之间。也就是说2再加上某个数，这个数是多少呢？
* 我让11减去刚才最后一次的结果6，剩下5，我们计算5是3的几倍，也就是除法，看，递归出现了。
* */
//被除数 dividend 和除数 divisor。将两数相除，不使用乘法、除法和mod
public class _29 {
    public long divide(int dividend, int divisor) {
        if (dividend == 0) return 0; //如果被除数是0，返回0
        if (divisor == 1) return dividend; //如果除数是1，返回被除数
        if (divisor == -1) { //如果除数是-1，返回被除数相反数
            if (dividend > Integer.MIN_VALUE) return -dividend;// 只要不是最小整数返回相反数
            return Integer.MAX_VALUE;// 是最小的就返回最大整数
        }
        long a = dividend; //将被除数和除数转换成长整型
        long b = divisor;
        int sign = 1; //两个数相除后的符号位
        if ((a > 0 && b < 0) || (a < 0 && b > 0)) {
            sign = -1;
        }
        a = a > 0 ? a : -a; //将被除数和除数都转换成正数
        b = b > 0 ? b : -b;
        long res = div(a, b); //执行a除以b得到结果
        //如果符号位是正，就返回res，否则返回-res，记得进行最大值判断
        if (sign > 0) return res > Integer.MAX_VALUE ? Integer.MAX_VALUE : res;
        return -res;
    }

    long div(long a, long b) {
        if (a < b) return 0; //如果a小于b返回0，递归退出条件
        long count = 1;
        long tb = b; // 在后面的代码中不更新b
        while ((tb + tb) <= a) { //循环判断如果a大于等于b的两倍
            count = count + count; // 最小解翻倍
            tb = tb + tb; // 当前测试值也翻倍
        } //跳出循环时tb已经足够大了，此时a不能超过tb两倍，结果+count并递归a-tb和b
        return count + div(a - tb, b);
    }
}
