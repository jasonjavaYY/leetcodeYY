package lc.lc1;

import java.util.HashMap;
import java.util.Map;

/*
* 两数之和
*
* 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
* 和为目标值 target的那 两个整数，并返回它们的数组下标。
* 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
* 你可以按任意顺序返回答案。
*
* 能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
* 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N)O(N) 降低到 O(1)O(1)。
* 创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，然后将 x 插入到哈希表中，
* 即可保证不会让 x 和自己匹配。
* */
public class _1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            //key+value=target，找到了key，说明找到了一对数字和为target，返回两个数的下标
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            //没找到就把数字和下标放入map
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
