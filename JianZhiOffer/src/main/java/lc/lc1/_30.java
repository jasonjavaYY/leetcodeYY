package lc.lc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 串联所有单词的子串
*
* 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
示例 1：
输入：s = "barfoothefoobarman", words = ["foo","bar"]
输出：[0,9]
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。
示例 2：
输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
输出：[]
示例 3：
输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
输出：[6,9,12]
*
* 此题是「438. 找到字符串中所有字母异位词」的进阶版。不同的是第 438 题的元素是字母，而此题的元素是单词。
* 可以用类似「438. 找到字符串中所有字母异位词的官方题解」的方法二的滑动窗口来解这题。
记 words 的长度为 m，words 中每个单词的长度为 n，s 的长度为 ls。首先需要将 s 划分为单词组，每个单词的大小均为 n （首尾除外）。
* 这样的划分方法有 n 种，即先删去前 i （i=0∼n−1）个字母后，将剩下的字母进行划分，如果末尾有不到 n个字母也删去。
* 对这 n种划分得到的单词数组分别使用滑动窗口对 words 进行类似于「字母异位词」的搜寻。
* 划分成单词组后，一个窗口包含 s 中前 m 个单词，用一个哈希表 differ 表示窗口中单词频次和 words 中单词频次之差。
* 初始化 differ 时，出现在窗口中的单词，每出现一次，相应的值增加 1，出现在 words 中的单词，每出现一次，相应的值减少 1。
* 然后将窗口右移，右侧会加入一个单词，左侧会移出一个单词，并对 differ 做相应的更新。窗口移动时，若出现differ中值不为0的键的数量为 0，
* 则表示这个窗口中的单词频次和 words 中单词频次相同，窗口的左端点是一个待求的起始位置。划分的方法有n 种，做n 次滑动窗口后，
* 即可找到所有的起始位置。
* */
public class _30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>();
        int m = words.length, n = words[0].length(), ls = s.length();
        for (int i = 0; i < n; i++) {
            if (i + m * n > ls) {
                break;
            }
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {
                String word = s.substring(i + j * n, i + (j + 1) * n);
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }
            for (String word : words) {
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) {
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                    word = s.substring(start - n, start);
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;
    }
}
