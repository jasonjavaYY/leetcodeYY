package lc.lc1;
/*
* 两数相加
*
* 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序
* 的方式存储的，并且每个节点只能存储 一位 数字。
* 请你将两个数相加，并以相同形式返回一个表示和的链表。
* 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
*
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807
*
输入：l1 = [0], l2 = [0]
输出：[0]
*
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
*
* 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
* 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表
* 处相应位置的数字为 n1,n2，进位值为carry，则它们的和为
* n1+n2+carry；其中，答案链表处相应位置的数字为 (n1+n2+carry)mod 10，
* 而新的进位值为 (n1+n2+carry)/10,如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个0 。
* 此外，如果链表遍历结束后，有 carry > 0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
* */
public class _2 {
    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = null, tail = null;
            int carry = 0;
            while (l1 != null || l2 != null) {
                //获取两个链表相同位置节点的值，求和，记得加上carry，最开始的carry为0
                //如果链表元素为null，就把值看做0
                int n1 = l1 != null ? l1.val : 0;
                int n2 = l2 != null ? l2.val : 0;
                int sum = n1 + n2 + carry;
                //如果是第一轮计算，同事更新头结点和尾节点，值为sum%10
                if (head == null) {
                    head = tail = new ListNode(sum % 10);
                } else {
                    //否则只需要更新尾节点
                    tail.next = new ListNode(sum % 10);
                    tail = tail.next;
                }
                //计算一次和，就要计算进位
                carry = sum / 10;
                //同时移动l1和l2
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            //如果最后一位计算出来的carry也不为0，要在最后再插入一个节点保存进位
            if (carry > 0) {
                tail.next = new ListNode(carry);
            }//最后返回链表
            return head;
        }
    }
    class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
