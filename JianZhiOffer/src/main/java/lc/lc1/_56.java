package lc.lc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
* 合并区间
*
* 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
* 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
示例 1：
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2：
输入：intervals = [[1,4],[4,5]]
输出：[[1,5]]
解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
*
* 方法一：排序
如果我们按照区间的左端点排序，那么在排完序的列表中，可以合并的区间一定是连续的。
我们用数组 merged 存储最终的答案。首先将列表中的区间按照左端点升序排序。然后将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，可以直接将这个区间加入数组 merged 的末尾；
否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。
* */
//数组 数组intervals表示区间集合，单区间intervals[i]=[starti, endi]。合并所有重叠区间返回不重叠的区间数组
public class _56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) { //如果区间列表为空，返回0到2
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            //return 参数1-参数2 就是升序  将区间列表按左端点升序
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>(); //构造一个数组列表保存返回值
        for (int i = 0; i < intervals.length; ++i) { //遍历传入数组
            //获取第i个数组的左右端
            int L = intervals[i][0], R = intervals[i][1];
            //如果合并数组为空或者merge最后一个数组的右端小于左端，代表不能合并，要新构建一个LR加入merge
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {//否则说明能合并，把merge最后一个数组右端设置为R和自身中较大的数
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]); //返回merge
    }
}
