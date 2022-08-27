package lc.lc3;

import java.util.HashMap;
import java.util.Map;

/*
* 单词规律
*
* 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
示例1:
输入: pattern = "abba", s = "dog cat cat dog"
输出: true
示例 2:
输入:pattern = "abba", s = "dog cat cat fish"
输出: false
示例 3:
输入: pattern = "aaaa", s = "dog cat cat dog"
输出: false
*
* 方法一：哈希表
我们需要判断字符与字符串之间是否恰好一一对应。即任意一个字符都对应着唯一的字符串，任意一个字符串也只被唯一的一个字符对应。
在集合论中，这种关系被称为「双射」。利用哈希表记录每一个字符对应的字符串，以及每一个字符串对应的字符。然后我们枚举每一对字符与字符串的配对过程，
* 不断更新哈希表，如果发生了冲突，则说明给定的输入不满足双射关系。在实际代码中，我们枚举 pattern 中的每一个字符，利用双指针来均摊线性地找
* 到该字符在 str 中对应的字符串。每次确定一个字符与字符串的组合，我们就检查是否出现冲突，最后我们再检查两字符串是否比较完毕即可。
* */
//给一种规律pattern和一个字符串s ，判断 s 是否遵循相同规律
public class _290 {
    public boolean wordPattern(String pattern, String str) {
        //建立两个映射表，字符串到字符，字符到字符串
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length(); //单词字符串长度m
        int i = 0;//代表单词起始下标
        for (int p = 0; p < pattern.length(); ++p) {//遍历模式串
            char ch = pattern.charAt(p);//获取模式串每个位置字符
            if (i >= m) {//如果单词起始下标超出单词字符串长度m，返回false
                return false;
            }
            int j = i; //j是单词结束下标
            while (j < m && str.charAt(j) != ' ') {//找到字符串中对应单词的结束下标
                j++;
            }
            String tmp = str.substring(i, j); //获取单词
            //如果str2ch映射包含该单词但该单词对应的字符不是ch返回false
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }//如果ch2str映射包含该字符但该字符对应的单词不是tmp返回false
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);//否则说明没找到映射，将映射关系放入两个表
            ch2str.put(ch, tmp);
            i = j + 1;//重置i位置为当前单词最后一个字母后一位
        }
        return i >= m;//返回i是否≥m，即单词字符串都被遍历完了
    }
}
