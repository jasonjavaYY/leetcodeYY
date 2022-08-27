package jzoffer;

import java.util.ArrayList;

/*
* 和为 S 的连续正数序列
*
* 输出所有和为 S 的连续正数序列。例如和为 100 的连续序列有：
[9, 10, 11, 12, 13, 14, 15, 16]
[18, 19, 20, 21, 22]。
* */
//输出所有和为 S 的连续正数序列
public class _61 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>(); //存放结果
        int start = 1, end = 2;  //起始和结束值
        int curSum = 3; //当前的连续数和
        //循环，end是结束元素，start是起始元素
        while (end < sum) {
            //如果当前连续序列和大于预期，就减掉一个起始值，把起始值右移一位
            if (curSum > sum) {
                curSum -= start;
                start++;
            } else if (curSum < sum) {
                //如果当前连续序列和小于预期，就增加一个终止值，把终止值右移一位
                end++;
                curSum += end;
            } else {
                //如果当前连续序列和等于预期，就说明找到了一个连续数组，将该数组放入结果集
                ArrayList<Integer> list = new ArrayList<>();
                for (int i = start; i <= end; i++)
                    list.add(i);
                ret.add(list);
                //将数组整体右移一个数
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }
}
