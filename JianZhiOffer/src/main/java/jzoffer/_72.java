package jzoffer;
/*
* 把字符串转换成整数
*
* 将一个字符串转换成一个整数，字符串不是一个合法的数值则返回 0，要求不能使用字符串转换整数的库函数。
Iuput:
+2147483647
1a33

Output:
2147483647
0
* */
public class _72 {
    public int StrToInt(String str) {
        //如果字符串为空，返回0
        if (str == null || str.length() == 0)
            return 0;
        //如果字符串以-开头，就是负数
        boolean isNegative = str.charAt(0) == '-';
        int ret = 0;
        for (int i = 0; i < str.length(); i++) {
            //遍历字符数组，如果是最开始有+或者-，就代表符号，进行下一轮循环
            char c = str.charAt(i);
            if (i == 0 && (c == '+' || c == '-'))  /* 符号判定 */
                continue;
                //如果字符小宇0或者大于9，就是非法字符，直接返回0
            if (c < '0' || c > '9')                /* 非法输入 */
                return 0;
                //否则就不停更新结果
            ret = ret * 10 + (c - '0');
        }
        return isNegative ? -ret : ret;  //返回带符号的结果
    }
}
