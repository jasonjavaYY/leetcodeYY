package lc.lc1;

/*
* 寻找两个正序数组的中位数
*
* 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
* 请你找出并返回这两个正序数组的 中位数 。
* 算法的时间复杂度应该为 O(log (m+n)) 。
*
输入：nums1 = [1,3], nums2 = [2]
输出：2.00000
解释：合并数组 = [1,2,3] ，中位数 2
*
输入：nums1 = [1,2], nums2 = [3,4]
输出：2.50000
解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
*
* 根据中位数的定义，当 m+nm+n 是奇数时，中位数是两个有序数组中的第 (m+n)/2个元素，
* 当 m+nm+n 是偶数时，中位数是两个有序数组中的第 (m+n)/2个元素和第 (m+n)/2+1个元素的平均值。
* 因此，这道题可以转化成寻找两个有序数组中的第 k 小的数，其中 k 为 (m+n)/2 或 (m+n)/2+1。
* 假设两个有序数组分别是A 和B。要找到第 k 个元素，我们可以比较A[k/2−1] 和B[k/2−1]，其中/ 表示整数除法。
* 由于A[k/2−1] 和B[k/2−1] 的前面分别有 A[0..k/2−2] 和B[0..k/2−2]，即 k/2-1个元素，
* 对于A[k/2−1] 和B[k/2−1] 中的较小值，最多只会有 (k/2-1)+(k/2-1)≤k−2 个元素比它小，那么它就不能是第 k小的数了。
* 因此我们可以归纳出三种情况：
* 如果 A[k/2−1]<B[k/2−1]，则比A[k/2−1] 小的数最多只有 A 的前k/2−1 个数和B 的前k/2−1个数，即比A[k/2−1]小的数最多只有k−2 个，
* 因此A[k/2−1] 不可能是第 k个数，A[0] 到 A[k/2−1] 也都不可能是第 k个数，可以全部排除。
* 如果 A[k/2−1]>B[k/2−1]，则可以排除 B[0] 到 B[k/2−1]。
* 如果 A[k/2−1]=B[k/2−1]，则可以归入第一种情况处理。
*
* 有以下三种情况需要特殊处理：
* 如果 A[k/2−1] 或者 B[k/2−1] 越界，可以选取对应数组中的最后一个元素。在这种情况下，必须根据排除数的个数减少k 的值，不能直接将 k 减去k/2。
* 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素。
* 如果 k=1，只要返回两个数组首元素的最小值即可。
* */
//两个m 和 n的正序数组nums1 和 nums2。返回这两个正序数组的中位数
public class _4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length; //计算两个数组的长度之和
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) { //如果总长度为奇数，则k=midIndex + 1
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {  //如果为偶数，就是midIndex1 + 1和midIndex2 + 1的平均数
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */
        int length1 = nums1.length, length2 = nums2.length; //获取两个数组各自长度
        int index1 = 0, index2 = 0;
        int kthElement = 0;
        while (true) {
            // 边界情况 如果index1越界了，就返回num2的第k-1个数
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            } //如果index2越界了，就返回num1的第k-1个数
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }  //如果k=1，就返回两个数组队首元素的较小值
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            // 正常情况  更新index1和index2，获取两个数组对应位置的值
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            //如果num1≤num2，更新k和index1的值
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                //否则更新k和index2的值
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
