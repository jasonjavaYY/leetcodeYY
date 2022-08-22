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
public class _16 {
    public double Power(double base, int exponent) {
        if (exponent == 0) //如果指数是0，返回1
            return 1;
        if (exponent == 1) //如果指数是1，返回base
            return base;
        boolean isNegative = false;
        if (exponent < 0) { //记录指数是否为负值
            exponent = -exponent;
            isNegative = true;
        }
        //递归每次将指数减半
        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0) //如果指数是奇数，还要再乘一个base
            pow = pow * base;
        return isNegative ? 1 / pow : pow; //如果指数为正，返回pow，否则返回pow倒数
    }
}
