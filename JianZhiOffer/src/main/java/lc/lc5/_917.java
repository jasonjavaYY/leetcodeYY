package lc.lc5;

/*
* 仅仅反转字母
*
* 给你一个字符串 s ，根据下述规则反转字符串：所有非英文字母保留在原有位置。所有英文字母位置反转。返回反转后的 s 。
示例 1：
输入：s = "ab-cd"
输出："dc-ba"
示例 2：
输入：s = "a-bC-dEf-ghIj"
输出："j-Ih-gfE-dCba"
示例 3：
输入：s = "Test1ng-Leet=code-Q!"
输出："Qedo1ct-eeLg=ntse-T!"
*
* 方法一：双指针
使用 left 指针从左边开始扫描字符串 s，right 指针从右边开始扫描字符串 s。如果两指针都扫描到字母，且 left<right，交换 s[left] 和 rs[right]，
* 然后继续进行扫描；否则表明反转过程结束，返回处理后的字符串。
* */
//字符串s，非英文字母保留原位置。英文字母位置反转。返回反转后的s
public class _917 {
    public String reverseOnlyLetters(String s) {
        int n = s.length();//计算字符串长度n
        char[] arr = s.toCharArray();//字符串转换为字符数组
        int left = 0, right = n - 1;//初始化左右指针
        while (true) {//循环
            //左指针跳过所有非字母字符
            while (left < right && !Character.isLetter(s.charAt(left))) {
                left++;
            }//右指针跳过所有非字母字符
            while (right > left && !Character.isLetter(s.charAt(right))) {
                right--;
            }//如果左大于等于右，跳出循环
            if (left >= right) {
                break;
            }//否则交换左右字符，左右靠近一位
            swap(arr, left, right);
            left++;
            right--;
        }
        return new String(arr);
    }
    public void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
