package jzoffer;
/*
* 复杂链表的复制
*
* 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，
* 另一个特殊指针指向任意一个节点），返回结果为复制后复杂链表的 head
*
* 第一步，在每个节点的后面插入复制的节点
* 第二步，对复制节点的 random 链接进行赋值
* 第三步，拆分
* */
//输入一个复杂链表，节点有节点值及两个指针，一个指向下一节点，另一个指向任意节点，返回复制后复杂链表的head
public class _38 {
    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) //如果原链表为空，返回空
            return null;
        // 在原链表每个节点后面插入相同新节点
        RandomListNode cur = pHead;
        while (cur != null) {
            RandomListNode clone = new RandomListNode(cur.label);
            clone.next = cur.next;
            cur.next = clone;
            cur = clone.next;
        }
        // 建立 random 链接
        cur = pHead;
        while (cur != null) {
            RandomListNode clone = cur.next;
            //如果有random，说明是原链表节点，就把当前节点的next（clone链表节点）的random
            //设置为当前的节点的random的next
            if (cur.random != null)
                clone.random = cur.random.next;
            cur = clone.next;
        }
        // 拆分
        cur = pHead;
        RandomListNode pCloneHead = pHead.next;
        while (cur.next != null) {
            //让原链表的1指向原链表的2，clone链表的1指向clone链表的2
            //以此向后遍历节点拆分
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return pCloneHead;
    }
}
