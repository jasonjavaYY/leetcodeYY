package jzoffer;
/*
* 删除链表中重复的结点
* */
//链表 删除链表中重复结点
public class _19 {
    public ListNode deleteDuplication(ListNode pHead) {
        //链表为空或者只有一个元素，肯定没有重复元素，直接返回原链表
        if (pHead == null || pHead.next == null)
            return pHead;
        //获取头的下一个节点
        ListNode next = pHead.next;
        if (pHead.val == next.val) { //如果头结点值和next值相等
            //就不断找值相等的节点，跳过这些节点，直到找到值不等的节点，递归删除该节点
            while (next != null && pHead.val == next.val)
                next = next.next;
            return deleteDuplication(next);
        } else {//如果头结点值和next不等，头的next就是递归删除头.next的节点
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }

    class ListNode{
        private int val;
        private ListNode next;
    }
}
