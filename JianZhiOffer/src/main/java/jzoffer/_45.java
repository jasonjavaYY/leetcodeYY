package jzoffer;

import java.util.LinkedList;
import java.util.Queue;

/*
* 字符流中第一个不重复的字符
*
* 请实现一个函数用来找出字符流中第一个只出现一次的字符。
* 例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。
* 当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。
* */
//栈 返回字符流中第一个不重复字符，例如当读出"go" 时，第一个不重复字符是 "g"，当读出“google" 时字符是 "l"
public class _45 {
    //一共只有256个字符，用cnts代表各个字符出现的次数
    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    public void Insert(char ch) {
        //插入一个字符，先把字符的asc码位置的cnts+1，然后将字符放入队列
        cnts[ch]++;
        queue.add(ch);
        //如果队列不为空并且当前字符已经出现超过1次，就弹出当前字符
        //保证queue中只保留出现一次的字符，且后插入的放在队尾
        while (!queue.isEmpty() && cnts[queue.peek()] > 1)
            queue.poll();
    }

    public char FirstAppearingOnce() {
        //弹出队首元素就是第一个只出现一次的字符
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
