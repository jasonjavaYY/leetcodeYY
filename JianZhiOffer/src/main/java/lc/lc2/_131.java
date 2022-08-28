package lc.lc2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 分割回文串
*
* 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文串 。返回 s 所有可能的分割方案。
回文串 是正着读和反着读都一样的字符串。
示例 1：
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
示例 2：
输入：s = "a"
输出：[["a"]]
*
* 方法一：回溯 + 动态规划预处理
由于需要求出字符串 s 的所有分割方案，因此我们考虑使用搜索 + 回溯的方法枚举所有可能的分割方法并进行判断。
假设我们当前搜索到字符串的第 i 个字符，且 s[0..i−1] 位置的所有字符已经被分割成若干个回文串，并且分割结果被放入了答案数组 ans 中，
* 那么我们就需要枚举下一个回文串的右边界 j，使得 s[i..j] 是一个回文串。因此，我们可以从 i 开始，从小到大依次枚举 j。
* 对于当前枚举的 j 值，我们使用双指针的方法判断 s[i..j] 是否为回文串：如果 s[i..j] 是回文串，那么就将其加入答案数组 ans 中，
* 并以 j+1 作为新的 i 进行下一层搜索，并在未来的回溯时将 s[i..j] 从 ans 中移除。
如果我们已经搜索完了字符串的最后一个字符，那么就找到了一种满足要求的分割方法。
细节
当我们在判断 s[i..j] 是否为回文串时，常规的方法是使用双指针分别指向 i 和 j，每次判断两个指针指向的字符是否相同，直到两个指针相遇。
* 然而这种方法会产生重复计算，因此，我们可以将字符串 s 的每个子串 s[i..j] 是否为回文串预处理出来，使用动态规划即可。
* 设 f(i,j) 表示 s[i..j] 是否为回文串，那么有状态转移方程：
f(i,j)={     True,                      i≥j
​	         f(i+1,j−1)∧(s[i]=s[j]),   otherwise
其中 ∧ 表示逻辑与运算，即 s[i..j] 为回文串，当且仅当其为空串（i>j），其长度为 1（i=j），或者首尾字符相同且 s[i+1..j−1] 为回文串。
预处理完成之后，我们只需要 O(1) 的时间就可以判断任意 s[i..j] 是否为回文串了。
* */
//字符串s，将s分割成一些子串，使每个子串都是回文。返回所有可能分割方案
public class _131 {
    boolean[][] f; //f(i,j)表示s[i..j]是否为回文串
    //存放结果数组，是一个二层数组
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>(); //存放一种可能的分割方式
    int n; //保存输入字符串长度

    public List<List<String>> partition(String s) {
        n = s.length(); //计算字符串长度n
        f = new boolean[n][n]; //构造f
        for (int i = 0; i < n; ++i) { //最开始都填充为true
            Arrays.fill(f[i], true);
        }
        //i≥j代表空串或单字符的串，为true
        //f(i,j)={     True,                      i≥j
        //​	           f(i+1,j−1)∧(s[i]=s[j]),   otherwise
        for (int i = n - 1; i >= 0; --i) {//i逆向遍历
            for (int j = i + 1; j < n; ++j) {
                //j从i+1开始，利用状态方程更新f[i][j]
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }
        dfs(s, 0); //从第0位开始搜索字符串s
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) { //如果i=n代表遍历完了原字符串
            ret.add(new ArrayList<String>(ans)); //将ans放入ret返回
            return;
        }
        for (int j = i; j < n; ++j) {//否则j从i开始
            if (f[i][j]) { //如果i到j是回文串，就将i到j子串加入ans
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1); //继续回溯j+1
                ans.remove(ans.size() - 1);//回退时删除ans最后一个串
            }
        }
    }
}
