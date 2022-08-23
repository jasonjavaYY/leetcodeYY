package jzoffer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
* 滑动窗口的最大值
*
* 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
* 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存
* 在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
* */
public class _64 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> ret = new ArrayList<>();  //存放结果数组
        //如果滑动窗口超出数组长度或者小于等于1，直接返回原数组
        if (size > num.length || size < 1)
            return ret;
        //构建一个大顶堆 o2-o1是大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //将最开始的size个数放入堆，取出堆顶元素放入结果集
        for (int i = 0; i < size; i++)
            heap.add(num[i]);
        ret.add(heap.peek());
        //以此平移窗口，每次向右移动一位，更新大顶堆元素，将堆顶元素放入结果集
        for (int i = 0, j = i + size; j < num.length; i++, j++) {
            heap.remove(num[i]); //滑动窗口，移除i元素，加入j元素，将大顶堆堆顶元素放入结果集
            heap.add(num[j]);
            ret.add(heap.peek());
        }
        return ret;
    }
}
