package jzoffer;

public class _75 {
    //    输入一个整数，输出该数二进制表示中0的个数。
    public static int findZero(int n) {
        int count = 0;
        while(n != 0) {
            if((n&1)!=1)
                count++;
            n>>>=1;
        }
        return count;
    }
}
