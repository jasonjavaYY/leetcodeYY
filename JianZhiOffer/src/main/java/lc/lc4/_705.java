package lc.lc4;

import java.util.Iterator;
import java.util.LinkedList;

/*
* 设计哈希集合
*
* 不用任何内建的哈希表库设计一个哈希集合（HashSet）。
实现 MyHashSet 类：
void add(key) 向哈希集合中插入值 key 。
bool contains(key) 返回哈希集合中是否存在这个值 key 。
void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
示例：
输入：
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
输出：
[null, null, null, true, false, null, true, null, false]
解释：
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // 返回 True
myHashSet.contains(3); // 返回 False ，（未找到）
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // 返回 True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // 返回 False ，（已移除）
*
* 方法一：链地址法
设哈希表大小为 base，可以设计一个简单的哈希函数：hash(x)=x%base。
开辟一个大小为 base 的数组，数组的每个位置是一个链表。当计算出哈希值后，就插入到对应位置的链表中。
由于我们使用整数除法作为哈希函数，为了尽可能避免冲突，应当将 base 取为质数。在这里，我们取base=769。
* */
//数学 设计HashSet。void add(key)插入key。bool contains(key)是否存在key。void remove(key)将key删除，如果集合没key不做
public class _705 {
    class MyHashSet {
        private static final int BASE = 769;//取一个较大质数作为base用于哈希
        private LinkedList[] data;
        //构造方法
        public MyHashSet() {
            data = new LinkedList[BASE];//初始化指定大小数组
            for (int i = 0; i < BASE; ++i) {
                //数组的每个节点都是个链表
                data[i] = new LinkedList<Integer>();
            }
        }
        //添加
        public void add(int key) {
            int h = hash(key);//对key进行哈希
            Iterator<Integer> iterator = data[h].iterator();//获取data指定下标处的链表
            while (iterator.hasNext()) {//遍历链表，如果已经存在key直接返回
                Integer element = iterator.next();
                if (element == key) {
                    return;
                }
            }//否则将key加入链表最后一个节点
            data[h].offerLast(key);
        }
        //删除
        public void remove(int key) {
            int h = hash(key);//计算key的哈希
            Iterator<Integer> iterator = data[h].iterator();//获取data指定下标处的链表
            while (iterator.hasNext()) {//遍历链表，如果元素值为key，就删除链表该节点
                Integer element = iterator.next();
                if (element == key) {
                    data[h].remove(element);
                    return;
                }
            }
        }
        //判断存在
        public boolean contains(int key) {
            int h = hash(key);//计算key的哈希值
            Iterator<Integer> iterator = data[h].iterator();//获取data指定下标处的链表
            while (iterator.hasNext()) {//遍历链表每个元素，如果存在值为key的元素，返回true
                Integer element = iterator.next();
                if (element == key) {
                    return true;
                }
            }
            return false;//遍历结束找不到就返回false
        }
        //hash方法，让key对BASE取余
        private int hash(int key) {
            return key % BASE;
        }
    }
}
