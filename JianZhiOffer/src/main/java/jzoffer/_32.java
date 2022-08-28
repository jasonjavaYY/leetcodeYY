package jzoffer;

import java.util.Stack;

/*
* 栈的压入、弹出序列
*
* 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
例如序列 1,2,3,4,5 是某栈的压入顺序，序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
* */
//栈 输入两个整数序列，第一个序列表示栈压入顺序，判断第二序列是否为该栈弹出顺序。假设入栈所有数字不相等。
public class _32 {
    public static void main(String[] args) {
        int[] pushSequence = new int[]{1,2,3,4,5};
        int[] popSequence = new int[]{1,4,3,2,5};
        System.out.println(IsPopOrder(pushSequence, popSequence));
    }

    public static boolean IsPopOrder(int[] pushSequence, int[] popSequence) {
        //计算入栈元素数
        int n = pushSequence.length;
        //构造一个辅助栈，按照入栈序列把元素放入辅助栈，然后去出栈序列中找这个元素
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            //按照入栈顺序把元素依次放入stack
            stack.push(pushSequence[pushIndex]);
            //每次入栈一个元素，就看当前stack栈顶是否是弹栈序列中待找元素，如果是就弹出，弹栈下标++继续找
            //直到栈顶元素和待弹栈元素不一致，就下一轮循环继续把元素入栈
            while (popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
