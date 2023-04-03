package lc.lc3;

import java.util.Stack;

/*
 * 用栈实现队列
 * */
public class _232 {
    //同jzoffer的_7
    class MyQueue {

        Stack<Integer> in = new Stack<Integer>(); //入栈
        Stack<Integer> out = new Stack<Integer>();  //出栈

        public MyQueue() {

        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            if (!empty()) {
                return out.pop();
            } else {
                return 0;
            }
        }

        public int peek() {
            if (!empty()) {
                return out.peek();
            } else {
                return 0;
            }
        }

        public boolean empty() {
            if (out.isEmpty())
                while (!in.isEmpty())
                    out.push(in.pop());
            return out.isEmpty();
        }
    }
}
