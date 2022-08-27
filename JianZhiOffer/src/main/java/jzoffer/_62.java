package jzoffer;
/*
* 翻转单词顺序列
*
Input:
"I am a student."

Output:
"student. a am I"
*
* 题目应该有一个隐含条件，就是不能用额外的空间。虽然Java的题目输入参数为 String类型，
* 需要先创建一个字符数组使得空间复杂度为 O(N)，但是正确的参数类型应该和原书一样，为字符数组，
* 并且只能使用该字符数组的空间。任何使用了额外空间的解法在面试时都会大打折扣，包括递归解法。
* 正确的解法应该是和书上一样，先旋转每个单词，再旋转整个字符串。
* */
//翻转单词顺序列，如将"I am a student."变为"student. a am I"
public class _62 {
    public static void main(String[] args) {
        System.out.println(ReverseSentence("I am a student."));
    }
    public static String ReverseSentence(String str) {
        //"I am a student."
        int n = str.length();  //获取字符串总长度
        char[] chars = str.toCharArray(); //字符串转换为字符数组
        int i = 0, j = 0;
        while (j <= n) { //遍历整个字符串，翻转每个单词
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        //此时变为："I ma a .tneduts"
        //最后再整体翻转，变为："student. a am I"
        reverse(chars, 0, n - 1);
        return new String(chars);
    }

    //翻转一个字符数组（单词）
    private static void reverse(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    private static void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }
}
