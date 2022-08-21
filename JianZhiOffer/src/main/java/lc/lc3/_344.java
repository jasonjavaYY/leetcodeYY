package lc.lc3;

/*
* 反转字符串
*
* 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
示例 1：
输入：s = ["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：
输入：s = ["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
*
* 方法一：双指针
对于长度为 N 的待被反转的字符数组，我们可以观察反转前后下标的变化，假设反转前字符数组为 s[0] s[1] s[2] ... s[N - 1]，
* 那么反转后字符数组为 s[N - 1] s[N - 2] ... s[0]。容易得出 s[i] 的字符与 s[N - 1 - i] 的字符发生了交换，可以得出如下双指针的解法：
将 left 指向字符数组首元素，right 指向字符数组尾元素。
当 left < right：交换 s[left] 和 s[right]；left 指针右移一位，即 left = left + 1；right 指针左移一位，即 right = right - 1。
当 left >= right，反转结束，返回字符数组即可。
* */
public class _344 {
    public void reverseString(char[] s) {
        int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
    }
}
