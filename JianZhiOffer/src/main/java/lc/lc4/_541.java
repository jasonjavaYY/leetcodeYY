package lc.lc4;

/*
* 反转字符串 II
*
* 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
如果剩余字符少于 k 个，则将剩余字符全部反转。如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
示例 1：
输入：s = "abcdefg", k = 2
输出："bacdfeg"
示例 2：
输入：s = "abcd", k = 2
输出："bacd"
*
* 方法一：模拟
我们直接按题意进行模拟：反转每个下标从 2k 的倍数开始的，长度为 k 的子串。若该子串长度不足 k，则反转整个子串。
* */
//数组 给字符串s和整数k，每计数2k字符反转前k字符。如果剩字符少于k则全反转。如果小于2k但大于等于k个则反转前k字符
public class _541 {
    public String reverseStr(String s, int k) {
        int n = s.length();//计算字符串长度n
        char[] arr = s.toCharArray();//字符串转换为字符数组
        for (int i = 0; i < n; i += 2 * k) {//i+=2k
            //翻转从i到i+k(不包含)，注意判断i+k和n的较小值再-1
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);//返回翻转后的字符串
    }
    //翻转字符串的left到right元素
    public void reverse(char[] arr, int left, int right) {
        while (left < right) {//不断替换首尾元素，指针逐渐靠近
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
