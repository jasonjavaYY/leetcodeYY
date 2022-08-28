package lc.lc2;

import java.util.*;

/*
* 单词接龙
*
* 字典 wordList 中从单词 beginWord 和 endWord 的转换序列是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
每一对相邻的单词只差一个字母。对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列中的单词数目 。
如果不存在这样的转换序列，返回 0 。
示例 1：
输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
示例 2：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。
*
* 方法一：广度优先搜索 + 优化建图
本题要求的是最短转换序列的长度，看到最短首先想到的就是广度优先搜索。想到广度优先搜索自然而然的就能想到图，
但是本题并没有直截了当的给出图的模型，因此我们需要把它抽象成图的模型。我们可以把每个单词都抽象为一个点，
如果两个单词可以只改变一个字母进行转换，那么说明他们之间有一条双向边。因此我们只需要把满足转换条件的点相连，就形成了一张图。
基于该图，我们以 beginWord 为图的起点，以 endWord 为终点进行广度优先搜索，寻找 beginWord 到 endWord 的最短路径。
首先为了方便表示，我们先给每一个单词标号，即给每个单词分配一个 id。创建一个由单词 word 到 id 对应的映射 wordId，
并将 beginWord 与 wordList 中所有的单词都加入这个映射中。之后我们检查 endWord 是否在该映射内，若不存在，则输入无解。
我们可以使用哈希表实现上面的映射关系。然后我们需要建图，依据朴素的思路，我们可以枚举每一对单词的组合，判断它们是否恰好相差一个字符，
以判断这两个单词对应的节点是否能够相连。但是这样效率太低，我们可以优化建图。具体地，我们可以创建虚拟节点。对于单词 hit，
我们创建三个虚拟节点 *it、h*t、hi*，并让 hit 向这三个虚拟节点分别连一条边即可。如果一个单词能够转化为 hit，那么该单词必然会连接到
这三个虚拟节点之一。对于每一个单词，我们枚举它连接到的虚拟节点，把该单词对应的 id 与这些虚拟节点对应的 id 相连即可。
最后我们将起点加入队列开始广度优先搜索，当搜索到终点时，我们就找到了最短路径的长度。注意因为添加了虚拟节点，
所以我们得到的距离为实际最短路径长度的两倍。同时我们并未计算起点对答案的贡献，所以我们应当返回距离的一半再加一的结果。
* */
//单词begin和end转换序列是begin -> s1 -> s2.. -> sk：相邻单词只差一个字母。si在wordList中。begin不需要在wordList中。
//给两个单词begin和 end和字典wordList，返回从begin到end的最短转换序列单词数。如不存在转换序列返回0
public class _127 {
    //给每个单词标号id。创建由 word 到 id 的映射 wordId
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    //保存边，edge[idx]是个数组，代表从idx可以一步到达哪些节点的id数组
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;  //记录节点数

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) { //遍历单词列表，为每个单词构造虚拟节点添加边
            addEdge(word);
        }
        addEdge(beginWord); //给开始单词添加边
        //如果添加完所有节点和边，单词映射表不包含结束单词，肯定没法转换，返回0
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];  //存放到指定节点的最短距离
        Arrays.fill(dis, Integer.MAX_VALUE); //最开始设置为最大值
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord); //获取开始id和结束id
        dis[beginId] = 0; //到达初始节点距离是0，因为不需要变换

        Queue<Integer> que = new LinkedList<Integer>(); //构造队列
        que.offer(beginId); //起点加入队列
        while (!que.isEmpty()) { //广度搜索
            int x = que.poll(); //弹出队首id
            if (x == endId) { //如果队首id是结束id表明找到了路径，
                return dis[endId] / 2 + 1; //返回到endId的距离/2+1
            }
            for (int it : edge.get(x)) { //否则遍历包含x的所有边的节点id
                if (dis[it] == Integer.MAX_VALUE) { //如果还没遇到过
                    dis[it] = dis[x] + 1; //就更新dis[it]=dis[x]+1，因为从x需要再更新一次
                    que.offer(it); //将it节点加入队列
                }
            }
        }
        return 0; //遍历都没找到，就返回0
    }

    public void addEdge(String word) {
        addWord(word); //先把单词加入表中，增加一条空边
        int id1 = wordId.get(word); //获取单词对应的节点值id1
        char[] array = word.toCharArray(); //单词转换成字符数组
        int length = array.length;
        for (int i = 0; i < length; ++i) { //遍历字符数组
            char tmp = array[i]; //更新第i位为*
            array[i] = '*';
            //构造虚拟的新单词
            String newWord = new String(array);
            addWord(newWord); //添加新单词
            int id2 = wordId.get(newWord); //从映射表中获取新单词的id2
            //id1和id2能互相转换，增加两条边
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp; //恢复原单词
        }
    }

    public void addWord(String word) {
        //如果映射表不包含该单词，将其加入表中，新增一条空边
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
}
