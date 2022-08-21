package lc.lc3;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
* 前 K 个高频元素
*
* 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按任意顺序返回答案。
示例 1:
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]
示例 2:
输入: nums = [1], k = 1
输出: [1]
*
* 思路与算法
首先遍历整个数组，并使用哈希表记录每个数字出现的次数，并形成一个「出现次数数组」。找出原数组前 k 高频元素，就相当于找出「出现次数数组」的前 k 大的值。
最简单的做法是给「出现次数数组」排序。但由于可能有 O(N) 个不同的出现次数（其中 N 为原数组长度），故算法复杂度达到 O(NlogN)，不满足题目的要求。
可以建立一个小顶堆，然后遍历「出现次数数组」：如果堆元素个数小于 k，直接插入堆。如果堆元素个数等于 k，则检查堆顶与当前出现次数的大小。如果堆顶更大，
* 舍弃当前值；否则，就弹出堆顶，将当前值插入堆。遍历完成后，堆中的元素就代表了「出现次数数组」中前 kk 大的值。
* */
public class _347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) {
                return m[1] - n[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
    }
}
