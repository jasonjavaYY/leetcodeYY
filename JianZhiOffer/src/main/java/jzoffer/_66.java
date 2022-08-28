package jzoffer;

import java.util.Arrays;

/*
* 扑克牌顺子
*
* 五张牌，其中大小鬼为癞子，牌面为 0。判断这五张牌是否能组成顺子。
* */
//数组 五张牌，其中大小鬼为癞子牌为 0。判断五张牌是否能组成顺子
public class _66 {
    public boolean isContinuous(int[] nums) {
        if (nums.length < 5) return false; //如果牌数小于5，返回false
        Arrays.sort(nums); //将数组排序
        int cnt = 0;  // 统计癞子数量
        for (int num : nums)
            if (num == 0)
                cnt++;
        // 使用癞子补全不连续的顺子
        for (int i = cnt; i < nums.length - 1; i++) {
            //遍历除了癞子后面的数字，只要有重复的直接返回false
            if (nums[i + 1] == nums[i]) return false;
            //否则就用癞子填充两个数字之间的间隔
            cnt -= nums[i + 1] - nums[i] - 1;
        }
        //如果最后癞子牌替换之后还是大于等于0的，说明能成顺子
        return cnt >= 0;
    }
}
