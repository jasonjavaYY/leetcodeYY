package lc.lc1;
/*
* N皇后 II
*
* n 皇后问题 研究的是如何将 n 个皇后放置在n × n的棋盘上，并且使皇后彼此之间不能相互攻击。给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
*
* 同lc的_51，返回数量即可
* */
public class _52 {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                rightmost = Math.max(rightmost, i + nums[i]);
                if (rightmost >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
