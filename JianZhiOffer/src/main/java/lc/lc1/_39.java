package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 组合总和
*
* 给你一个无重复元素的整数数组 candidates 和一个目标整数 target ，找出candidates 中可以使数字和为目标数 target 的 所有不同组合 ，
* 并以列表形式返回。你可以按 任意顺序 返回这些组合。candidates 中的同一个数字可以无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
示例 1：
输入：candidates = [2,3,6,7], target = 7
输出：[[2,2,3],[7]]
解释：
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。
示例 2：
输入: candidates = [2,3,5], target = 8
输出: [[2,2,2,2],[2,3,3],[3,5]]
示例 3：
输入: candidates = [2], target = 1
输出: []
*
* 方法一：搜索回溯
对于这类寻找所有可行解的题，都可以尝试用「搜索回溯」的方法来解决。
回到本题，我们定义递归函数 dfs(target,combine,idx) 表示当前在candidates 数组的第idx 位，还剩 target 要组合，已经组合的列表为combine。
* 递归的终止条件为 target≤0 或者candidates 数组全部用完。那么在当前的函数中，每次我们可以选择跳过不用第idx个数，即执行 dfs(target,combine,idx+1)。
* 也可以选择使用第 idx 个数，即执行  dfs(target−candidates[idx],combine,idx)，注意到每个数字可以被无限制重复选取，因此搜索的下标仍为idx。
* 更形象化地说，如果我们将整个搜索过程用一个树来表达，即如下图呈现，每次的搜索都会延伸出两个分叉，直到递归的终止条件，这样就能不重复且不遗漏找到所有可行解：
* */
//回溯法 无重复元素整数数组candidates和目标target，找出数组中使数字和为target的不同组合，数字可重复。如至少一个数被选次数不同则两组合不同
public class _39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>(); //构造记录答案数组，是一个双层数组
        List<Integer> combine = new ArrayList<Integer>(); //用来表示结果数组中的每个子数组
        dfs(candidates, target, ans, combine, 0);
        return ans;
    }

    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
        if (idx == candidates.length) { //如果下标达到数组长度，返回
            return;
        }
        if (target == 0) { //如果target=0表明找到了一组，将数字组合加入ans
            ans.add(new ArrayList<Integer>(combine));
            return;
        }
        // 继续找id+1
        dfs(candidates, target, ans, combine, idx + 1);
        // 如果当前数字总和还不到target
        if (target - candidates[idx] >= 0) { //将当前数字加入combine
            combine.add(candidates[idx]); //更新target继续找
            dfs(candidates, target - candidates[idx], ans, combine, idx);
            combine.remove(combine.size() - 1); //回退时删除最后一个数字
        }
    }
}
