package lc.lc3;

import java.util.Arrays;

/*
* 有效的字母异位词
*
* 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
示例 1:
输入: s = "anagram", t = "nagaram"
输出: true
示例 2:
输入: s = "rat", t = "car"
输出: false
*
* 方法一：排序
t 是 s 的异位词等价于「两个字符串排序后相等」。因此我们可以对字符串 s 和 t 分别排序，看排序后的字符串是否相等即可判断。
* 此外，如果 s 和 t 的长度不同，t 必然不是 s 的异位词。
* */
//给两个字符串s和t，判断t是否是s的字母异位词。若s和t中每个字符出现次数都相同则是字母异位词
public class _242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {//如果s和t长度不等，返回false
            return false;
        }
        char[] str1 = s.toCharArray();//s和t转换成字符数组后排序
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);//判断排序后数组是否相同
    }
}
