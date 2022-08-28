package lc.lc2;

/*
* 环形链表
*
* 给你一个链表的头节点 head ，判断链表中是否有环。如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
* 则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
* 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。如果链表中存在环 ，则返回 true 。 否则，返回 false 。
*
* 方法二：快慢指针
假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表上的同一个节点开始移动时，
* 如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；如果该链表中有环，那么「兔子」会先于「乌龟」进入环，
* 并且一直在环内移动。等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。
* 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
* */
//一个链表的头节点，判断链表是否有环
public class _141 {
    public boolean hasCycle(ListNode head) {
        //如果链表为空或者只有一个节点，返回false
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head; //定义快慢指针，快指针在前
        ListNode fast = head.next;
        while (slow != fast) {//循环判断快慢指针是否指向同节点
            //如果快指针到末尾了，说明没有环，返回false
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next; //更新快慢指针
            fast = fast.next.next;
        }
        return true; //如果能退出，说明快慢指针相遇，有环返回true
    }

    class ListNode {
        ListNode next;
    }
}
