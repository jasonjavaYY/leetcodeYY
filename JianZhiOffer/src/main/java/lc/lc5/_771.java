package lc.lc5;

import java.util.HashSet;
import java.util.Set;

/*
* 宝石与石头
*
* 给一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。 stones 中每个字符代表了一种你拥有的石头的类型，
* 你想知道你拥有的石头中有多少是宝石。
字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
示例 1：
输入：jewels = "aA", stones = "aAAbbbb"
输出：3
示例 2：
输入：jewels = "z", stones = "ZZ"
输出：0
*
* 方法二：哈希集合
使用哈希集合存储字符串 jewels 中的宝石，可以降低判断的时间复杂度。
遍历字符串 jewels，使用哈希集合存储其中的字符，然后遍历字符串 stones，对于其中的每个字符，如果其在哈希集合中，则是宝石。
* */
public class _771 {
    public int numJewelsInStones(String jewels, String stones) {
        int jewelsCount = 0;
        Set<Character> jewelsSet = new HashSet<Character>();
        int jewelsLength = jewels.length(), stonesLength = stones.length();
        for (int i = 0; i < jewelsLength; i++) {
            char jewel = jewels.charAt(i);
            jewelsSet.add(jewel);
        }
        for (int i = 0; i < stonesLength; i++) {
            char stone = stones.charAt(i);
            if (jewelsSet.contains(stone)) {
                jewelsCount++;
            }
        }
        return jewelsCount;
    }
}
