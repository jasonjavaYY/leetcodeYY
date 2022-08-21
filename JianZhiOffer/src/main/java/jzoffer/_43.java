package jzoffer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
* 最小的 K 个数
*
* 快速选择，复杂度：O(N) + O(1)，只有当允许修改数组元素时才可以使用
* 快速排序的 partition() 方法，会返回一个整数 j 使得 a[l..j-1] 小于等于 a[j]，
* 且 a[j+1..h] 大于等于 a[j]，此时 a[j] 就是数组的第 j 大元素。
* 可以利用这个特性找出数组的第 K 个元素，这种找第 K 个元素的算法称为快速选择算法。
* */
public class _43 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] nums, int k) {
        if (k > nums.length || k <= 0)
            return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : nums) {
            maxHeap.add(num);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        return new ArrayList<>(maxHeap);
    }
}
