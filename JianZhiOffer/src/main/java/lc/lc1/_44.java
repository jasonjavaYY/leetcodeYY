package lc.lc1;

/*
* 通配符匹配
*
* 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。'?' 可以匹配任何单个字符。'*' 可以匹配任意字符串（包括空字符串）。
两个字符串完全匹配才算匹配成功。s 可能为空，且只包含从 a-z 的小写字母。p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
示例 2:
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
示例 3:
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
示例 4:
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
示例 5:
输入:
s = "acdcb"
p = "a*c?b"
输出: false
*
* 方法一：动态规划
在给定的模式 p 中，只会有三种类型字符：a−z，可以匹配对应的一个字母；问号 ?可以匹配任意一个字母；星号 ∗，可以匹配零或任意多个小写字母。
其中「小写字母」和「问号」的匹配是确定的，而「星号」匹配是不确定的，因此我们需要枚举所有的匹配情况。为了减少重复枚举，使用动态规划来解决。
用 dp[i][j] 表示字符串 s 的前 i 个字符和模式 p 的前 j 个字符是否能匹配。在进行状态转移时，我们可以考虑模式 p 的第 j 个字符 p_j，
* 与之对应的是字符串 s 中的第 i 个字符 s_i：
①如果 p_j是小写字母，那么 s_i必须也为相同的小写字母，状态转移方程为：
dp[i][j]=(si与 pj相同)∧dp[i−1][j−1]，∧ 表示与运算。也就是说，dp[i][j] 为真，当且仅当dp[i−1][j−1] 为真，并且 s_i与 p_j相同。
②如果 p_j是问号，那么对 s_i没有任何要求，状态转移方程为：dp[i][j]=dp[i−1][j−1]
③如果 p_j是星号，那么同样对 s_i没有任何要求，但是星号可以匹配零或任意多个小写字母，因此状态转移方程分为两种情况，即使用或不使用这个星号：
dp[i][j]=dp[i][j−1]∨dp[i−1][j]，其中 ∨ 表示或运算。如果不使用这个星号，那么就会从 dp[i][j−1] 转移来；如果使用这个星号，就会从dp[i−1][j]转移来。
最终的状态转移方程如下：
dp[i][j]=    (si与 pj相同)∧dp[i−1][j−1],    pj是小写字母
             dp[i−1][j−1],                  pj是问号
             dp[i][j−1]∨dp[i−1][j],        pj是星号
我们也可以将前两种转移进行归纳：
dp[i][j]=    dp[i−1][j−1],                 si与 pj相同或者 pj是问号
             dp[i][j−1]∨dp[i−1][j],        pj是星号
             False,                        其它情况
只有确定了边界条件，才能进行动态规划。在上述的状态转移方程中，由于dp[i][j] 对应着 s 的前 i 个字符和模式 p 的前 j 个字符，
* 因此所有的 dp[0][j] 和 dp[i][0] 都是边界条件，因为它们涉及到空字符串或者空模式情况，这是我们在状态转移方程中没有考虑到的：
dp[0][0]=True，即当字符串 s 和模式 p 均为空时，匹配成功；
dp[i][0]=False，即空模式无法匹配非空字符串；
dp[0][j] 需要分情况讨论：因为星号才能匹配空字符串，所以只有当模式 p 的前 j 个字符均为星号时，dp[0][j] 才为真。
所以dp[i][0] 恒为假，dp[0][j] 在 j 大于模式 p 的开头出现的星号字符个数之后，也恒为假，而 dp[i][j]的默认值（其它情况）也为假，
* 因此在对动态规划的数组初始化时，我们就可以将所有的状态初始化为 False。最终的答案即为 dp[m][n]，其中m 和n 分别是字符串s 和模式p 的长度。
* 需要注意的是，由于大部分语言中字符串的下标从 00 开始，因此 s_i和 p_j分别对应着 s[i−1] 和 p[j−1]。
* */
public class _44 {
    //同jzoffer的_20
}
