package jzoffer;
/*
* 剪绳子
*
* 把一根绳子剪成多段，并且使得每段的长度乘积最大。
*
n = 2
return 1 (2 = 1 + 1)

n = 10
return 36 (10 = 3 + 3 + 4)
*
* 尽可能多剪长度为 3 的绳子，并且不允许有长度为 1 的绳子出现。
* 如果出现了，就从已经切好长度为 3 的绳子中拿出一段与长度为 1
* 的绳子重新组合，把它们切成两段长度为 2 的绳子。

证明：当 n >= 5 时，3(n - 3) - n = 2n - 9 > 0，且 2(n - 2) - n = n - 4 > 0。
* 因此在 n >= 5 的情况下，将绳子剪成一段为 2 或者 3，得到的乘积会更大。
* 又因为 3(n - 3) - 2(n - 2) = n - 5 >= 0，所以剪成一段长度为 3 比长度为 2 得到的乘积更大。
* */
public class _15 {
    public int integerBreak(int n) {
        if (n < 2) //如果绳子长度小于2没法剪断，返回0
            return 0;
        if (n == 2) //长度2的绳子只能剪成2个1，乘积为1
            return 1;
        if (n == 3) //长度3的绳子只能剪成1和2，乘积为2
            return 2;
        int timesOf3 = n / 3; //计算能获取多少段3
        if (n - timesOf3 * 3 == 1) //如果全是3最后剩下1，就少一个3，变成2和2
            timesOf3--;
        int timesOf2 = (n - timesOf3 * 3) / 2; //计算有多少个2
        //计算总乘积，3的timesOf3次方*2的timesOf2次方
        return (int) (Math.pow(3, timesOf3)) * (int) (Math.pow(2, timesOf2));
    }
}
