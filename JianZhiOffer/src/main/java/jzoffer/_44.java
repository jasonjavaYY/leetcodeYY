package jzoffer;

import java.util.PriorityQueue;

/*
* 数据流中的中位数
*
* 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
* 那么中位数就是所有数值排序之后位于中间的数值。
* 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
* */
//堆 得到数据流中的中位数
public class _44 {
    /* 大顶堆，存储左半边元素 */
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    /* 当前数据流读入的元素个数 */
    private int N = 0;

    public void Insert(Integer val) {
        /* 插入要保证两个堆存于平衡状态 */
        if (N % 2 == 0) {
            /* N为偶数插到右半边。右半边要大于左半边，但新插入元素不一定比左半边元素大
             * 因此先将元素插入左半边，然后取出堆顶元素即为最大元素，插入右半边 */
            left.add(val);
            right.add(left.poll());
        } else {
            //N为奇数把数字插入左半边，要先插入右半边然后把堆顶元素弹出移到左边，保证左边数都小于右边
            right.add(val);
            left.add(right.poll());
        }
        N++; //增大N
    }
    public Double GetMedian() {
        //如果N是偶数，就返回两个堆顶元素的平均值
        if (N % 2 == 0) return (left.peek() + right.peek()) / 2.0;
        //如果是奇数，返回右半边的堆顶元素，因为第一个数插入右半边，所以右边比左边多一个数
        else return (double) right.peek();
    }
}
