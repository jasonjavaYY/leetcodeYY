package lc.lc2;

/*
* 相交链表
*
* 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
*
* 使用双指针的方法，可以将空间复杂度降至 O(1)。
只有当链表 headA 和 headB 都不为空时，两个链表才可能相交。因此首先判断链表 headA 和 headB 是否为空，如果其中至少有一个链表为空，
* 则两个链表一定不相交，返回 null。当链表 headA 和 headB 都不为空时，创建两个指针 pA 和pB，初始时分别指向两个链表的头节点headA
* 和 headB，然后将两个指针依次遍历两个链表的每个节点。具体做法如下：
每步操作需要同时更新指针 pA 和 pB。如果指针pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表headA 的头节点。
当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者null。
* */
public class _160 {
    //同jzoffer的_54
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    class ListNode {
        ListNode next;
    }
}
