package lc.lc2;

import java.util.HashMap;
import java.util.Map;

/*
* LRU 缓存
*
* 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；不存在则向缓存中插入 key-value 。
* 如果插入导致关键字超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
*
* 输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]
解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
*
* 方法一：哈希表 + 双向链表
LRU 缓存机制可以通过哈希表辅以双向链表实现，我们用一个哈希表和一个双向链表维护所有在缓存中的键值对。
双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。
哈希表即为普通的哈希映射（HashMap），通过缓存数据的键映射到其在双向链表中的位置。
我们首先使用哈希表进行定位，找出缓存项在双向链表中的位置，随后将其移动到双向链表头部，即可在 O(1) 时间内完成 get 或 put 。具体的方法如下：
对于 get，首先判断 key 是否存在：如果 key 不存在，则返回 −1；如果 key 存在，则 key 对应的节点是最近被使用的节点。
* 通过哈希表定位到该节点在双向链表中的位置，并将其移动到双向链表的头部，最后返回该节点的值。
对于 put，首先判断 key 是否存在：如果 key 不存在，使用 key 和 value 创建一个新的节点，在双向链表的头部添加该节点，
* 并将 key 和该节点添加进哈希表中。然后判断双向链表的节点数是否超出容量，如果超出容量，则删除双向链表的尾部节点，并删除哈希表中对应的项；
如果 key 存在，则与 get 类似，先通过哈希表定位，再将对应的节点的值更新为 value，并将该节点移到双向链表的头部。
上述各项操作中，访问哈希表的时间复杂度为 O(1)，在双向链表的头部添加节点、在双向链表的尾部删除节点的复杂度也为O(1)。
* 而将一个节点移到双向链表的头部，可以分成「删除该节点」和「在双向链表的头部添加节点」两步操作，都可以在 O(1) 时间内完成。
在双向链表的实现中，使用一个伪头部（dummy head）和伪尾部（dummy tail）标记界限，这样在添加节点和删除节点的时候就不需要检查相邻节点是否存在。
* */
//实现一个LRU(最近最少使用)。
//LRUCache(int capacity) 以capacity为容量初始化LRU。int get(int key) 如果key在缓存返回关键字值否则返回-1。
//void put(int key, int value)如果key已存在则变更value，不存在则插入k-v。如果插入导致超过容量应逐出最久未使用key
public class _146 {
    class LRUCache {
        class DLinkedNode { //双向链表
            int key; //存放k和v
            int value;
            DLinkedNode prev;//前后指针
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }

        //哈希表辅以双向链表实现，哈希表存放key到节点
        private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;  //当前节点数
        private int capacity; //最大容量
        private DLinkedNode head, tail; //头尾节点

        public LRUCache(int capacity) {//构造方法
            //初始化size、容量。头尾节点，头尾互相指向对方
            this.size = 0;
            this.capacity = capacity;
            // 使用伪头部和伪尾部节点
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {//从缓存中获取指定key的节点
            DLinkedNode node = cache.get(key);
            if (node == null) {//如果节点为空，返回-1
                return -1;
            }
            //将node删除再插入到头节点，保证最新访问的在最前面
            moveToHead(node);
            return node.value;//返回节点值
        }

        //放入
        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);//获取key对应的node
            if (node == null) {
                // 如果node不存在，根据k和v创建新节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);// node添加进哈希表
                addToHead(newNode); // node添加至链表头
                ++size; //更新size
                if (size > capacity) {
                    DLinkedNode tail = removeTail();//如果超出容量删除尾节点
                    cache.remove(tail.key);//删除哈希表中对应项
                    --size;//size--
                }
            } else {
                //如果node存在，更新value并移到头部
                node.value = value;
                moveToHead(node);
            }
        }

        //节点添加到头，双向链表添加节点要移动四个指针
        private void addToHead(DLinkedNode node) {
            //head是虚拟节点，因此让node的pre指向head
            //node的next指向head的next
            node.prev = head;
            node.next = head.next;
            //head的next的pre指向node
            //head的next指向node
            head.next.prev = node;
            head.next = node;
        }

        //删除某个节点，要移动两个指针
        private void removeNode(DLinkedNode node) {
            //node的前节点的next指向node的next，跳过node
            node.prev.next = node.next;
            //node的后节点的pre指向node的pre，也跳过node实现删除node
            node.next.prev = node.prev;
        }

        //先将node删除，然后将node添加到头
        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        //删除尾节点
        private DLinkedNode removeTail() {
            //tail也是虚拟节点，获取tail的pre是当前的尾节点，将其删除
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}
