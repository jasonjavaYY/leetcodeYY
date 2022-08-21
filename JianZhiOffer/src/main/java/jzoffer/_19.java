package jzoffer;
/*
* 删除链表中重复的结点
*
*
* */
public class _19 {
    public ListNode deleteDuplication(ListNode pHead) {
        //链表为空或者只有一个元素，肯定没有重复元素，直接返回原链表
        if (pHead == null || pHead.next == null)
            return pHead;
        //获取头的下一个节点
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(next);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    class ListNode{
        private int val;
        private ListNode next;
    }
}
