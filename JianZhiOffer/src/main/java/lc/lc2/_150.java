package lc.lc2;

import java.util.Deque;
import java.util.LinkedList;

/*
* 逆波兰表达式求值
*
* 根据 逆波兰表示法，求表达式的值。
有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
注意 两个整数之间的除法只保留整数部分。
可以保证给定的逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
示例 1：
输入：tokens = ["2","1","+","3","*"]
输出：9
解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
示例 2：
输入：tokens = ["4","13","5","/","+"]
输出：6
解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
示例 3：
输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
输出：22
解释：该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
*
* 前言
逆波兰表达式的特点是：没有括号，运算符总是放在和它相关的操作数之后。因此，逆波兰表达式也称后缀表达式。
方法一：栈
逆波兰表达式严格遵循「从左到右」运算。计算逆波兰表达式的值时，使用一个栈存储操作数，从左到右遍历逆波兰表达式，进行如下操作：
如果遇到操作数，则将操作数入栈；如果遇到运算符，将两个操作数出栈，先出栈的是右操作数，后出栈的是左操作数，使用运算符对两个数运算，新操作数入栈。
整个逆波兰表达式遍历完毕之后，栈内只有一个元素，该元素即为逆波兰表达式的值。
* */
public class _150 {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();//栈存储操作数
        int n = tokens.length; //计算传入波兰式长度n
        for (int i = 0; i < n; i++) {//遍历每个字符
            String token = tokens[i];
            if (isNumber(token)) {//如果字符是数字直接转换成整形入栈
                stack.push(Integer.parseInt(token));
            } else {//否则是表达式，弹出栈顶两个元素
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {//针对表达式对栈顶两元素计算，结果入栈
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();//最后返回栈顶元素就是结果
    }

    public boolean isNumber(String token) {//不是+-*/就是数字
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
}
