package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 括号生成
*
* 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
示例 1：
输入：n = 3
输出：["((()))","(()())","(())()","()(())","()()()"]
示例 2：
输入：n = 1
输出：["()"]
*
* 方法三：按括号序列的长度递归
任何一个括号序列都一定是由 ‘(’ 开头，并且第一个 ‘(’ 一定有一个唯一与之对应的 ‘)’。这样一来，每一个括号序列可以用 (a)b 来表示，
* 其中 a 与 b 分别是一个合法的括号序列（可以为空）。那么，要生成所有长度为2n 的括号序列，我们定义一个函数 generate(n)返回所有可能的括号序列。
* 那么在函数 generate(n)的过程中：我们需要枚举与第一个 ‘(’ 对应的 ‘)’ 的位置 2i+1；
* 递归调用 generate(i) 即可计算 a 的所有可能性；递归调用 generate(n−i−1) 即可计算 b 的所有可能性；
* 遍历 a 与 b 的所有可能性并拼接，即可得到所有长度为 2n 的括号序列。为了节省计算时间，我们在每次 generate(i) 函数返回之前，
* 把返回值存储起来，下次再调用 generate(i) 时可以直接返回，不需要再递归计算。
* */
//递归 数字n代表括号对数，请生成所有可能的且有效的括号组合
public class _22 {
    ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) { //如果缓存数组n位不为空，返回
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>(); //存放结果数组
        if (n == 0) ans.add(""); //如果n=0，返回空字符串
        else {
            for (int c = 0; c < n; ++c) { //c从0开始
                for (String left : generate(c)) { //左半部分是generate(c)
                    //右半部分是generate(n-1-c)
                    for (String right : generate(n - 1 - c)) {
                        //再ans中拼接(left)right
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans; //将ans赋予cache[n]
        return ans; //返回ans
    }

    public List<String> generateParenthesis(int n) {
        return generate(n);
    }
}
