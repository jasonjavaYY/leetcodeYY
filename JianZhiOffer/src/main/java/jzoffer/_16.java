package jzoffer;
/*
* 数值的整数次方
*
* 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方
*
* xn={   (x∗x) n/2     n%2=0
*       x∗(x∗x)n/2   n%2=1
*
* 因为 (x*x)n/2 可以通过递归求解，并且每次递归 n 都减小一半，因此整个算法的时间复杂度为 O(logN)
* */
//数学 一个double的base和int的exponent，求base的exponent 次方
public class _16 {
    public double myPow(double x, int n) {
        if (n == 0) return 1; //如果指数是0，返回1
        if (n == 1) return x;//如果指数是1，返回base
        boolean isNegative = false;
        if (n < 0) { //记录指数是否为负值
            n = -n;
            isNegative = true;
        }
        //递归每次将指数减半
        double pow = myPow(x * x, n / 2);
        if (n % 2 != 0) //如果指数是奇数，还要再乘一个base
            pow = pow * x;
        return isNegative ? 1 / pow : pow; //如果指数为正，返回pow，否则返回pow倒数
    }
}
