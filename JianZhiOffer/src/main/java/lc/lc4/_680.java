package lc.lc4;

/*
* 验证回文字符串 Ⅱ
*
* 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
示例 1:
输入: s = "aba"
输出: true
示例 2:
输入: s = "abca"
输出: true
解释: 你可以删除c字符。
示例 3:
输入: s = "abc"
输出: false
*
* 方法一：贪心
首先判断原串是否是回文串，如果是就返回true；如果不是，则枚举每一个位置作为被删除的位置，再判断剩下的字符串是否是回文串。
* 这种做法的时间复杂度是 O(n^2)。我们换一种想法。首先考虑如果不允许删除字符，如何判断一个字符串是否是回文串。常见的做法是使用双指针。
* 定义左右指针，初始时分别指向字符串的第一个字符和最后一个字符，每次判断左右指针指向的字符是否相同，如果不相同，则不是回文串；如果相同，
* 则将左右指针都往中间移动一位，直到左右指针相遇，则字符串是回文串。在允许最多删除一个字符的情况下，同样可以使用双指针，通过贪心实现。
* 初始化两个指针 low 和 high 分别指向字符串的第一个字符和最后一个字符。每次判断两个指针指向的字符是否相同，如果相同，则更新指针，
* 将 low 加 1，high 减 1，然后判断更新后的指针范围内的子串是否是回文字符串。如果两个指针指向的字符不同，则两个字符中必须有一个被删除，
* 此时我们就分成两种情况：即删除左指针对应的字符，留下子串 s[low+1:high]，或者删除右指针对应的字符，留下子串 s[low:high−1]。
* 当这两个子串中至少有一个是回文串时，就说明原始字符串删除一个字符之后就以成为回文串。
* */
//一个非空字符串s，最多删一个字符判断是否能成回文字符串
public class _680 {
    public boolean validPalindrome(String s) {
        int low = 0, high = s.length() - 1;//初始化低位和高位指针
        while (low < high) {//循环
            char c1 = s.charAt(low), c2 = s.charAt(high);//判断l和h处字符如果相等，就靠近l和h继续判断
            if (c1 == c2) {
                ++low;
                --high;
            } else {//字符如果不相等，可以删除左或右字符，判断l到h-1或者l+1到h是否有一个是回文串
                return validPalindrome(s, low, high - 1) || validPalindrome(s, low + 1, high);
            }
        }
        return true;
    }
    //判断s从low到high的内容是否是回文串
    public boolean validPalindrome(String s, int low, int high) {
        //不断移动l和h，只要两个位置字符串不相等，就返回false，相等就靠近
        for (int i = low, j = high; i < j; ++i, --j) {
            char c1 = s.charAt(i), c2 = s.charAt(j);
            if (c1 != c2) {
                return false;
            }
        }
        return true;//便利完毕都能匹配，返回true
    }
}
