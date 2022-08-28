package lc.lc1;

import java.util.ArrayList;
import java.util.List;

/*
* 组合
*
* 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。你可以按 任何顺序 返回答案。
示例 1：
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
示例 2：
输入：n = 1, k = 1
输出：[[1]]
*
* 标签：回溯与剪枝
n 表示范围为 1...n，balance 表示剩余空间，start 表示开始位置，list 为回溯列表
判断 balance == 0，如果为 0 则代表 list 中已经存入 k 个数，拷贝 list 存入结果 ans 中如果不为 0，从 start 位置开始递归调用，
* 现将当前位置数据加入 list 中，并进入下一层，等待返回后将本层加入的数据移除，本质就是树的构造过程,其中循环结束条件默认为最大值到 n
* */
//回溯法 两个整数n和k，返回[1, n]中所有可能的 k 个数组合
public class _77 {
    private List<List<Integer>> ans = new ArrayList<>(); //存放答案

    public List<List<Integer>> combine(int n, int k) {
        //因为是1到n，所以start从1开始
        getCombine(n, k, 1, new ArrayList<>()); //list存放一种结果
        return ans;
    }

    public void getCombine(int n, int k, int start, List<Integer> list) {
        if (k == 0) { //如果k减到0，将结果放入ans并返回，因为下面是不断k-1
            ans.add(new ArrayList<>(list));
            return;
        } //i从start开始到n-k+1，因为i后面至少要有k-1个元素，否则肯定找不到k个元素的组合
        for (int i = start; i <= n - k + 1; i++) {
            list.add(i); //将i加入list
            getCombine(n, k - 1, i + 1, list); //继续找k-1和i+1
            list.remove(list.size() - 1); //回退时移除最后一个元素
        }
    }
}
