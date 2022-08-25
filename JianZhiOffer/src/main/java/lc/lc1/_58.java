package lc.lc1;

/*
* 最后一个单词的长度
*
* 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
示例 1：
输入：s = "Hello World"
输出：5
解释：最后一个单词是“World”，长度为5。
示例 2：
输入：s = "   fly me   to   the moon  "
输出：4
解释：最后一个单词是“moon”，长度为4。
示例 3：
输入：s = "luffy is still joyboy"
输出：6
解释：最后一个单词是长度为6的“joyboy”。
*
* 方法一：反向遍历
题目要求得到字符串中最后一个单词的长度，可以反向遍历字符串，寻找最后一个单词并计算其长度。
由于字符串中至少存在一个单词，因此字符串中一定有字母。首先找到字符串中的最后一个字母，该字母即为最后一个单词的最后一个字母。
从最后一个字母开始反向遍历字符串，直到遇到空格或者到达字符串起始位置。遍历到的每个字母都是最后一个单词中的字母，遍历到的字母数量即为最后一个单词的长度。
* */
public class _58 {
    public int lengthOfLastWord(String s) {
        int index = s.length() - 1; //获取传入字符串长度
        while (s.charAt(index) == ' ') { //从后往前，如果字符是空格，index--
            index--;
        }
        int wordLength = 0;
        //从index开始往前，如果字符不是空格，就把单词长度+1，index--
        while (index >= 0 && s.charAt(index) != ' ') {
            wordLength++;
            index--;
        }
        return wordLength; //返回最后一个单词长度
    }
}
