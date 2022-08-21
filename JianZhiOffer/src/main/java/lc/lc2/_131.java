package lc.lc2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 分割回文串
*
* 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
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
public class _131 {
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }
}
