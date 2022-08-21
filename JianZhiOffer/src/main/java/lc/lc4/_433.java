package lc.lc4;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/*
* 最小基因变化
*
* 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。（变化后的基因必须位于基因库 bank 中）
给你两个基因序列 start 和 end ，以及一个基因库 bank ，请找出并返回能够使 start 变化为 end 所需的最少变化次数。
* 如果无法完成此基因变化，返回 -1 。注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
*
* 示例 1：
输入：start = "AACCGGTT", end = "AACCGGTA", bank = ["AACCGGTA"]
输出：1
示例 2：
输入：start = "AACCGGTT", end = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
输出：2
示例 3：
输入：start = "AAAAACCC", end = "AACCCCCC", bank = ["AAAACCCC","AAACCCCC","AACCCCCC"]
输出：3
*
* 方法一：广度优先搜索
题目要求将一个基因序列 A 变化至另一个基因序列 B，需要满足一下条件：序列 A 与 序列 B 之间只有一个字符不同；
变化字符只能从 ‘A’, ‘C’, ‘G’, ‘T’ 中选择；变换后的序列 B 一定要在字符串数组 bank 中。可以尝试所有合法的基因变化，并找到最小变换次数。
* 步骤如下：如果 start 与 end 相等，此时直接返回 0；如果最终的基因序列不在 bank 中，直接返回 −1；
首先将可能变换的基因 s 从队列中取出，按照上述的变换规则，尝试所有可能的变化后的基因，比如一个 AACCGGTA，我们依次尝试改变基因 s 的一个字符，
* 并尝试所有可能的基因变化序列 s0,s1,s2,⋯,si,⋯,s23，变化一次最多可能会生成 3×8=24 种不同基因序列。需要检测当前生成基因序列合法性 s_i，
* 首先利用哈希表检测, s_i是否在数组 bank 中，是则认为该基因合法，否则非法直接丢弃；其次还需要用哈希表记录已经遍历过的基因序列，
* 如果该基因序列已经遍历过，则直接跳过；如果合法且未遍历过的基因序列，则我们将其加入到队列中。如果当前变换后的基因序列与 end 相等，
* 直接返回最小变化次数；如果队列中所有元素都已经遍历完成还无法变成 end，则此时无法实现目标变化，返回 −1。
* */
public class _433 {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> cnt = new HashSet<String>();
        Set<String> visited = new HashSet<String>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String w : bank) {
            cnt.add(w);
        }
        if (start.equals(end)) {
            return 0;
        }
        if (!cnt.contains(end)) {
            return -1;
        }
        Queue<String> queue = new ArrayDeque<String>();
        queue.offer(start);
        visited.add(start);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curr = queue.poll();
                for (int j = 0; j < 8; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (keys[k] != curr.charAt(j)) {
                            StringBuffer sb = new StringBuffer(curr);
                            sb.setCharAt(j, keys[k]);
                            String next = sb.toString();
                            if (!visited.contains(next) && cnt.contains(next)) {
                                if (next.equals(end)) {
                                    return step;
                                }
                                queue.offer(next);
                                visited.add(next);
                            }
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
