package lc.lc2;

/*
* 环形链表 II
*
* 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。为了表示给定链表中的环，评测系统内部使用整数 pos
* 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
*
* 方法二：快慢指针
我们使用两个指针，fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。
* 如果链表中存在环，则 fast 指针最终将再次与slow 指针在环中相遇。设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离
* 与 fast 相遇。此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc。
* 根据题意，任意时刻，fast 指针走过的距离都为slow 指针的 2 倍。因此，我们有
a+(n+1)b+nc=2(a+b)⟹a=c+(n−1)(b+c)
有了 a=c+(n−1)(b+c) 的等量关系，我们会发现：从相遇点到入环点的距离加上 n−1 圈的环长，恰好等于从链表头部到入环点的距离。
因此，当发现 slow 与 fast 相遇时，我们再额外使用一个指针 ptr。起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们在入环点相遇。
* */
//给一个链表头节点，返回链表入环第一个节点。如果无环返回 null
public class _142 {
    public ListNode detectCycle(ListNode head) {
        if (head == null) { //如果链表为空返回空
            return null;
        } //定义快慢指针
        ListNode slow = head, fast = head;
        //因为可能无环，所以不能用fast!=slow判断
        while (fast != null) {//遍历判断快指针是否到头
            slow = slow.next; //更新慢指针
            //如果快指针下一节点到头，返回null代表无环，否则走两步
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            } //如果快慢指针相遇
            if (fast == slow) {
                //使用指针ptr指向头部
                ListNode ptr = head;
                //ptr和 slow每次向后移动。最终在入环点相遇
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr; //返回相遇点
            }
        }
        return null; //如果快指针到头了，说明无环，返回null
    }

    class ListNode {
        ListNode next;
    }
}
