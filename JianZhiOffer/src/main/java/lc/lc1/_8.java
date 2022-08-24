package lc.lc1;

/*
* 字符串转换整数 (atoi)
*
* 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数：
* 读入字符串并丢弃无用的前导空格
* 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
* 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
* 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
* 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。小于 −231 的整数应该被固定为 −231 ，
* 大于 231 − 1 的整数应该被固定为 231 − 1 。返回整数作为最终结果。
注意：
本题中的空白字符只包括空格字符 ' ' 。
除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
* */
public class _8 {
    public int myAtoi(String str) {
        int len = str.length();
        char[] charArray = str.toCharArray();// 转换成字符数组
        int index = 0;
        while (index < len && charArray[index] == ' ') { // 1、去除前导空格
            index++;
        }
        if (index == len) { // 2、如果已经遍历完成（针对极端用例 "     "）
            return 0;
        }
        int sign = 1; // 3、如果出现符号字符，仅第 1 个有效，并记录正负sign
        char firstChar = charArray[index];
        if (firstChar == '+') {
            index++;
        } else if (firstChar == '-') {
            index++;
            sign = -1;
        }
        int res = 0; // 4、将后续出现的数字字符进行转换，不能用 long 类型，这是题目说的
        while (index < len) {
            char currChar = charArray[index];
            if (currChar > '9' || currChar < '0') { // 4.1 先判断不合法的情况
                break;
            }
            // 题中说环境只能存储 32 位大小有符号数，因此要提前判断乘以 10 以后是否越界
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            res = res * 10 + sign * (currChar - '0'); // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            index++;
        }
        return res;
    }
}
