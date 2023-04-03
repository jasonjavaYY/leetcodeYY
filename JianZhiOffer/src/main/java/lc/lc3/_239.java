package lc.lc3;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
*
* */
public class _239 {
    //同jzoffer的_64
    public int[] maxInWindows(int[] nums, int k) {
        ArrayList<Integer> ret = new ArrayList<>();  //存放结果数组
        //如果滑动窗口超出数组长度或者小于等于1，直接返回原数组
        if (k > nums.length) {
            int max = nums[0];
            for (int num : nums) {
                max = Math.max(max,num);
            }
            return new int[]{max};
        }
        //构建一个大顶堆 o2-o1是大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        //将最开始的size个数放入堆，取出堆顶元素放入结果集
        for (int i = 0; i < k; i++)
            heap.add(nums[i]);
        ret.add(heap.peek());
        //以此平移窗口，每次向右移动一位，更新大顶堆元素，将堆顶元素放入结果集
        for (int i = 0, j = i + k; j < nums.length; i++, j++) {
            heap.remove(nums[i]); //滑动窗口，移除i元素，加入j元素，将大顶堆堆顶元素放入结果集
            heap.add(nums[j]);
            ret.add(heap.peek());
        }
        int [] res = new int[ret.size()];
        int i = 0;
        for (Integer num : ret) {
            res[i++] = num;
        }
        return res;
    }
}
