package jzoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 字符串的排列
 *
 * 输入一个字符串，按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串 abc，则打印出由字符 a, b, c 所能排列出来的所有字符串
 *  abc, acb, bac, bca, cab 和 cba。
 * */
//回溯 一个字符串按字典序打印该字符串字符的所有排列
public class _411 {
    static List<List<Integer>> ret = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(Permutation(nums));
    }

    public static List<List<Integer>> Permutation(int[] str) {
        if (str.length == 0) return ret; //如果字符串长度为0，直接返回空数组
        int[] chars = str; //字符串转换为字符数组
        Arrays.sort(chars); //字符数组排序
        List<Integer> combine = new ArrayList<>();
        backtracking(chars, new boolean[chars.length], combine); //回溯排序后的字符数组
        return ret;
    }

    //回溯法
    private static void backtracking(int[] chars, boolean[] hasUsed, List<Integer> combine) {
        //如果拼接字符串s长度和chars长度相等，说明拼好了一个结果字符串，放入ret
        if (combine.size() == chars.length) {
            ret.add(combine);
        }
        for (int i = 0; i < chars.length; i++) {
            //判断第i个位置字符串是否用过
            if (hasUsed[i]) continue;
            //将第i个字符设置为已使用，将字符拼接到s
            hasUsed[i] = true;
            combine.add(chars[i]);
            backtracking(chars, hasUsed, combine);  //继续向下一个字符回溯
            //某一条路径走不通了，将s的最后一个字符剔除并将第i位置设置为没用过
            combine.remove(combine.size() - 1);
            hasUsed[i] = false;
        }
    }
}
