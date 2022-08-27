package jzoffer;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 从尾到头打印链表
 *
 * 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序
 * */
//从尾到头打印链表
public class _4 {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>(); //构造一个栈
        while (listNode != null) { //将链表值依次放入栈
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        //依次弹栈元素就是反向链表顺序
        while (!stack.isEmpty())
            ret.add(stack.pop());
        return ret;
    }

    static class ListNode{
        private int val;
        private ListNode next;
    }
}
