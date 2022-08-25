package lc.lc1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 全排列 II
*
* 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
示例 1：
输入：nums = [1,1,2]
输出：
[[1,1,2],
 [1,2,1],
 [2,1,1]]
示例 2：
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
*
* 方法一：搜索回溯
我们定义递归函数 backtrack(first,output) 表示从左往右填到第 first 个位置，当前排列为 output。 那么整个递归函数分为两个情况：
如果 first=n，说明我们已经填完了 n 个位置（注意下标从 0 开始），找到了一个可行的解，我们将 output 放入答案数组中，递归结束。
如果 idx<n，我们要考虑第 idx 个位置填哪个数。肯定不能填已经填过的数，很容易想到的一个处理手段是定义一个标记数组 vis 标记已经填过的数，
* 那么在填第 idx 个数的时候我们遍历题目给定的 n 个数，如果这个数没有被标记过，我们就尝试填入并将其标记，继续尝试填下一个位置，
* 即调用函数 backtrack(idx+1,perm)。搜索回溯的时候要撤销该个位置填的数以及标记，并继续尝试其他没被标记过的数。
但题目解到这里并没有满足「全排列不重复」的要求，在上述的递归函数中会生成大量重复的排列，因为对于第 idx 的位置，如果存在重复的数字 i，
* 我们每次会将重复的数字都重新填上去并继续尝试导致最后答案的重复，因此我们需要处理这个情况。要解决重复问题，我们只要设定一个规则，
* 保证在填第idx 个数的时候重复数字只会被填入一次即可。而在本题解中，我们选择对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是
* 这个数所在重复数集合中「从左往右第一个未被填过的数字」，即如下的判断条件
if (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]) {
    continue;
}
这个判断条件保证了对于重复数的集合，一定是从左往右逐个填入的。
* */
public class _47 {
    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>(); //保存结果，是双层数组
        List<Integer> perm = new ArrayList<Integer>(); //存放一个结果
        vis = new boolean[nums.length]; //用于标记某个数字是否用过
        Arrays.sort(nums); //先排序
        backtrack(nums, ans, 0, perm); //回溯
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) { //如果idx达到了数组长度，说明找到了一个组合，加入ans返回
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; ++i) { //i从0到num的长度
            //如果i用过了或者i和i-1相等且i-1没用过，就跳过
            if (vis[i] || (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1])) {
                continue;
            }
            perm.add(nums[i]); //将元素i加入perm
            vis[i] = true; //更新i为已使用
            backtrack(nums, ans, idx + 1, perm); //继续回溯idx+1
            vis[i] = false; //回退时更新i为未使用
            perm.remove(idx); //将idx元素从perm移除
        }
    }
}
