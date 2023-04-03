package lc.lc2;
/*
* 只出现一次的数字
* */
public class _136 {

    public int singleNumber(int[] nums) {
        int diff = 0; //初始diff是0
        //遍历所有数字，做异或，相同数字做异或都成了0
        for (int num : nums)
            diff ^= num;
        return diff;
    }
}
