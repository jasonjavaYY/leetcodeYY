package lc.lc5;

import java.util.*;

/*
* 滑动谜题
*
* 在一个 2 x 3 的板上有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 表示。一次移动定义为选择 0 与一个相邻的数字（上下左右）交换.
最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
示例 1：
输入：board = [[1,2,3],[4,0,5]]
输出：1
解释：交换 0 和 5 ，1 步完成
示例 2:
输入：board = [[1,2,3],[5,4,0]]
输出：-1
解释：没有办法完成谜板
示例 3:
输入：board = [[4,1,2],[5,0,3]]
输出：5
解释：
最少完成谜板的最少移动次数是 5 ，
一种移动路径:
尚未移动: [[4,1,2],[5,0,3]]
移动 1 次: [[4,1,2],[0,5,3]]
移动 2 次: [[0,1,2],[4,5,3]]
移动 3 次: [[1,0,2],[4,5,3]]
移动 4 次: [[1,2,0],[4,5,3]]
移动 5 次: [[1,2,3],[4,5,0]]
*
* 方法一：广度优先搜索
可以使用广度优先搜索，找出从初始状态 board 到目标状态 [[1,2,3],[4,5,0]] 的最小交换次数。
具体地，我们在一开始将 (board,0) 加入队列，使用该队列进行广度优先搜索。在搜索过程中，设当前搜索到的状态为 status，操作次数为 step，
* 我们可以枚举 status 通过一次操作得到的状态。设其中的某个状态为 next_status，如果其没有被搜索过，我们就将 (next_status,step+1) 加入队列。
* 如果搜索到了 target，我们就返回其对应的操作次数。搜索过程中需要一个哈希表存储所有搜索到的状态，避免重复搜索。
如果搜索完成，仍没有搜索到 [[1,2,3],[4,5,0]]，说明无法解开谜板，返回 −1。
* 本题中，搜索状态 status 是一个 2×3 数组，我们无法将数组直接放入哈希表，可将数组转换成语言中可以直接进行哈希的类型。
我们将 status 按行优先顺序拼成一个长度为 2×3=6 的字符串。例如目标状态 [[1,2,3],[4,5,0]] 可以表示为 123450。
在确定了解决方案后，我们还需要考虑如何有效地找出 status 通过一次操作得到的所有状态。根据题目规定，每一次操作可以将 status 中的 0 与相邻位置
* 的数字进行交换，因此我们同样可以按照行优先的顺序给 2×3 的谜板进行编号:
* 这样一来，我们可以预处理出每一个位置的所有相邻位置，即：
0 的相邻位置是 1,3；
1 的相邻位置是 0,2,4；
2 的相邻位置是 1,5；
3 的相邻位置是 0,4；
4 的相邻位置是 1,3,5；
5 的相邻位置是 2,4。
* 因此，我们在 status 中找出 0 所在的位置 x，对于每一个与 x 相邻的位置 y，我们将 status[x] 与 status[y] 进行交换，即等同于进行一次操作。
* 注意：status 是已经拼接完成的字符串。最后还需注意：如果 board 是[[1,2,3],[4,5,0]]，直接返回答案 0。
* */
public class _773 {
    int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

    public int slidingPuzzle(int[][] board) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 3; ++j) {
                sb.append(board[i][j]);
            }
        }
        String initial = sb.toString();
        if ("123450".equals(initial)) {
            return 0;
        }

        int step = 0;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(initial);
        Set<String> seen = new HashSet<String>();
        seen.add(initial);

        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                String status = queue.poll();
                for (String nextStatus : get(status)) {
                    if (!seen.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return step;
                        }
                        queue.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }

        return -1;
    }

    // 枚举 status 通过一次交换操作得到的状态
    public List<String> get(String status) {
        List<String> ret = new ArrayList<String>();
        char[] array = status.toCharArray();
        int x = status.indexOf('0');
        for (int y : neighbors[x]) {
            swap(array, x, y);
            ret.add(new String(array));
            swap(array, x, y);
        }
        return ret;
    }

    public void swap(char[] array, int x, int y) {
        char temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
