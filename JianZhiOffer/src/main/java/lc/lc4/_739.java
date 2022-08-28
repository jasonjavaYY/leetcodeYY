package lc.lc4;

import java.util.Deque;
import java.util.LinkedList;

/*
* 每日温度
*
* 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
* 如果气温在这之后都不会升高，请在该位置用 0 来代替。
示例 1:
输入: temperatures = [73,74,75,71,69,72,76,73]
输出: [1,1,4,2,1,1,0,0]
示例 2:
输入: temperatures = [30,40,50,60]
输出: [1,1,1,0]
示例 3:
输入: temperatures = [30,60,90]
输出: [1,1,0]
*
* 方法二：单调栈
维护一个存储下标的单调栈，从栈底到栈顶下标对应温度列表中温度依次递减。如果一个下标在单调栈里，则表示尚未找到下一次温度更高的下标。
正向遍历温度列表。对于列表中每个元素 temperatures[i]，如果栈为空，则直接将 i 进栈，如果栈不为空，则比较栈顶元素 prevIndex 对应的温度
*  temperatures[prevIndex] 和当前温度 temperatures[i]，如果 temperatures[i] > temperatures[prevIndex]，则将 prevIndex 移除，
* 并将 prevIndex 对应的等待天数赋为 i - prevIndex，重复上述操作直到栈为空或者栈顶元素温度小于等于当前温度，然后将 i 进栈。
为什么可以在弹栈时更新 ans[prevIndex] 呢？因为这种情况下，即将进栈的 i 对应的 temperatures[i] 一定是 temperatures[prevIndex] 右边
* 第一个比它大的元素，试想如果 prevIndex 和 i 有比它大的元素，假设下标为 j，那么 prevIndex 一定会在下标 j 的那一轮被弹掉。
由于单调栈满足从栈底到栈顶元素对应的温度递减，因此每次元素进栈，会将温度更低的元素全移除，并更新出栈元素的等待天数，确保等待天数一定是最小的。
* 对于温度列表 [73,74,75,71,69,72,76,73]，单调栈 stack 初始状态为空，答案 ans 初始状态是 [0,0,0,0,0,0,0,0]，
* 按照以下步骤更新单调栈和答案，其中单调栈内的元素都是下标，括号内的数字表示下标在温度列表中对应的温度。
当 i=0 时，单调栈为空，因此将 0 进栈。
stack=[0(73)]               ans=[0,0,0,0,0,0,0,0]
当 i=1 时，由于 74 大于 73，因此移除栈顶元素 0，赋值 ans[0]=1−0，将 1 进栈。
stack=[1(74)]               ans=[1,0,0,0,0,0,0,0]
当 i=2 时，由于 75 大于 74，因此移除栈顶元素 1，赋值 ans[1]=2−1，将 2 进栈。
stack=[2(75)]               ans=[1,1,0,0,0,0,0,0]
当 i=3 时，由于 71 小于 75，因此将 3 进栈。
stack=[2(75),3(71)]         ans=[1,1,0,0,0,0,0,0]
当 i=4 时，由于 69 小于 71，因此将 4 进栈。
stack=[2(75),3(71),4(69)]   ans=[1,1,0,0,0,0,0,0]
当 i=5 时，由于 72 大于 69 和 71，因此依次移除栈顶元素 4 和 3，赋值 ans[4]=5−4=1 和 ans[3]=5−3=2，将 5 进栈。
stack=[2(75),5(72)]         ans=[1,1,0,2,1,0,0,0]
当 i=6 时，由于 76 大于 72 和 75，因此依次移除栈顶元素 5 和 2，赋值 ans[5]=6−5=1 和 ans[2]=6−2=4，将 6 进栈。
stack=[6(76)]               ans=[1,1,4,2,1,1,0,0]
当 i=7 时，由于 73 小于 76，因此将 7 进栈。
stack=[6(76),7(73)]         ans=[1,1,4,2,1,1,0,0]
* */
//数组 整数数组temperatures表示温度，返回数组answer，answer[i]指对第i天，下个更高温在几天后。如果在这之后都不升温则为0
public class _739 {
    public int[] dailyTemperatures(int[] temperatures) {
        int length = temperatures.length;//计算温度列表天数
        int[] ans = new int[length];//构造结果数组
        //存储下标的单调栈，栈底到栈顶温度递减。如果一下标在栈里表示尚未找到下一温度更高下标
        Deque<Integer> stack = new LinkedList<Integer>();//构造栈
        for (int i = 0; i < length; i++) {
            //遍历每天的温度
            int temperature = temperatures[i];

            //如果栈不为空，比较栈顶下标的温度和当前温度，如果当前温度高于栈顶下标温度，将栈顶元素移除
            //ans[prevIndex] = i - prevIndex，直到栈空或栈顶元素温度大于等于当前温度，将i进栈
            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);//如果栈为空，则直接将 i 进栈
        }
        return ans;//返回ans
    }
}
