package lc.lc5;

/*
* 数组的相对排序
*
* 给你两个数组，arr1 和 arr2，arr2 中元素各不相同，arr2 中每个元素都出现在 arr1 中。
对 arr1 中的元素排序，使 arr1 中项的相对顺序和 arr2 中相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 末尾。
示例 1：
输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]
示例  2:
输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
输出：[22,28,8,6,17,44]
*
* 使用一个长度为 1001（下标从 0 到 1000）的数组 frequency，记录每个元素在数组 arr1中出现次数。随后我们遍历数组 arr2，遍历到元素 x 时，
* 我们将 frequency[x] 个 x 加入答案，并将 frequency[x] 清零。遍历结束后，所有在 arr2中出现过的元素就已经有序了。
此时还剩下没有在 arr2中出现过的元素，因此还要对整个数组 frequency 进行一次遍历。当遍历到元素 x 时，如果 frequency[x] 不为 0，
* 我们将 frequency[x] 个 x 加入答案。可以对空间复杂度优化。不使用长度为 1001 数组，可以找出数组 arr1中最大值 upper，使用upper+1 数组。
* */
public class _1122 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int upper = 0;
        for (int x : arr1) {
            upper = Math.max(upper, x);
        }
        int[] frequency = new int[upper + 1];
        for (int x : arr1) {
            ++frequency[x];
        }
        int[] ans = new int[arr1.length];
        int index = 0;
        for (int x : arr2) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
            frequency[x] = 0;
        }
        for (int x = 0; x <= upper; ++x) {
            for (int i = 0; i < frequency[x]; ++i) {
                ans[index++] = x;
            }
        }
        return ans;
    }
}
