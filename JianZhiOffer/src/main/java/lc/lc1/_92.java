package lc.lc1;

/*
* 反转链表 II
*
* 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
*
* 示例 1：
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
示例 2：
输入：head = [5], left = 1, right = 1
输出：[5]
*
* 整体思想是：在需要反转的区间里，每遍历到一个节点，让这个新节点来到反转部分的起始位置。
* 下面我们具体解释如何实现。使用三个指针变量 pre、curr、next 来记录反转的过程中需要的变量，它们的意义如下：
curr：指向待反转区域的第一个节点 left；
next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
*
* 操作步骤：
先将 curr 的下一个节点记录为 next；
执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
执行操作 ③：把 pre 的下一个节点指向 next。
* */
public class _92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
