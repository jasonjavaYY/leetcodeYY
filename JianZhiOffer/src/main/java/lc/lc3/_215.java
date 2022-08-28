package lc.lc3;

/*
* 数组中的第K个最大元素
*
* 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
*
示例 1:
输入: [3,2,1,5,6,4], k = 2
输出: 5
示例 2:
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
*
* 方法二：基于堆排序的选择方法
思路和算法
使用堆排序来解决这个问题——建立一个大根堆，做 k−1 次删除操作后堆顶元素就是我们要找的答案。
* */
//给整数数组nums和整数k，返回数组中第k大的元素
public class _215 {
    public int findKthLargest(int[] nums, int k) {
        int heapSize = nums.length; //计算数组大小
        buildMaxHeap(nums, heapSize); //用数组构造大顶堆
        for (int i = nums.length - 1; i >= nums.length - k + 1; --i) {
            swap(nums, 0, i);//i从最后一个到n-k+1
            --heapSize; //堆size--
            maxHeapify(nums, 0, heapSize); //从0到堆size
        }
        return nums[0]; //返回Nums[0]
    }

    public void buildMaxHeap(int[] a, int heapSize) {
        //i从heapSize/2到0，调整堆
        for (int i = heapSize / 2; i >= 0; --i) {
            maxHeapify(a, i, heapSize);
        }
    }

    public void maxHeapify(int[] a, int i, int heapSize) {
        //l是2i+1，r是2i+2，最大下标初始化为i
        int l = i * 2 + 1, r = i * 2 + 2, largest = i;
        //如果l不越界并且a[l] > a[largest]，更新最大下标为l
        if (l < heapSize && a[l] > a[largest]) {
            largest = l;
        }//如果r不越界并且a[r] > a[largest]，更新最大下标为r
        if (r < heapSize && a[r] > a[largest]) {
            largest = r;
        }
        if (largest != i) {//如果更新过large
            swap(a, i, largest);//交换i和large位置元素
            maxHeapify(a, largest, heapSize);//继续调整large
        }
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
