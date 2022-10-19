package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _5FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>(); //存放结果数组
        if (nums == null || nums.length < 4) { //如果数组长度小于4，返回空数组
            return quadruplets;
        }
        Arrays.sort(nums); //传入数组排序
        int length = nums.length; //获取传入数组长度
        for (int i = 0; i < length - 3; i++) { //i从0到length-3防止越界，因为i后面还要有3个数
            if (i > 0 && nums[i] == nums[i - 1]) continue; //去重
            //如果从i开始四个最小数和大于target，直接退出循环
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) break;
            //如果从i加上其余最大3个数和小于target，说明当前i值太小，进行下一轮循环
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) continue;
            for (int j = i + 1; j < length - 2; j++) { //j从i+1到length-2
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; //去重
                //如果i+从j开始3个最小数和大于target，直接退出循环
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) break;
                //如果i+j再+最大2数和小于target，说明当前j值太小，进入下一轮循环
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) continue;
                int left = j + 1, right = length - 1; //初始化左右下标
                while (left < right) { //循环判断
                    int sum = nums[i] + nums[j] + nums[left] + nums[right]; //求四个数和
                    if (sum == target) { //如果等于target，将四个数放入结果集
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++; //去重left
                        left++; //更新left
                        while (left < right && nums[right] == nums[right - 1]) right--; //去重right
                        right--; //更新right
                    } else if (sum < target) left++;  //如果和太小，增大left
                    else right--; //如果和太大，减小right
                }
            }
        }
        return quadruplets; //返回结果数组
    }
}
