package jzoffer;

/*
 * 斐波那契数列
 *
 * 求斐波那契数列的第 n 项，n <= 39
 * */
public class _8 {
    public class Solution {
        private int[] fib = new int[40];

        public Solution() {
            fib[1] = 1;
            for (int i = 2; i < fib.length; i++)
                fib[i] = fib[i - 1] + fib[i - 2];
        }

        public int Fibonacci(int n) {
            return fib[n];
        }
    }
}
