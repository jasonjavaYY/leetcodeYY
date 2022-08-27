package lc.lc3;

import java.util.ArrayList;
import java.util.List;

/*
* 删除无效的括号
*
* 给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。返回所有可能的结果。答案可以按任意顺序返回。
示例 1：
输入：s = "()())()"
输出：["(())()","()()()"]
示例 2：
输入：s = "(a)())()"
输出：["(a())()","(a)()()"]
示例 3：
输入：s = ")("
输出：[""]
*
* 背景知识
如果当前遍历到的「左括号」的数目严格小于「右括号」的数目则表达式无效。因此，我们可以遍历一次输入字符串，统计「左括号」和「右括号」出现的次数。
当遍历到「左括号」的时候：「左括号」数量加 1。当遍历到「右括号」的时候：如果此时「左括号」的数量不为 0，「右括号」可以与之前遍历到的「左括号」匹配，
* 此时「左括号」出现的次数 −1；如果此时「左括号」的数量为 0，「右括号」数量加 1。得到的「左括号」和「右括号」的数量就是各自最少应该删除的数量。
* 思路与算法
使用回溯算法,首先我们利用括号匹配的规则求出该字符串 s 中最少需要去掉的左括号的数目 lremove 和右括号的数目 rremove，
* 然后我们尝试在原字符串 s 中去掉 lremove 个左括号和 rremove 个右括号，然后检测剩余的字符串是否合法匹配，如果合法匹配则我们则认为
* 该字符串为可能的结果，我们利用回溯算法来尝试搜索所有可能的去除括号的方案。在进行回溯时可以利用以下的剪枝技巧来增加搜索的效率：
我们从字符串中每去掉一个括号，则更新 lremove 或者 rremove，当我们发现剩余未尝试的字符串的长度小于 lremove+rremove 时，则停止本次搜索。
当 lremove 和 rremove 同时为 0 时，则我们检测当前的字符串是否合法匹配，如果合法匹配则我们将其记录下来。
由于记录的字符串可能存在重复，因此需要对重复的结果进行去重，去重的办法有如下两种：
利用哈希表对最终生成的字符串去重。
我们在每次进行搜索时，如果遇到连续相同的括号我们只需要搜索一次即可，比如当前遇到的字符串为 "(((())"，去掉前四个左括号中的任意一个，
* 生成的字符串是一样的，均为 "((())"，因此我们在尝试搜索时，只需去掉一个左括号进行下一轮搜索，不需要将前四个左括号都尝试一遍。
* */
//一个由若干括号和字母组成的字符串s，删除最小数量无效括号使输入字符串有效。返回所有可能结果
public class _301 {
    private List<String> res = new ArrayList<String>(); //存放结果

    public List<String> removeInvalidParentheses(String s) {
        //如果左括号数严格小于右括号则表达式无效。因此统计左右括号次数。
        //遍历到左括号时lremove加1。遍历到右括号时如果lremove不为0就−1；如果为0，rremove加1。得到值就是各自最少删除数
        int lremove = 0;
        int rremove = 0;
        for (int i = 0; i < s.length(); i++) { //计算需要删除的左右括号数
            if (s.charAt(i) == '(') {
                lremove++;
            } else if (s.charAt(i) == ')') {
                if (lremove == 0) {
                    rremove++;
                } else {
                    lremove--;
                }
            }
        }//执行删除操作
        helper(s, 0, lremove, rremove);
        return res;//返回结果
    }

    private void helper(String str, int start, int lremove, int rremove) {
        if (lremove == 0 && rremove == 0) {
            if (isValid(str)) {
                res.add(str);
            }//如果待删除的左右括号数都是0，且str有效就将str加入res并返回
            return;
        }//否则从start开始遍历字符串
        for (int i = start; i < str.length(); i++) {//如果i不是start且i和i-1字符相同，下一轮循环
            if (i != start && str.charAt(i) == str.charAt(i - 1)) {
                continue;
            }
            // 如果剩余的字符无法满足去掉的数量要求，直接返回
            if (lremove + rremove > str.length() - i) {
                return;
            }
            // 如果还需要删除左括号且当前字符是(，将第i个字符去掉，继续回溯i，l-1
            if (lremove > 0 && str.charAt(i) == '(') {
                helper(str.substring(0, i) + str.substring(i + 1), i, lremove - 1, rremove);
            }
            // 如果还需要删除右括号且当前字符是)，将第i个字符去掉，继续回溯i，r-1
            if (rremove > 0 && str.charAt(i) == ')') {
                helper(str.substring(0, i) + str.substring(i + 1), i, lremove, rremove - 1);
            }
        }
    }

    //ok  判断某个字符串是否有效
    private boolean isValid(String str) {
        int cnt = 0; //计算str的左右括号数，如果相等就返回true
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                cnt++;
            } else if (str.charAt(i) == ')') {
                cnt--;
                if (cnt < 0) {
                    return false;
                }
            }
        }
        return cnt == 0;
    }
}
