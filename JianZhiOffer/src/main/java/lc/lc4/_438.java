package lc.lc4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 找到字符串中所有字母异位词
*
* 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
示例 1:
输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
示例 2:
输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
*
* 方法一：滑动窗口
因为字符串 p 的异位词的长度一定与字符串 p 长度相同，所以可以在字符串 s 中构造一个长度为与字符串 p 的长度相同的滑动窗口，
* 并在滑动中维护窗口中每种字母的数量；当窗口中每种字母的数量与字符串 p 中每种字母的数量相同时，则说明当前窗口为字符串 p 的异位词。
我们可以使用数组来存储字符串 p 和滑动窗口中每种字母的数量。当字符串 s 长度小于字 p 长度时，s 中一定不存在p 的异位词。
* 但字符串 s 中无法构造长度与字符串 p 的长度相同的窗口，所以这种情况需要单独处理。
* */
//两个字符串s和p，找到s中所有p的异位词子串，返回子串起始索引列表
public class _438 {
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();//获取s和p的长度
        if (sLen < pLen) {//如果s比p短，肯定找不到，返回空数组
            return new ArrayList<Integer>();
        }
        List<Integer> ans = new ArrayList<Integer>();//建立结果数组
        int[] sCount = new int[26];//构造s和p中各字符出现次数表
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];//计算sCount和pCount各位值
            ++pCount[p.charAt(i) - 'a'];
        }//如果s和p所有字符出现次数都相同，ans中添加0，说明从s起始下标就可匹配
        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }//i从0到sl-pl，如果s剩余的字符数少于p的长度，肯定没法匹配异位词
        for (int i = 0; i < sLen - pLen; ++i) {
            //每移动一步，将s该位置字符次数-1，窗口右侧字符次数+1，更新sCount
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];
            //如果sCount和pCount相等，将i+1下标加入结果集
            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }
        return ans;//返回结果集
    }
}
