package lc.lc4;

import java.util.Arrays;

/*
* 分发饼干
*
* 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但每个孩子最多只能给一块饼干。
对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，
* 我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
示例 1:
输入: g = [1,2,3], s = [1,1]
输出: 1
解释:
你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
所以你应该输出1。
示例 2:
输入: g = [1,2], s = [1,2,3]
输出: 2
解释:
你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
你拥有的饼干数量和尺寸都足以让所有孩子满足。
所以你应该输出2.
*
* 方法一：排序 + 贪心
为了尽可能满足最多数量的孩子，从贪心的角度考虑，应该按照孩子胃口从小到大顺序依次满足，对于每个孩子，应该选择可以满足孩子胃口且尺寸最小的饼干。
* 首先对数组 g 和 s 排序，然后从小到大遍历 g 中每个元素，对于每个元素找到能满足该元素的 s 中的最小的元素。令 i 是 g 的下标，j 是 s 的下标，
* 初始时 i 和 j 都为 0，进行如下操作。对于每个元素 g[i]，找到未被使用的最小的 j 使得 g[i]≤s[j]，则 s[j] 可以满足 g[i]。
* 由于 g 和 s 已经排好序，因此整个过程只需要对数组 g 和 s 各遍历一次。两个数组之一遍历结束时，说明所有孩子都分配到了饼干，或所有饼干都已经被分配
* ,此时被分配到饼干的孩子数量即为可以满足的最多数量。
* */
//每个孩子最多一块饼干。孩子i有胃口值g[i]，饼干j有尺寸s[j]。如果s[j]>=g[i]孩子满足。输出能满足孩子的最大值
public class _455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);//孩子胃口升序排序
        Arrays.sort(s);//饼干大小升序排序
        int numOfChildren = g.length, numOfCookies = s.length;//计算孩子数和饼干数
        int count = 0;//保存满足的最多孩子数
        //i从0到孩子数，j从0到饼干数遍历，注意外层循环j每次不一定比上一次大1，因为内层也会j++
        //如果外层循环j跳跃了多个，说明部分小饼干没法用，不能满足胃口最小的孩子被跳过了
        for (int i = 0, j = 0; i < numOfChildren && j < numOfCookies; i++, j++) {
            //如果j小于饼干数且孩子胃口大于饼干，就j++找下一块更大饼干
            while (j < numOfCookies && g[i] > s[j]) {
                j++;
            }//如果j小于饼干数，说明找到了满足g[i]胃口最小的饼干就count++
            if (j < numOfCookies) {
                count++;
            }
        }
        return count;
    }
}
