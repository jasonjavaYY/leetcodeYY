package jzoffer;
/*
* 合并两个排序的链表
*
* 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
* */
public class _26 {
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode list = null;
        if (list1 == null && list2 == null) return null;
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;
        //如果list1的节点值比list2的值小，list的这个节点就是list1的节点，并将list1节点向后延一个继续merge
        if (list1.val < list2.val) {
            list = list1;
            list.next = Merge(list1.next, list2);
        } else {
            //否则，list的这个节点就是list2的节点，并将list2节点向后延一个继续merge
            list = list2;
            list.next = Merge(list1, list2.next);
        }
        return list;
    }
}
