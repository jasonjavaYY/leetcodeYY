package lc.lc4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
* 递增子序列
*
* 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中至少有两个元素 。可以按任意顺序返回答案。
数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
示例 1：
输入：nums = [4,6,7,7]
输出：[[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
示例 2：
输入：nums = [4,4,3,2,1]
输出：[[4,4]]
*
* 方法一：二进制枚举 + 哈希
我们可以采取最朴素的思路，枚举所有子序列，然后判断序列是否非严格递增。我们可以用什么办法来枚举所有的子序列呢？我们需要从原序列中选出一些数，
* 来形成新的序列，即原序列的子序列。对于原序列的每一个数来说，都有两种可能的状态，即被选中或者不被选中。如果我们用 1 代表被选中，0 代表不被选中，
* 假设一个序列长度为 3，那么选出的子序列就对应着下面的八种状态：
[0,0,0]
[0,0,1]
[0,1,0]
[0,1,1]
[1,0,0]
[1,0,1]
[1,1,0]
[1,1,1]
* 由此可见长度为 n 的序列选择子序列一共会有 2^n种情况，这 2^n中情况就是区间 [0，2^(n−1)] 的所有整数二进制表示。
* 我们可以枚举区间 [0， 2^{n - 1}]中每个数，然后对它做二进制数位拆分，我们会得到一个 0/1 序列，接着可以构造出这个 0/1 序列对应的子序列，
* 然后再检查这个序列是否是非严格递增的。当然，我们还需要解决子序列去重的问题。每次我们找到一个合法序列的时候，都去计算这个序列的哈希值，
* 用一个哈希表来记录已有的哈希值，如果该值已经出现在哈希表中，就舍弃这个序列，否则就把这个序列加入到答案中。
* */
//一个整数数组nums，找出所有该数组中不同的递增子序列，至少有两个元素，两整数相等视作递增
public class _491 {
    List<Integer> temp = new ArrayList<Integer>(); //存放一种子序列，每找下一个子序列都清空它
    List<List<Integer>> ans = new ArrayList<List<Integer>>();//用于存放结果
    Set<Integer> set = new HashSet<Integer>(); //存放所有已经出现序列的哈希值
    int n; //保存输入数组长度

    public List<List<Integer>> findSubsequences(int[] nums) {
        n = nums.length;//计算输入数组长度
        //长度n的序列选子序列一共有2^n种
        for (int i = 0; i < (1 << n); ++i) {
            findSubsequences(i, nums);//根据标记位i从nums中找子序列
            //要解决子序列去重问题。每找到一个合法序列都计算序列的哈希
            int hashValue = getHash(263, (int) 1E9 + 7);
            //如果子序列合法并且哈希值集合中不包含该子序列的哈希
            if (check() && !set.contains(hashValue)) {
                //就将子序列加入ans中，将哈希值加入set中
                ans.add(new ArrayList<Integer>(temp));
                set.add(hashValue);
            }
        }
        return ans; //最后返回ans
    }

    public void findSubsequences(int mask, int[] nums) {
        temp.clear();//清除子序列
        for (int i = 0; i < n; ++i) {//i从0到n
            if ((mask & 1) != 0) {//如果mask这位是1，就将nums[i]加入集合
                temp.add(nums[i]);
            }
            mask >>= 1;//标记位右移一位
        }
    }
    //计算哈希
    public int getHash(int base, int mod) {
        int hashValue = 0;
        for (int x : temp) {//遍历子序列每个整数，不断更新哈希值
            hashValue = hashValue * base % mod + (x + 101);
            hashValue %= mod;
        }
        return hashValue;
    }
    //检查子序列是否递增
    public boolean check() {
        for (int i = 1; i < temp.size(); ++i) {
            //从i开始遍历子序列每个数值，如果值小于前一个值返回false
            if (temp.get(i) < temp.get(i - 1)) {
                return false;
            }
        }//保证递增，最后判断子序列是否至少含2个数
        return temp.size() >= 2;
    }
}
