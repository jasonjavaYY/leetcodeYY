package jzoffer;

import java.util.Stack;

/*
* 包含 min 函数的栈
*
* 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的 min 函数。
* */
public class _31 {
    private Stack<Integer> dataStack = new Stack<>();  //存放原数据序列的栈
    private Stack<Integer> minStack = new Stack<>();  //保证栈顶存放当前栈内最小的元素

    //关键是入栈，数据栈直接入栈，最小栈需要比较待入栈元素和当前栈顶元素谁更小，
    // 放入那个小值，保证最小栈的栈顶元素一定是当前数据栈内容的最小值
    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    //弹栈一起弹出
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    //ok  查看最顶元素就调用数据栈的peek
    public int top() {
        return dataStack.peek();
    }

    //ok获取最小元素就调用最小栈的peek
    public int min() {
        return minStack.peek();
    }
}
