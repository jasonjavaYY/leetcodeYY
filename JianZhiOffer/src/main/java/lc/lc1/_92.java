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
//链表 给你单链表头head和两个数left和right，反转从left到right的链表节点，返回反转后的链表
public class _92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 因为left可能是0即从头开始翻转，因此要建立哑节点
        ListNode dummyNode = new ListNode(-1); //设置哑节点
        dummyNode.next = head; //让哑节点next指向头
        ListNode pre = dummyNode; //找到pre，即left的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next; //cur指向待翻转区第一个节点，cur不断后移
        ListNode next; //next指向cur下一个节点
        for (int i = 0; i < right - left; i++) {//需要翻转right-left次
            next = cur.next; //curr 的下一个节点记录为 next
            cur.next = next.next; //curr的next指向 next 的next
            next.next = pre.next;//next 的next指向 pre 的next
            pre.next = next;//pre 的next指向 next
        }
        return dummyNode.next; //返回头节点，也就是哑节点的next
    }

    private class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
