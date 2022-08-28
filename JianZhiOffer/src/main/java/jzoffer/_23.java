package jzoffer;
/*
* 链表中倒数第 K 个结点
*
* 设链表的长度为 N。设置两个指针 P1 和 P2，先让 P1 移动 K 个节点，
* 则还有 N - K 个节点可以移动。此时让 P1 和 P2 同时移动，
* 可以知道当 P1 移动到链表结尾时，P2 移动到第 N - K 个节点处，该位置就是倒数第 K 个节点
* */
//链表 返回链表中倒数第 K 个结点
public class _23 {
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) return null; //如果链表为空，返回空
        //p1从前往后移动k个节点
        ListNode P1 = head;
        while (P1 != null && k-- > 0)
            P1 = P1.next;
        //如果k>0说明原链表不够k个节点，所以无法找到倒数第k个节点
        if (k > 0) return null;
        //否则让p2指向头节点，p1和p2同时向后移动，p1指向尾节点时，p2指向的就是倒数k节点
        ListNode P2 = head;
        while (P1 != null) {
            P1 = P1.next;
            P2 = P2.next;
        }
        return P2;
    }
    class ListNode{
        ListNode next;
    }
}
