package jzoffer;
/*
* 两个链表的第一个公共结点
*
* 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a。
*
* 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；
* 同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A。
* 这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
* */
//链表 求两个链表的第一个公共结点
public class _54 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next; //如果l1指向了空，就让l1指向p2，否则指向next
            l2 = (l2 == null) ? pHead1 : l2.next; //如果l2指向了空，就让l1指向p1，否则指向next
        }
        return l1; //最后l1和l2相等，返回任意一个，都是公共节点
    }
    class ListNode{
        ListNode next;
    }
}
