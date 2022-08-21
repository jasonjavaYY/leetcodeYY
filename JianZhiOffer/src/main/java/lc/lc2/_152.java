package lc.lc2;

/*
* 乘积最大子数组
*
* 一个整数数组 nums ，找出数组中乘积最大的非空连续子数组（该子数组至少包含一个数字），返回所对应的乘积。
测试用例的答案是一个 32位 整数。子数组 是数组的连续子序列。
示例 1:
输入: nums = [2,3,-2,4]
输出: 6
解释: 子数组 [2,3] 有最大乘积 6。
示例 2:
输入: nums = [-2,0,-1]
输出: 0
解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
*
* 方法一：动态规划
如果我们用 fmax(i) 来表示以第 i 个元素结尾的乘积最大子数组的乘积，a 表示输入参数 nums，我们很容易推导出这样的状态转移方程：
fmax(i)= i=1 max n {f(i−1)×ai,ai}
它表示以第 i 个元素结尾的乘积最大子数组的乘积可以考虑 ai加入前面的 fmax(i−1) 对应的一段，或者单独成为一段，这里两种情况下取最大值。
* 求出所有的 fmax(i) 之后选取最大的一个作为答案。
可是在这里，这样做是错误的。为什么呢？
具体地讲，如果 a = a={5,6,−3,4,−3}，那么此时 fmax对应的序列是 {5,30,−3,4,−3}，按照前面的算法我们可以得到答案为 30，即前两个数的乘积，
* 而实际上答案应该是全体数字的乘积。我们来想一想问题出在哪里呢？问题出在最后一个 −3 所对应的 fmax的值既不是 −3，也不是 4×(−3)，
* 而是 5×6×(−3)×4×(−3)。所以我们得到了一个结论：当前位置的最优解未必是由前一个位置的最优解转移得到的。
我们可以根据正负性进行分类讨论。
考虑当前位置如果是一个负数，我们希望以它前一个位置结尾的某个段的积也是负数，并且我们希望这个积尽可能「负得更多」，即尽可能小。
* 如果当前位置是一个正数，我们更希望以它前一个位置结尾的某个段的积也是个正数，并且希望它尽可能地大。于是这里我们可以再维护一个 fmin(i)，
* 它表示以第 i 个元素结尾的乘积最小子数组的乘积，那么我们可以得到这样的动态规划转移方程：
fmax(i) = i=1 max n {fmax(i−1)×ai,fmin(i−1)×ai,ai}
fmin(i) = i=1 min n{fmax(i−1)×ai,fmin(i−1)×ai,ai}
它代表第 i 个元素结尾的乘积最大子数组的乘积 fmax(i)，可以考虑把ai加入第 i−1 个元素结尾的乘积最大或最小的子数组的乘积中，二者加上 ai，
* 三者取大，就是第 i 个元素结尾的乘积最大子数组的乘积。第 i 个元素结尾的乘积最小子数组的乘积 fmin(i) 同理。
* */
public class _152 {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        int[] maxF = new int[length];
        int[] minF = new int[length];
        System.arraycopy(nums, 0, maxF, 0, length);
        System.arraycopy(nums, 0, minF, 0, length);
        for (int i = 1; i < length; ++i) {
            maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(nums[i], minF[i - 1] * nums[i]));
            minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(nums[i], maxF[i - 1] * nums[i]));
        }
        int ans = maxF[0];
        for (int i = 1; i < length; ++i) {
            ans = Math.max(ans, maxF[i]);
        }
        return ans;
    }
}
