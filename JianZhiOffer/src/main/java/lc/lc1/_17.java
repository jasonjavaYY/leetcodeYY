package lc.lc1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 电话号码的字母组合
*
* 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
* 示例 1：
输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
示例 2：
输入：digits = ""
输出：[]
示例 3：
输入：digits = "2"
输出：["a","b","c"]
*
* 方法一：回溯
首先使用哈希表存储每个数字对应的所有可能的字母，然后进行回溯操作。
回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
* 该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，并将其中的一个字母插入到
* 已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，即得到一个完整的字母排列。
* 然后进行回退操作，遍历其余的字母排列。回溯算法用于寻找所有的可行解，如果发现一个解不可行，则会舍弃不可行的解。
* 在这道题中，由于每个数字对应的每个字母都可能进入字母组合，因此不存在不可行的解，直接穷举所有的解即可。
* */
//回溯 一个仅含数字 2-9 的字符串，返回所有能表示的字母组合
public class _17 {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>(); //记录字符串数组结果
        if (digits.length() == 0) { //如果字符串数组为空，返回空结果集
            return combinations;
        } //构造数字到字母映射表
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");put('3', "def");put('4', "ghi");put('5', "jkl");
            put('6', "mno");put('7', "pqrs");put('8', "tuv");put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());//回溯
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap,
                          String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {//如果index等于传入字符串长度，表明找到一个可能结果，放入结果集
            combinations.add(combination.toString());
        } else { //否则获取index位置数字
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit); //获取该数字对应字母
            int lettersCount = letters.length(); //计算对应的字母个数
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));//针对每个字母，将该字母追加到结果字符串
                backtrack(combinations, phoneMap, digits, index + 1, combination); //递归index+1
                combination.deleteCharAt(index); //回溯的时候要删除结果的index位置字符
            }
        }
    }
}
