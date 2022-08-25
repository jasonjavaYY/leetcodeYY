package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 子集
*
* 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。你可以按任意顺序返回解集。
示例 1：
输入：nums = [1,2,3]
输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
示例 2：
输入：nums = [0]
输出：[[],[0]]
*
* 方法一：迭代法实现子集枚举
记原序列中元素的总数为 n。原序列中的每个数字 a_i的状态可能有两种，即「在子集中」和「不在子集中」。我们用 1 表示「在子集中」，0 表示不在子集中，
* 那么每一个子集可以对应一个长度为 n 的 0/1 序列，第 i 位表示 a_i是否在子集中。例如n=3 ，a = a={5,2,9} 时
0/1序列	    子集	          0/1 序列对应的二进制数
000000	    {}	               0
001001	    {9}	               1
010010	    {2}	               2
011011	    {2,9}	           3
100100	    {5}	               4
101101	    {5,9}	           5
110110	    {5,2}	           6
111111	    {5,2,9}	           7
* 可以发现 0/1 序列对应的二进制数正好从 0 到 2^n - 1。我们可以枚举mask∈[0,2^n−1]，mask 的二进制表示是一个 0/1 序列，
* 我们可以按照这个 0/1 序列在原集合当中取数。当我们枚举完所有 2^n个 mask，我们也就能构造出所有的子集。
* */
public class _78 {
    List<Integer> t = new ArrayList<Integer>(); //t存放每种可能的结果
    List<List<Integer>> ans = new ArrayList<List<Integer>>(); //存最终结果集

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length; //计算输入数组长度n
        //mask从0到2^n
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear(); //先清空
            for (int i = 0; i < n; ++i) { //i从0到n
                //mask和2^i与不为0，就把nums[i]加入t
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            } //内层循环每次找一个t，将t放入ans中
            ans.add(new ArrayList<Integer>(t));
        }
        return ans; //返回ans
    }
}
