package lc.lc1;

import java.util.*;

/*
* 字母异位词分组
*
* 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
字母异位词是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
示例 1:
输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
示例 2:
输入: strs = [""]
输出: [[""]]
示例 3:
输入: strs = ["a"]
输出: [["a"]]
*
* 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，故可以将排序之后的字符串作为哈希表的键。
* */
public class _49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>(); //用map保存，key是排序之后的字符串，value是异位词
        for (String str : strs) { //遍历字符串数组
            char[] array = str.toCharArray(); //字符串转换成数组
            Arrays.sort(array); //字符数组排序
            String key = new String(array); //排序后的数组作为key
            //从map中找这个key是否有value，没有就初始化一个list
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str); //将str放入list
            map.put(key, list); //将list和key放入map
        }
        return new ArrayList<List<String>>(map.values()); //最后返回map的value这个双重数组
    }
}
