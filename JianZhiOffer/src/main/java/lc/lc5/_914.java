package lc.lc5;

import java.util.ArrayList;
import java.util.List;

/*
* 卡牌分组
*
* 给定一副牌，每张牌上都写着一个整数。此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
每组都有 X 张牌。组内所有的牌上都写着相同的整数。仅当你可选的 X >= 2 时返回 true。
示例 1：
输入：deck = [1,2,3,4,4,3,2,1]
输出：true
解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
示例 2：
输入：deck = [1,1,1,2,2,2,3,3]
输出：false
解释：没有满足要求的分组。
*
* 方法一：暴力
枚举所有可行的 X，判断是否有满足条件的 X 。从 2 开始，从小到大枚举 X。由于每一组都有 X 张牌，那么 X 必须是卡牌总数 N 的约数。
其次，对于写着数字 i 的牌，如果有 count_i张，由于题目要求「组内所有的牌上都写着相同的整数」，那么 X 也必须是 count_i的约数，即：
count_i%X==0,所以对于每一个枚举到的 X，我们只要先判断 X 是否为 N 的约数，然后遍历所有牌中存在的数字 i，看它们对应牌的数量 count_i
是否满足上述要求。如果都满足等式，则 X 为符合条件的解，否则需要继续令 X 增大，枚举下一个数字。
* */
public class _914 {
    public boolean hasGroupsSizeX(int[] deck) {
        int N = deck.length;
        int[] count = new int[10000];
        for (int c : deck) {
            count[c]++;
        }

        List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                values.add(count[i]);
            }
        }

        for (int X = 2; X <= N; ++X) {
            if (N % X == 0) {
                boolean flag = true;
                for (int v : values) {
                    if (v % X != 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }
}
