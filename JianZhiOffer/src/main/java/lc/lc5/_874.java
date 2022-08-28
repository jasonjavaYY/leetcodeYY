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
//数组 人在无限大XY平面走，(0,0)出发向北。可接收三种命令：-2左转90度; -1右转90度;1<= x <= 9 ：向前移动x
//有一些障碍物obstacles[i]=(xi, yi)。会停在障碍物前一个网格。返回从原点到机器人所有经过路径点的最大距离平方
public class _874 {
    public int robotSim(int[] commands, int[][] obstacles) {
        int[] dx = new int[]{0, 1, 0, -1};//组合dx和dy代表方向
        int[] dy = new int[]{1, 0, -1, 0};
        int x = 0, y = 0, di = 0;//保存x，y，di代表同时取出dx[di]和dy[di]两个数代表的方向，分别是北、东、南、西
        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)
        //为了将障碍点xy二维数组转换成一维的编码code，放入code集合，后面容易判断
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }
        int ans = 0;//保存最大距离答案
        for (int cmd : commands) {//遍历命令
            if (cmd == -2)  //左转90度是di+3之后对4取余
                di = (di + 3) % 4;
            else if (cmd == -1)  //右转90度是di+1之后对4取余
                di = (di + 1) % 4;
            else {//否则是数字，代表要走路
                for (int k = 0; k < cmd; ++k) {//针对每个小于x的整数，都要计算其距离，因为最大距离可能在走路途中达到
                    int nx = x + dx[di];//计算走一步之后的坐标
                    int ny = y + dy[di];
                    //计算坐标的编码值
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {//判断障碍列表中如果不包含该编码
                        x = nx;//更新x和y，计算距离平方，更新最大ans
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }
        return ans;//返回ans
    }
}
