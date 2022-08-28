package jzoffer;

/*
 * 斐波那契数列
 *
 * 求斐波那契数列的第 n 项，n <= 39
 * */
//递归 求斐波那契数列的第 n 项
public class _8 {
    //fib[1]是1，从fib[2]开始，fib[i]=fib[i - 1]+fib[i - 2]
    public int Fibonacci(int n) {
        int[] fib = new int[40];
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
        return fib[n];
    }
}
