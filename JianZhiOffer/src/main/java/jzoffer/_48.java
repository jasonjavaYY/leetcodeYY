package jzoffer;

import java.util.Arrays;

/*
* 把数组排成最小的数
*
* 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
* 打印能拼接出的所有数字中最小的一个。例如输入数组 {3，32，321}，
* 则打印出这三个数字能排成的最小数字为 321323。
*
* 可以看成是一个排序问题，在比较两个字符串 S1 和 S2 的大小时，
* 应该比较的是 S1+S2 和 S2+S1 的大小，如果 S1+S2 < S2+S1，那么应该把 S1 排在前面，否则应该把 S2 排在前面。
* */
//数组 输入一个正整数数组，把数组所有数字拼成一个数，打印最小的一个。如输入数{3，32，321}，则打印的最小数字为321323
public class _48 {
    public String PrintMinNumber(int[] numbers) {
        //如果数组为空，返回空
        if (numbers == null || numbers.length == 0)
            return "";
        int n = numbers.length; //数组长度
        String[] nums = new String[n]; //构造字符数组
        //遍历数组，将数组转变成字符串
        for (int i = 0; i < n; i++)
            nums[i] = numbers[i] + "";
        //比较s1+s2和s2+s1，如果s2+s1大，就返回s1，s2顺序
        Arrays.sort(nums, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        //遍历排序后的字符串数组，拼接
        for (String str : nums)
            ret += str;
        return ret;
    }
}
