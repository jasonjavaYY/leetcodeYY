package lc.lc2;

import java.util.HashSet;
import java.util.Set;

/*
* 最长连续序列
*
* 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
示例 1：
输入：nums = [100,4,200,1,3,2]
输出：4
解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
示例 2：
输入：nums = [0,3,7,2,5,8,4,6,0,1]
输出：9
*
* 如果已知有一个 x,x+1,x+2,⋯,x+y 的连续序列，而我们却重新从 x+1，x+2 或者是x+y 处开始尝试匹配，那么得到结果肯定不会优于枚举 x为起点的答案，
* 因此我们在外层循环的时候碰到这种情况跳过即可。怎么判断是否跳过呢？由于我们要枚举的数 x 一定是在数组中不存在前驱数 x−1 的，
* 不然按照上面的分析我们会从 x−1 开始尝试匹配，因此我们每次在哈希表中检查是否存在 x−1 即能判断是否需要跳过了。
* */
//一个未排序整数数组nums，找出连续最长序列（不要求序列元素在原数组中连续）长度
public class _128 {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) { //先去重
            num_set.add(num);
        }
        int longestStreak = 0; //保存最长连续序列
        for (int num : num_set) { //遍历数组
            if (!num_set.contains(num - 1)) { //如果数组不包含当前数值-1
                int currentNum = num; //就将num设置为当前数
                int currentStreak = 1; //找到一个最小的数，当前长度为1
                //不停判断数组是否包含当前数的下一个数
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1; //如果包含，就当前数+1，当前长度+1
                    currentStreak += 1;
                }//退出这个循环说明找到了一条最长连续数组，更新全局最大值
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak; //返回全局最大值
    }
}
