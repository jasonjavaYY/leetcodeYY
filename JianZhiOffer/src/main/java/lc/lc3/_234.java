package lc.lc3;

/*
* 回文链表
*
* 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
示例 1：
输入：head = [1,2,2,1]
输出：true
示例 2：
输入：head = [1,2]
输出：false
*
* 方法三：快慢指针
将链表的后半部分反转（修改链表结构），然后将前半部分和后半部分进行比较。
整个流程可以分为以下五个步骤：
找到前半部分链表的尾节点。反转后半部分链表。判断是否回文。恢复链表。返回结果。
执行步骤一，我们可以使用快慢指针在一次遍历中找到：慢指针一次走一步，快指针一次走两步，快慢指针同时出发。
* 当快指针移动到链表的末尾时，慢指针恰好到链表的中间。通过慢指针将链表分为两部分。
若链表有奇数个节点，则中间的节点应该看作是前半部分。
步骤二可以使用「206. 反转链表」问题中的解决方法来反转链表的后半部分。
步骤三比较两个部分的值，当后半部分到达末尾则比较完成，可以忽略计数情况中的中间节点。
步骤四与步骤二使用的函数相同，再反转一次恢复链表本身。
* */
//链表 一个单链表头节点head ，判断是否为回文链表
public class _234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {//如果链表为空，返回true
            return true;
        }
        // 找到前半部分链表尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
        // 判断是否回文，p1指向头，p2指向后半部分的头
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {//当标志位为true且p2没到终点时
            if (p1.val != p2.val) {//如果p1和p2值不相等，返回false
                result = false;
            }//否则p1和p2依次移动一位
            p1 = p1.next;
            p2 = p2.next;
        }
        // 翻转后半部分链表，还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    //ok 翻转链表
    private ListNode reverseList(ListNode head) {
        ListNode prev = null; //pre=null
        ListNode curr = head; //cur指向head
        while (curr != null) {//循环
            //四部曲，构造temp是cur的next
            //cur的next是pre，pre=cur,cur=temp
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev; //返回pre
    }

    //ok 找到链表中间节点
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;//构造快慢指针，最初都指向head
        ListNode slow = head;//当快指针的next和next.next都不到末尾时
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;//移动快慢指针
        }
        return slow;//返回慢指针
    }

    class ListNode{
        ListNode next;
        int val;
    }
}
