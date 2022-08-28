package lc.lc1;

/*
* 最长公共前缀
*
* 编写一个函数来查找字符串数组中的最长公共前缀。
如果不存在公共前缀，返回空字符串 ""。
示例 1：
输入：strs = ["flower","flow","flight"]
输出："fl"
示例 2：
输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
*
* 方法二：纵向扫描
* 纵向扫描时，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，如果相同则继续对下一列进行比较，
* 如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
* */
//字符串 查找字符串数组中最长公共前缀。如不存在返回空字符串
public class _14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) { //如果传入字符串数组位空，返回空
            return "";
        }
        //随便找一个字符串长度遍历，如果strs[0]是最长的，遍历其他字符串时肯定不需要遍历这么长
        //如果strs[0]是最短的，那最多只需要比较strs[0]的长度个位数
        int length = strs[0].length();
        int count = strs.length; //获取一共有多少个字符串
        for (int i = 0; i < length; i++) { //从第0位开始纵向比较
            char c = strs[0].charAt(i); //获取第一个字符串i位字符
            for (int j = 1; j < count; j++) { //遍历所有字符串的第i位，如果达到了长度或者这一位字符不同，直接返回
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0]; //如果遍历结束都没返回，说明strs[0]就是最短的且其余字符串前缀全包含strs[0]，返回strs[0]
    }
}
