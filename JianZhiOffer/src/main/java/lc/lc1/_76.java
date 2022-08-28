package lc.lc1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
* 最小覆盖子串
*
* 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
*
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：
输入：s = "a", t = "a"
输出："a"
示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
*
* 方法一：滑动窗口
本问题要求我们返回字符串 s 中包含字符串 t 的全部字符的最小窗口。我们称包含 t 的全部字母的窗口为「可行」窗口。
我们可以用滑动窗口的思想解决这个问题。在滑动窗口类型的问题中都会有两个指针，一个用于「延伸」现有窗口的 r 指针，和一个用于「收缩」窗口的 l指针。
* 在任意时刻，只有一个指针运动，另一个保持静止。我们在 s 上滑动窗口，通过移动 r 指针不断扩张窗口。当窗口包含 t 全部所需的字符后，如果能收缩，
* 我们就收缩窗口直到得到最小窗口。如何判断当前的窗口包含所有 t 所需的字符呢？我们可以用一个哈希表表示 t 中所有的字符以及它们的个数，
* 用一个哈希表动态维护窗口中所有的字符以及它们的个数，如果这个动态表中包含 t 的哈希表中的所有字符，
* 并且对应的个数都不小于 t 的哈希表中各个字符的个数，那么当前的窗口是「可行」的。
* */
//滑动变长窗口 字符串s和t。返回s中涵盖t所有字符的最小子串。如果s中不存在则返回空字符串
public class _76 {
    Map<Character, Integer> ori = new HashMap<Character, Integer>();//保存t各字符出现次数
    Map<Character, Integer> cnt = new HashMap<Character, Integer>(); //保存当前窗口各字符出现次数

    public String minWindow(String s, String t) {
        int tLen = t.length(); //得到字符串t长度
        for (int i = 0; i < tLen; i++) {  //获取t各字符次数
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1; //用于延伸现有窗口的r指针，和用于收缩窗口的l指针
        //len保存当前满足条件结果子串最短长度，ansL为最终子串左下标，ansR为最终子串右下标
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();  //计算s字符串长度
        while (r < sLen) {  //循环，判断右指针r不越界
            ++r; //右指针++，让r从0开始找字符直到r位置
            if (r < sLen && ori.containsKey(s.charAt(r))) { //如果在t中有s[r]字符
                //就把cnt中s[r]字符出现的次数++
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            //r滑动一位，就判断当前子串是否包含t的全部字符，不包含就继续++r更新cnt
            while (check() && l <= r) { //循环判断当前窗口是否包含t全部字符且l<=r
                //如果当前左右指针包含的字符串长度小于len，更新len、ansL、ansR，先更新结果
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                //如果t包含s[l]字符，就将窗口中该字符次数-1，因为接下来要l++把这个字符踢出窗口了
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;  //踢出l字符继续判断窗口是否满足条件
            }
        } //当右指针遍历完的时候，len就达到了最短，返回s.substring(ansL, ansR)
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator();
        while (iter.hasNext()) {  //遍历ori每个元素
            //获取字符串t每个字符及出现次数
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            //如果窗口内字符串的字符出现次数小于t的字符次数，返回false
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true; //返回true代表当前窗口字符串能找到t的全部字符甚至多出部分字符
    }
}
