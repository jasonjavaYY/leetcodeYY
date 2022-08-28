package lc.lc2;

/*
* 验证回文串
*
* 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
说明：本题中，我们将空字符串定义为有效的回文串。
示例 1:
输入: "A man, a plan, a canal: Panama"
输出: true
解释："amanaplanacanalpanama" 是回文串
示例 2:
输入: "race a car"
输出: false
解释："raceacar" 不是回文串
*
* 方法二：在原字符串上直接判断
我们可以对方法一中第二种判断回文串的方法进行优化，就可以得到只使用 O(1) 空间的算法。
直接在原字符串 s 上使用双指针。在移动任意一个指针时，需要不断地向另一指针的方向移动，直到遇到一个字母或数字字符，
* 或者两指针重合为止。也就是说，我们每次将指针移到下一个字母字符或数字字符，再判断这两个指针指向的字符是否相同。
* */
//回文串 一个字符串，验证是否是回文，只考虑字母和数字，忽略字母大小写
public class _125 {
    public boolean isPalindrome(String s) {
        int n = s.length(); //获取字符串长度n
        int left = 0, right = n - 1; //左右指针
        while (left < right) { //循环
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left; //从左遍历找到字符或数字字符
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right; //从右边找到哦啊字符或数字字符
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false; //如果两个字符不相同，返回false
                } //否则同时更新左右指针
                ++left; --right;
            }
        }
        return true; //如果遍历退出，说明是回文串，返回true
    }
}
