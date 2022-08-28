package jzoffer;

import java.util.Stack;

/*
* 用两个栈实现队列,完成队列的 Push 和 Pop 操作
*
in 栈用来处理入栈（push）操作，out 栈用来处理出栈（pop）操作。
* 一个元素进入 in 栈之后，出栈的顺序被反转。当元素要出栈时，需要先进入out栈，
* 此时元素出栈顺序再一次被反转，因此出栈顺序就和最开始入栈顺序相同，就是队列的顺序。
* */
//栈 两个栈实现队列,完成 Push 和 Pop
public class _7 {
    Stack<Integer> in = new Stack<Integer>(); //入栈
    Stack<Integer> out = new Stack<Integer>();  //出栈

    //push方法入栈直接push即可
    public void push(int node) {
        in.push(node);
    }

    //弹出方法：不断将in的元素依次放入out栈，最后弹出out栈顶元素
    public int pop() throws Exception {
        if (out.isEmpty())
            while (!in.isEmpty())
                out.push(in.pop());
        if (out.isEmpty())
            throw new Exception("queue is empty");
        return out.pop();
    }
}
