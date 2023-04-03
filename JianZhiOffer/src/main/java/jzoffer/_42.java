package jzoffer;
/*
* 数组中出现次数超过一半的数字
*
* 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
* 超过数组长度的一半，因此输出2。如果不存在则输出0。
*
* 多数投票问题，可以利用Boyer-Moore Majority Vote Algorithm解决这，时间复杂度为O(N)。
*
使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，令 cnt++，否则令 cnt--。
* 如果前面查找了 i 个元素，且 cnt == 0，说明前 i 个元素没有 majority，
* 或者有 majority，但是出现的次数少于 i / 2 ，因为如果多于 i / 2 的话cnt就一定不会为 0 。
* 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，因此继续查找就能找出 majority。
* */
//数组 数组中出现次数超一半的数字，如{1,2,3,2,2,2,5,4,2}。2出现5次超过一半输出2。如不存在输出0
public class _42 {
    public int majorityElement(int[] nums) {
        //majority代表数组中出现次数最多的元素值，首先设置第0个元素为majority
        int majority = nums[0];
        for (int i = 1, cnt = 1; i < nums.length; i++) {
            //遍历数组，如果某个元素等于majority，就cnt++，否则cnt--
            cnt = nums[i] == majority ? cnt + 1 : cnt - 1;
            //如果cnt减到0，就更新majority为当前值，记录出现次数为1
            if (cnt == 0) {
                majority = nums[i];
                cnt = 1;
            }
        }
        //遍历数组，找到majority出现的次数，如果超过一半就返回majority否则返回0
        int cnt = 0;
        for (int val : nums)
            if (val == majority)
                cnt++;
        return cnt > nums.length / 2 ? majority : 0;
    }
}
