package lc.lc4;

import java.util.ArrayList;
import java.util.List;

/*
* 找到所有数组中消失的数字
*
* 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请找出所有在 [1, n] 内但没出现在 nums 中的数字，以数组形式返回。
示例 1：
输入：nums = [4,3,2,7,8,2,3,1]
输出：[5,6]
示例 2：
输入：nums = [1,1]
输出：[2]
*
* 方法一：原地修改
用一个哈希表记录数组 nums 中的数字，由于数字范围均在 [1,n] 中，记录数字后我们再利用哈希表检查 [1,n] 中的每一个数是否出现，从而找到缺失的数字。
由于数字范围均在 [1,n] 中，我们也可以用一个长度为 n 的数组来代替哈希表。这一做法的空间复杂度是 O(n) 。我们的目标是优化空间复杂度到 O(1)。
注意到 nums 长度恰好也为 n，由于 nums 的数字范围均在 [1,n] 中，我们可以利用这一范围之外的数字，来表达「是否存在」的含义。
遍历 nums，每遇到一个数 x，就让 nums[x−1] 增加 n。增加以后，这些数必然大于 n。最后我们遍历 nums，若 nums[i] 未大于 n，说明没有遇到过数i+1。
* 这样就找到了缺失的数字。注意，当我们遍历到某个位置时，其中的数可能已经被增加过，因此需要对 n 取模来还原出它本来的值。
* */
//含n个整数的数组nums，nums[i]在[1, n]内。找出所有[1, n]内但没出现在nums中的数字，数组返回
public class _448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;//计算数组长度n
        for (int num : nums) {//遍历数组每个数字
            //遇到x，就让nums[x−1]加n。增加后这些数大于n代表存在
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();//构造结果集
        for (int i = 0; i < n; i++) {//遍历nums每个位置的数
            if (nums[i] <= n) {//如果nums[i]<=n说明i+1这个数字没出现过，加入ret
                ret.add(i + 1);
            }
        }
        return ret;//返回结果集
    }
}
