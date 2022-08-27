package jzoffer;

//一个整数，输出该数二进制中0的个数。
public class _75 {
    public static int findZero(int n) {
        int count = 0;
        while (n != 0) { //循环判断n不为0
            if ((n & 1) != 1) //如果n&1不是1，代表找到了一个0，count++
                count++;
            n >>>= 1; //n右移一位
        }
        return count;
    }
}
