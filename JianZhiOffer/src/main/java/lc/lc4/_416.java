package lc.lc4;

/*
* 分割等和子集
*
* 给你一个 只包含正整数的非空数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
示例 1：
输入：nums = [1,5,11,5]
输出：true
解释：数组可以分割成 [1, 5, 5] 和 [11] 。
示例 2：
输入：nums = [1,2,3,5]
输出：false
解释：数组不能分割成两个元素和相等的子集。
*
* 在使用动态规划求解之前，首先需要进行以下判断。
根据数组的长度 n 判断数组是否可以被划分。如果 n<2，则不可能将数组分割成元素和相等的两个子集，因此直接返回false。计算整个数组的元素和 sum
* 以及最大元素 maxNum。如果sum 是奇数，则不可能将数组分割成元素和相等的两个子集，返回false。如果 sum 是偶数，令 target= sum/2，
* 要判断是否可以从数组中选出一些数字，这些数字和等于target。如果maxNum>target，则除 maxNum 外所有元素之和小于 target，直接返回false。
* 创建二维数组 dp，包含 n 行 target+1 列，其中 dp[i][j] 表示从数组的 [0,i] 下标范围内选取若干个正整数（可以是 0 个），是否存在一种选取
* 方案使得被选取的正整数的和等于 j。初始时，dp 中的全部元素都是 false。以下两种情况属于边界情况。
如果不选取任何正整数，则被选取的正整数等于 0。因此对于所有 0≤i<n，都有 dp[i][0]=true。
当 i==0 时，只有一个正整数 nums[0] 可以被选取，因此 dp[0][nums[0]]=true。
对于 i>0 且 j>0，如何确定 dp[i][j] 的值？需要考虑以下两种情况。
如果 j≥nums[i]，则对于当前的数字 nums[i]，可以选取也可以不选取，两种情况只要有一个为 true，就有=dp[i][j]=true。
如果不选取 nums[i]，则 dp[i][j]=dp[i−1][j]；
如果选取 nums[i]，则 dp[i][j]=dp[i−1][j−nums[i]]。
如果 j<nums[i]，则在选取的数字的和等于 j 的情况下无法选取当前的数字 nums[i]，因此有 dp[i][j]=dp[i−1][j]。
状态转移方程如下：
dp[i][j]=  { dp[i−1][j] ∣ dp[i−1][j−nums[i]],  j≥nums[i]
           dp[i−1][j],                          j<nums[i]
最终得到 dp[n−1][target] 即为答案。
* */
//一个只含正整数的非空数组nums。判断是否可将数组分成两个子集，两个子集元素和相等
public class _416 {
    public boolean canPartition(int[] nums) {
        int n = nums.length;//计算数组长度
        if (n < 2) {//如果长度小于2，返回false
            return false;
        }
        int sum = 0, maxNum = 0;//最大元素maxNum
        for (int num : nums) {//计算整个数组元素和sum，求maxNum
            sum += num;
            maxNum = Math.max(maxNum, num);
        }//如果sum是奇数，不可能分成元素和相等的两个子集，返回false
        if (sum % 2 != 0) {
            return false;
        }//如果sum是偶数，令target= sum/2
        int target = sum / 2;
        if (maxNum > target) {
            return false;//如果数组中最大数超过target，也不能分割成功
        }//dp[i][j]表示从数组[0,i]选若干数，是否有一种方案使被选数和等于j
        boolean[][] dp = new boolean[n][target + 1];
        for (int i = 0; i < n; i++) {//j为0时肯定是true，即不选任何数
            dp[i][0] = true;
        }//从num的0-0下标选数，j只能是nums[0]，因此dp[0][nums[0]]=true
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {//i从1到n
            int num = nums[i];//获取nums[i]的值
            for (int j = 1; j <= target; j++) {//j从1到target
                if (j >= num) {//j≥nums[i]，当前数nums[i]可选可不选，只要一个为true即可
                    dp[i][j] = dp[i - 1][j] | dp[i - 1][j - num];
                } else {//如果j<nums[i]，则无法选当前数nums[i]，因此dp[i][j]=dp[i−1][j]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][target];//返回dp[n - 1][target]
    }
}
