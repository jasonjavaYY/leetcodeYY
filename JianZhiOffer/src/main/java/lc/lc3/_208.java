package lc.lc3;

/*
* 实现 Trie (前缀树)
*
* 前缀树是一种树形数据结构，高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
请你实现 Trie 类：
Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
示例：
输入
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
输出
[null, null, true, false, true, null, true]
解释
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // 返回 True
trie.search("app");     // 返回 False
trie.startsWith("app"); // 返回 True
trie.insert("app");
trie.search("app");     // 返回 True
*
* 方法一：字典树
Trie，又称前缀树，是一棵有根树，其每个节点包含以下字段：
指向子节点的指针数组 children。对于本题而言，数组长度为 26，即小写英文字母的数量。此时 children[0] 对应小写字母 a，children[1]
* 对应小写字母 b，…，children[25] 对应小写字母 z。布尔字段 isEnd，表示该节点是否为字符串的结尾。
插入字符串
我们从字典树的根开始，插入字符串。对于当前字符对应的子节点，有两种情况：
子节点存在。沿着指针移动到子节点，继续处理下一个字符。
子节点不存在。创建一个新的子节点，记录在 children 数组的对应位置上，然后沿着指针移动到子节点，继续搜索下一个字符。
重复以上步骤，直到处理字符串的最后一个字符，然后将当前节点标记为字符串的结尾。
查找前缀
我们从字典树的根开始，查找前缀。对于当前字符对应的子节点，有两种情况：
子节点存在。沿着指针移动到子节点，继续搜索下一个字符。
子节点不存在。说明字典树中不包含该前缀，返回空指针。
重复以上步骤，直到返回空指针或搜索完前缀的最后一个字符。
若搜索到了前缀的末尾，就说明字典树中存在该前缀。此外，若前缀末尾对应节点的 isEnd 为真，则说明字典树中存在该字符串。
* */
//实现Trie类：Trie()初始化前缀树。void insert(String word)向树插入word。boolean search(String word)如果word在树中返回true
//boolean startsWith(String prefix) 如果已经插入的word前缀之一为prefix返回true
public class _208 {
    //Trie节点包含：指向子节点指针数组children。本题数组长26即英文字母数。
    // children[0]对应a，children[25]对应z。
    // 布尔字段 isEnd，表示该节点是否为字符串结尾
    class Trie {
        private Trie[] children;
        private boolean isEnd;

        public Trie() { //构造方法初始化children和isEnd
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this; //构造节点
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i); //遍历单词每个字符
                int index = ch - 'a'; //计算该字符的index
                //如果node的children在index处没节点，就构造一个
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }//否则将node指向children在index节点，继续向下找
                node = node.children[index];
            }
            node.isEnd = true; //插入完毕将最后一个节点标记为end
        }
        //搜索前缀之后判断当前node是否不为空且是end
        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        //搜索前缀，判断是否能搜到最后一个节点
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        //根据前缀搜索节点
        private Trie searchPrefix(String prefix) {
            Trie node = this; //构造头节点
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i); //遍历前缀每个字符
                int index = ch - 'a'; //计算该字符的index
                //如果node的children在index处没节点，返回空
                if (node.children[index] == null) {
                    return null;
                }//否则继续找下一个节点
                node = node.children[index];
            }
            return node;//查找完毕返回前缀最后一个字符对应节点
        }
    }

}
