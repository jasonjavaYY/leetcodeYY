package jzoffer;
/*
* 表示数值的字符串
*
* 使用正则表达式进行匹配。
[]  ： 字符集合
()  ： 分组
?   ： 重复 0 ~ 1 次
+   ： 重复 1 ~ n 次
*   ： 重复 0 ~ n 次
.   ： 任意字符
\\. ： 转义后的 .
\\d ： 数字
* */
//字符串 表示数值的字符串
public class _21 {
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0)
            return false;
        //开头可以有0或者1个+/-，接下来必须是任意多个数字
        //接下来必须是0或1个.加任意多数组
        //接下来必须是e/E加0或1和+/-再跟1到多个数字的组合，并且该组合只能出现0或1次
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }
}
