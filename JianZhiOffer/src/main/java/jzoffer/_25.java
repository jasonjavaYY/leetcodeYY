package jzoffer;
/*
* 反转链表
*
* 递归
* */
//链表 反转链表
public class _25 {
    public ListNode ReverseList(ListNode head) {
        //如果为空或者只有一个节点，直接返回，因为翻转之后还是原链表
        if (head == null || head.next == null)
            return head;
        //获取头的next，头的next指向空
        ListNode next = head.next;
        head.next = null;
        ListNode newHead = ReverseList(next); //翻转next
        //next的next指向头
        next.next = head;
        return newHead; //返回新的头
    }
    class ListNode{
        ListNode next;
    }
}
