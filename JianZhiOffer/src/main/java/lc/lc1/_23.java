package lc.lc1;

/*
* 合并K个升序链表
*
* 给你一个链表数组，每个链表都已经按升序排列。请你将所有链表合并到一个升序链表中，返回合并后的链表。
示例 1：
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
示例 2：
输入：lists = []
输出：[]
示例 3：
输入：lists = [[]]
输出：[]
*
将 k 个链表配对并将同一对中的链表合并；第一轮合并以后， k 个链表被合并成了 k/2个链表，平均长度为2n/k，
* 然后是k/4个链表，k/8个链表等等；重复这一过程，直到我们得到了最终的有序链表。
* */
//链表 一个链表数组每个链表都升序。将所有链表合并到一个升序链表，返回合并后的链表
public class _23 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) { //如果l=r，直接返回对应链表
            return lists[l];
        }
        if (l > r) { //如果l>r，返回空
            return null;
        }
        int mid = (l + r) >> 1; //否则求l和r的中间
        //递归合并l到mid和mid+1到r，最后将两个链表合并
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) { //如果a或者b有空的，就返回另一个
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0); //构造合并后的链表头和尾，头是一个虚拟节点
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {//循环判断两个链表都不为空
            //如果a的节点<b的节点值，就把a的节点从a中取出放入tail后面，a后移一个节点
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {//否则就把b的节点从b中取出放入tail后面，b后移一个节点
                tail.next = bPtr;
                bPtr = bPtr.next;
            } //更新tail
            tail = tail.next;
        }//最后把不为空的剩余链表节点都拼到tail后面
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next; //返回拼接后的链表，因为头指向虚拟节点，返回头的next
    }

    class ListNode {
        private int val;
        public ListNode(int value) {
            this.val = value;
        }
        ListNode next;
    }

}
