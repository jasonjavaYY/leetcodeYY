package lc.lc4;

/*
* 有效的完全平方数
*
* 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
进阶：不要 使用任何内置的库函数，如  sqrt 。
示例 1：
输入：num = 16
输出：true
示例 2：
输入：num = 14
输出：false
*
* 方法三：二分查找
因为 num 是正整数，所以若正整数 x 满足 x×x=num，则 x 一定满足 1≤x≤num。于是我们可以将 1 和 num 作为二分查找搜索区间的初始边界。
因为我们在移动左侧边界 left 和右侧边界 right 时，新的搜索区间都不会包含被检查的下标 mid，所以搜索区间的边界始终是我们没有检查过的。
* 因此，当left=right 时，我们仍需要检查 mid=(left+right)/2。
* */
public class _367 {
    //同lc的_69
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long) mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
