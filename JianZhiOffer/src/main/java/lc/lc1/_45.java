package lc.lc1;

/*
* 跳跃游戏 II
*
* 给你一个非负整数数组 nums ，你最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。
你的目标是使用最少的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。
示例 1:
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
示例 2:
输入: nums = [2,3,0,1,4]
输出: 2
*
* 解题思路
这道题是典型的贪心算法，通过局部最优解得到全局最优解。方法一：反向查找出发位置
我们的目标是到达数组的最后一个位置，因此可以考虑最后一步跳跃前所在的位置，该位置通过跳跃能够到达最后一个位置。
如果有多个位置通过跳跃都能够到达最后一个位置，那么我们应该如何进行选择呢？直观上来看，我们可以「贪心」地选择距离最后一个位置最远的那个位置，
* 也就是对应下标最小的那个位置。因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
找到最后一步跳跃前所在的位置之后，我们继续贪心地寻找倒数第二步跳跃前所在的位置，以此类推，直到找到数组的开始位置。
*
* 为什么贪心算法正确，即每次选择距离最后一个位置最远的那个位置是合理的。因为从这个位置能一步跳到终点，即使选择了该位置后面的一个位置，也至少要
* 一步才能到终点，所以选择该位置后面的位置不可能比该位置更快，即该位置绝对是最快的位置（或之一），所以选择能一步到达目标位置的前面最远的位置是合理的
* */
public class _45 {
    public int jump(int[] nums) {
        //记录当前反向查找已经到达的位置
        int position = nums.length - 1;
        int steps = 0;
        while (position > 0) { //如果还没到第0个位置就循环找
            //从第0个位置开始找能到达position且离position最远的位置
            for (int i = 0; i < position; i++) {
                //如果找到了就把position = i，steps++，跳出内循环
                if (i + nums[i] >= position) {
                    position = i;
                    steps++;
                    break;
                }
            }
        }
        return steps; //最后返回step
    }
}
