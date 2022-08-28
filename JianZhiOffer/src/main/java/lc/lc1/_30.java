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
* 滑动窗口。
记 words的长度为 m，words 中每个单词的长度为 n，s 的长度为 ls。首先需要将 s 划分为单词组，每个单词的大小均为 n （首尾除外）。
* 这样的划分方法有 n 种，即先删去前 i （i=0∼n−1）个字母后，将剩下的字母进行划分，如果末尾有不到 n个字母也删去。
* 对这 n种划分得到的单词数组分别使用滑动窗口对 words 进行类似于「字母异位词」的搜寻。
* 划分成单词组后，一个窗口包含 s 中前 m 个单词，用一个哈希表 differ 表示窗口中单词频次和 words 中单词频次之差。
* 初始化 differ 时，出现在窗口中的单词，每出现一次，相应的值增加 1，出现在 words 中的单词，每出现一次，相应的值减少 1。
* 然后将窗口右移，右侧会加入一个单词，左侧会移出一个单词，并对 differ 做相应的更新。窗口移动时，若出现differ中值不为0的键的数量为 0，
* 则表示这个窗口中的单词频次和 words 中单词频次相同，窗口的左端点是一个待求的起始位置。划分的方法有n 种，做n 次滑动窗口后，
* 即可找到所有的起始位置。
* */
//滑动窗口 字符串s和一些长度相同单词words。找出s恰好由words中所有单词串联形成的子串起始位置。不考虑单词顺序
public class _30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<Integer>(); //存放结果数组
        //m是单词个数，n是每个单词长度，ls是字符串长度
        int m = words.length, n = words[0].length(), ls = s.length();
        //将s分为单词组，每个单词大小均n。划分方法有n种，即先删去前i（i=0∼n−1）个字母，将剩下字母划分
        //因为删去前n和字母和删0个字母对后面的单词来说效果一样，相当于少得到一个单词
        for (int i = 0; i < n; i++) {  //所以i从0到n-1   对于n种划分得到的所有窗口列表遍历
            if (i + m * n > ls) {  //如果i+mn超过ls退出
                break;
            } //differ表示窗口中单词频次和words中单词频次之差
            Map<String, Integer> differ = new HashMap<String, Integer>();
            for (int j = 0; j < m; j++) {  //一个窗口包含s中前m个单词，因此j从0到m
                //依次取出m个长n的字符串(单词)
                String word = s.substring(i + j * n, i + (j + 1) * n);
                //将单词放入differ，记录各个单词在当前窗口出现的次数
                differ.put(word, differ.getOrDefault(word, 0) + 1);
            }  //遍历words中的单词，将其加入differ
            for (String word : words) {
                //differ中某个单词的value为正，代表当前子串多了这个单词，
                //为负代表子串还缺少words中这个单词，为0代表正好匹配了words中这个单词个数
                //如果differ所有key的value都为0，代表找到了一条子串由words单词构成
                differ.put(word, differ.getOrDefault(word, 0) - 1);
                if (differ.get(word) == 0) {
                    differ.remove(word);
                }
            }//移动窗口，start代表窗口起始点，每次start+n(即右移一个单词)
            for (int start = i; start < ls - m * n + 1; start += n) {
                if (start != i) { //start!=i代表移动过窗口，右侧加个单词，左侧出个单词，要更新differ
                    //进来的单词是 start+(m-1)*n 到 start+m*n
                    String word = s.substring(start + (m - 1) * n, start + m * n);
                    //进来一个单词，要给word的value+1
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                    if (differ.get(word) == 0) { //如果该单词次数为0，将其移除
                        differ.remove(word);
                    } //出去的单词是start-n 到 start
                    word = s.substring(start - n, start);
                    //出去的单词，要给word的value-1
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    if (differ.get(word) == 0) { //如果该单词次数为0，将其移除
                        differ.remove(word);
                    }
                } //每移动一次窗口判断differ是否为空，如果空就把start加入res，因为从该位置开始的子串符合要求
                if (differ.isEmpty()) {
                    res.add(start);
                }
            }
        }
        return res;//最后返回结果
    }
}
