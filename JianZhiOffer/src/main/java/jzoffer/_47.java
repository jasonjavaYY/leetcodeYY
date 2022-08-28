package jzoffer;
/*
* 从 1 到 n 整数中 1 出现的次数
* */
//数组 从 1 到 n 整数中 1 出现的次数
public class _47 {
    public static int NumberOf1Between1AndN_Solution(int n) {
        //假设n=76
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) { //m从1开始，小于等于n，每次*10
            //a=n/m  b=n%m
            int a = n / m, b = n % m;
            //每次增加a+8再除10再*m再加，如果a%10==1就加b+1，否则是0
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1Between1AndN_Solution(76));
    }
}
