package lc.lc2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 单词拆分
*
* 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
示例 1：
输入: s = "leetcode", wordDict = ["leet", "code"]
输出: true
解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
示例 2：
输入: s = "applepenapple", wordDict = ["apple", "pen"]
输出: true
解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
     注意，你可以重复使用字典中的单词。
示例 3：
输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
输出: false
*
* 方法一：动态规划
我们定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i−1] 是否能被空格拆分成若干个字典中出现的单词。
* 从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i−1 的最后一个单词，看它是否出现在字典中以及除去这部分的字符串是否合法即可。
* 公式化来说，我们需要枚举 s[0..i−1] 中的分割点 j ，看 s[0..j−1] 组成的字符串 s_1（默认 j=0 时 s_1为空串）和 s[j..i−1] 组成的
* 字符串 s_2是否都合法，如果两个字符串均合法，那么按照定义 s_1和 s_2拼接成的字符串也同样合法。由于计算到 dp[i] 时我们已经计算
* 出了dp[0..i−1] 的值，因此字符串 s_1是否合法可以直接由 dp[j] 得知，剩下的我们只需要看 s_2是否合法即可，因此我们可以得出如下转移方程：
dp[i]=dp[j] && check(s[j..i−1])
其中 check(s[j..i−1]) 表示子串 s[j..i−1] 是否出现在字典中。
对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，
* */
//字符串s和wordDict作字典。判断是否可以用字典中单词拼出s 。不要求字典单词全使用，字典单词可重复使用
public class _139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict); //单词列表去重
        //dp[i]表示s前 i 个字符s[0..i−1] 是否能被拆分成若干个字典中的单词
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; //空字符串可以被拆分成单词表里的词
        for (int i = 1; i <= s.length(); i++) {//遍历s的每个字符
            //dp[i]=dp[j] && check(s[j..i−1])
            //check(s[j..i−1]) 表示s[j..i−1] 是否出现在字典中
            for (int j = 0; j < i; j++) { //因此j从0到i
                //如果dp[i]=dp[j] && check(s[j..i−1])为真，dp[i]就是true，继续找下一个i
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()]; //返回dp[s.length()]
    }
}
