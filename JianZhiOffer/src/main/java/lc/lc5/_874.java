package lc.lc5;

import java.util.HashSet;
import java.util.Set;

/*
* 模拟行走机器人
*
* 机器人在一个无限大小的 XY 网格平面上行走，从点 (0, 0) 出发，面向北方。该机器人可以接收以下三种命令 ：
-2 ：向左转 90 度;  -1 ：向右转 90 度;  1 <= x <= 9 ：向前移动 x 个单位长度
在网格上有一些格子被视为障碍物 。第 i 个障碍物位于网格点 obstacles[i] = (xi, yi) 。
机器人无法走到障碍物上，它将会停留在障碍物的前一个网格方块上，但仍然可以继续尝试进行该路线的其余部分。
返回从原点到机器人所有经过的路径点（坐标为整数）的最大欧式距离的平方。（即，如果距离为 5 ，则返回 25 ）
注意：
北表示 +Y 方向。
东表示 +X 方向。
南表示 -Y 方向。
西表示 -X 方向。
示例 1：
输入：commands = [4,-1,3], obstacles = []
输出：25
解释：
机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 3 个单位，到达 (3, 4)
距离原点最远的是 (3, 4) ，距离为 3^2 + 4^2 = 25
示例 2：
输入：commands = [4,-1,4,-2,4], obstacles = [[2,4]]
输出：65
解释：机器人开始位于 (0, 0)：
1. 向北移动 4 个单位，到达 (0, 4)
2. 右转
3. 向东移动 1 个单位，然后被位于 (2, 4) 的障碍物阻挡，机器人停在 (1, 4)
4. 左转
5. 向北走 4 个单位，到达 (1, 8)
距离原点最远的是 (1, 8) ，距离为 1^2 + 8^2 = 65
*
* 算法
存储机器人的位置和方向。如果机器人得到转弯的指令，我们就更新方向；否则就沿给定的方向走指定的步数。
必须注意使用 集合 Set 作为对障碍物使用的数据结构，以便我们可以有效地检查下一步是否受阻。
* */
public class _874 {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2)  //left
                di = (di + 3) % 4;
            else if (cmd == -1)  //right
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }

        return ans;
    }
}
