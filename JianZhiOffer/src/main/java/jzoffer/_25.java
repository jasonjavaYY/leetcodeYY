package jzoffer;
/*
* 反转链表
*
* 递归
* */
public class _25 {
    public ListNode ReverseList(ListNode head) {
        //如果为空或者只有一个节点，直接返回，因为翻转之后还是原链表
        if (head == null || head.next == null)
            return head;
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = ReverseList(next);
        next.next = head;
        return newHead;
    }
    class ListNode{
        ListNode next;
    }
}
