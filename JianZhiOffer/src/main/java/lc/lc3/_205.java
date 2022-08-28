package lc.lc3;

import java.util.HashMap;
import java.util.Map;

/*
* 同构字符串
*
* 给定两个字符串 s 和 t ，判断它们是否是同构的。如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
每个字符都应当映射到另一个字符，不改变字符顺序。不同字符不能映射到同一个字符，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
示例 1:
输入：s = "egg", t = "add"
输出：true
示例 2：
输入：s = "foo", t = "bar"
输出：false
示例 3：
输入：s = "paper", t = "title"
输出：true
*
* 方法一：哈希表
需要判断 s 和 t 每个位置上的字符是否一一对应，即 s 的任意一个字符被 t 中唯一的字符对应，同时 t 的任意一个字符被 s 中唯一的字符对应。
以示例 2 为例，t 中的字符 a 和 r 虽然有唯一的映射 o，但对于 s 中的字符 o 来说其存在两个映射 {a,r}，故不满足条件。
因此维护两张哈希表，第一张哈希表 s2t 以 s 中字符为键，映射至 t 的字符为值，第二张哈希表 t2s 以 t 字符为键，映射至 s 字符为值。
* 从左至右遍历两个字符串的字符，不断更新两张哈希表，如果出现冲突（即当前下标 index 对应的字符 s[index] 已经存在映射且不为t[index]
* 或当前下标 index 对应的字符 t[index] 已经存在映射且不为 s[index]）说明两个字符串无法构成同构，返回false。
如果遍历结束没有出现冲突，则表明两个字符串是同构的，返回 true 。
* */
//两个字符串s和t，判断是否同构。如果s中字符可按某种映射替换得到t，s和t就是是同构
public class _205 {
    public boolean isIsomorphic(String s, String t) {
        //两个map分别表示s到t的字符映射和t到s的字符映射
        Map<Character, Character> s2t = new HashMap<Character, Character>();
        Map<Character, Character> t2s = new HashMap<Character, Character>();
        int len = s.length(); //计算s的长度
        for (int i = 0; i < len; ++i) {
            //遍历s和t相同位置字符x和y
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false; //如果s2t映射中有x这个k但get(x)不是y；或t2s中包含y但get(y)不是x，映射失败返回false
            }//否则将xy  yx映射分别加入s2t和t2s
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true; //遍历完毕都匹配了，返回true
    }
}
