package jzoffer;

import java.util.Arrays;

/*
* 最长不含重复字符的子字符串
*
* 输入一个字符串（只包含 a~z 的字符），求其最长不含重复字符的子字符串的长度。
* 例如对于 arabcacfr，最长不含重复字符的子字符串为 acfr，长度为 4。
* */
//字符串 输入一个字符串（只包含 a~z），求最长不含重复字符的子串长度
public class _51 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring(" "));
    }
    public static int lengthOfLongestSubstring(String s) {
        int curLen = 0; //curLen代表当前统计的最长字符长度  maxLen代表历史最大字符长度
        int maxLen = 0;
        if (s == null || s.equals("")){
            return 0;
        }
        int[] preIndexs = new int[256]; //构造26的整形数组，代表某字符上次出现的下标
        Arrays.fill(preIndexs, -1);  //整形数组初值设为-1
        for (int curI = 0; curI < s.length(); curI++) {
            //遍历字符串，计算每个字符和a的差值，因为只能是a-z，所以c>=0
            int c = s.charAt(curI);
            int preI = preIndexs[c]; //获取上一次出现该字符的下标
            //preI=-1代表当前字符之前没出现过，所以curLen要+1
            //curI - preI > curLen代表虽然字符现在重复了，但上次出现的位置在当前统计字符串首前
            //所以仍要curLen+1且不重置当前字符串头，如统计到"acfr"的r时发现r重复，但上一r下标是1
            //“acfr”的字符串首下标是5，所以要把r加进来，curLen要++
            if (preI == -1 || curI - preI > curLen) {
                curLen++;
            } else {
                //每次遇到重复字符且curI-preI<=curLen时，就可能更新最大Len，
                //且一定要重新设置当前字符串头
                maxLen = Math.max(maxLen, curLen);
                curLen = curI - preI;
            }
            preIndexs[c] = curI; //更新字符c上一次出现的位置
        }
        maxLen = Math.max(maxLen, curLen); //求最大长度
        return maxLen;
    }
}
