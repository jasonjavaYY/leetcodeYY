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
//一副牌每张牌一个整数。选数字X，将牌分1或多组：每组有X张相同数字牌。当可选X >= 2时返回true
public class _914 {
    public boolean hasGroupsSizeX(int[] deck) {
        int N = deck.length;//计算牌的总数N
        int[] count = new int[10000];//遍历每张牌，记录数字出现次数
        for (int c : deck) {
            count[c]++;
        }
        List<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 10000; ++i) {
            if (count[i] > 0) {
                values.add(count[i]);
            }
        }//将牌出现的次数加入values数组
        for (int X = 2; X <= N; ++X) {//X从2到N
            if (N % X == 0) {//如果X能被N整除
                boolean flag = true;//默认能按X分组
                //遍历每张牌出现次数
                for (int v : values) {
                    //如果出现次数不能整除X，标记为false，判断下一个X
                    if (v % X != 0) {
                        flag = false;
                        break;
                    }
                }//如果遍历完所有数字的次数都能整除X，返回true
                if (flag) {
                    return true;
                }
            }
        }
        return false;//遍历完所有X没找到合理分组返回false
    }
}
