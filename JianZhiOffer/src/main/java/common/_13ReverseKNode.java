package common;

public class _13ReverseKNode {
    //k个一组翻转链表，整体反转更简单，不用考虑拼接回原链表
    public ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next; //先让prev为tail的next，保存反转后的头需要指向节点
        ListNode p = head; //p指向head
        while (prev != tail) { //判断prev不等于tail
            ListNode nex = p.next;//构造next是p的next
            p.next = prev; //p的next指向prev，反向，至此已经完成了翻转
            //调整pre和p，从而翻转下一个节点
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
