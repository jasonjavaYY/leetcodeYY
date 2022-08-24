package lc.lc1;

/*
* K 个一组翻转链表
*
* 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
*
* 我们需要把链表节点按照 k 个一组分组，所以可以使用一个指针 head 依次指向每组的头节点。
* 这个指针每次向前移动 k 步，直至链表结尾。对于每个分组，我们先判断它的长度是否大于等于 k。若是，我们就翻转这部分链表，否则不需要翻转。
* 接下来的问题就是如何翻转一个分组内的子链表。翻转一个链表并不难，过程可以参考「206. 反转链表」。
* 但是对于一个子链表，除了翻转其本身之外，还需要将子链表的头部与上一个子链表连接，以及子链表的尾部与下一个子链表连接。
* 因此，在翻转子链表的时候，我们不仅需要子链表头节点 head，还需要有 head 的上一个节点 pre，以便翻转完后把子链表再接回 pre。
* 但是对于第一个子链表，它的头节点 head 前面是没有节点 pre 的。我们新建一个节点，把它接到链表的头部，让它作为 pre 的初始值，
* 这样 head 前面就有了一个节点，我们就可以避开链表头部的边界条件。这么做还有一个好处，下面我们会看到。
* 反复移动指针 head 与 pre，对 head 所指向的子链表进行翻转，直到结尾，我们就得到了答案。
* 链表翻转之后，链表的头节点发生了变化，那么应该返回哪个节点呢？照理来说，前 k 个节点翻转之后，链表的头节点应该是第 k 个节点。
* 那么要在遍历过程中记录第 k 个节点吗？但是如果链表里面没有 k 个节点，答案又还是原来的头节点。我们又多了一大堆循环和判断要写
* 创建了节点 pre ,这个节点一开始被连接到了头节点的前面，而无论之后链表有没有翻转，它的 next 指针都会指向正确的头节点。
* 那么我们只要返回它的下一个节点就好了。至此，问题解决。
* */
public class _25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0); //构造虚拟节点
        hair.next = head; //虚拟节点指向头
        ListNode pre = hair; //pre指向虚拟节点

        while (head != null) { //遍历链表
            ListNode tail = pre; //先让tail也指向pre
            // 如果剩余部分长度小于 k，就不用翻转，直接返回hair的next也就是头
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next; //保存之前尾节点的next
            ListNode[] reverse = myReverse(head, tail); //翻转head到tail这部分链表
            head = reverse[0]; //获取翻转后的头和尾
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;  //更新pre，因为下一次要反转tail.next开始的子链表，所以pre=tail
            head = tail.next; //头更新为tail.next
        }
        return hair.next; //最后返回虚拟头的next
    }

    //ok  翻转头到尾的链表
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next; //tail的next指向prev
        ListNode p = head; //记录当前节点p
        while (prev != tail) { //循环判断prev不等于tail
            ListNode nex = p.next;//取出p的next
            p.next = prev; //p的next指向prev
            prev = p;//prev等于p
            p = nex;//p等于next
        }
        return new ListNode[]{tail, head}; //最后返回tail到head
    }

    class ListNode {
        private int val;

        public ListNode(int value) {
            this.val = value;
        }

        ListNode next;
    }
}
