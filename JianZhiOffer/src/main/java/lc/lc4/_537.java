package lc.lc4;

/*
* 复数乘法
*
* 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
实部 是一个整数，取值范围是 [-100, 100]
虚部 也是一个整数，取值范围是 [-100, 100],i^2 == -1,给你两个字符串表示的复数 num1 和 num2 ，请你返回表示它们乘积的字符串。
示例 1：
输入：num1 = "1+1i", num2 = "1+1i"
输出："0+2i"
解释：(1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
示例 2：
输入：num1 = "1+-1i", num2 = "1+-1i"
输出："0+-2i"
解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
*
* 方法一：模拟
复数可以写成 a+bi 形式，其中 a,b∈R，a 是实部，b 是虚部，i 是虚数单位，i^2 = -1,对于给定的两个复数 num1和 num2，首先分别得到实部和虚部，
* 然后计算两个复数的乘法。用 real1和 imag1表示 num1的实部和虚部，real2和 imag2表示 num2的实部和虚部，则两个复数的乘法计算如下：
(real1+imag1×i)×(real2+imag2×i) = real1×real2+real1×imag2×i+imag1×real2×i+imag1×imag2×i^2
*                               = ​(real1×real2−imag1×imag2)+(real1×imag2+imag1×real2)×i
得到两个复数的乘积之后，将乘积转换成复数格式的字符串并返回。
* */
//数学 复数用字符串表示，"实部+虚部i"，实部是一整数，虚部也是一整数，给两个复数num1和num2，返回表示乘积的字符串
public class _537 {
    public String complexNumberMultiply(String num1, String num2) {
        //将字符串按+或i切割得到 实部 虚部数组
        String[] complex1 = num1.split("\\+|i");
        String[] complex2 = num2.split("\\+|i");
        //解析得到整形的 实部1 虚部1 实部2 虚部2
        int real1 = Integer.parseInt(complex1[0]);
        int imag1 = Integer.parseInt(complex1[1]);
        int real2 = Integer.parseInt(complex2[0]);
        int imag2 = Integer.parseInt(complex2[1]);
        //最后结果是​(real1×real2−imag1×imag2)+(real1×imag2+imag1×real2)×i
        return String.format("%d+%di", real1 * real2 - imag1 * imag2, real1 * imag2 + imag1 * real2);
    }
}
