package lc.lc1;
/*
* Z 字形变换
*
* 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
* 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
P   A   H   N
A P L S I I G
Y   I   R
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
请你实现这个将字符串进行指定行数变换的函数：
string convert(string s, int numRows);
*
输入：s = "PAYPALISHIRING", numRows = 4
输出："PINALSIGYAHRPI"
解释：
P     I    N
A   L S  I G
Y A   H R
P     I
*
* 设 n 为字符串 s的长度，r=numRows。对于r=1（只有一行）或者r≥n（只有一列）的情况，答案与s 相同，我们可以直接返回s。
* 对于其余情况，考虑创建一个二维矩阵，然后在矩阵上按 Z 字形填写字符串 s，最后逐行扫描矩阵中的非空字符，组成答案。
* 根据题意，当我们在矩阵上填写字符时，会向下填写 r个字符，然后向右上继续填写r−2 个字符，最后回到第一行，因此 Z 字形变换的周期
* t=r+r−2=2r−2个字符，每个周期会占用矩阵上的 1+r−2=r−1 列。因此我们有 n/t个周期（最后一个周期视作完整周期），乘上每个周期的列数，
* 得到矩阵的列数 c=(n+t-1)/t*(r−1)。创建一个 r行 c列的矩阵，然后遍历字符串 s并按 Z 字形填写。具体来说，设当前填写的位置为 (x,y)，
* 即矩阵的 x 行 y列。初始(x,y)=(0,0)，即矩阵左上角。若当前字符下标 i满足 i mod t<r−1，则向下移动，否则向右上移动。
* 填写完成后，逐行扫描矩阵中的非空字符，组成答案。
* */
public class _6 {
    class Solution {
        public String convert(String s, int numRows) {
            int n = s.length(), r = numRows;
            if (r == 1 || r >= n) { //如果只拍一行或者排的行数超过字符串长度，直接返回原字符串
                return s;
            }
            int t = r * 2 - 2; //t=2r-2
            int c = (n + t - 1) / t * (r - 1);  //c列
            char[][] mat = new char[r][c];  //构造存放数组，r行c列
            for (int i = 0, x = 0, y = 0; i < n; ++i) {
                //遍历原字符数组
                mat[x][y] = s.charAt(i);
                if (i % t < r - 1) { //i mod t<r−1，则向下移动
                    ++x; // 向下移动
                } else {  //否则向右上移动
                    --x;
                    ++y; // 向右上移动
                }
            }
            StringBuffer ans = new StringBuffer();
            //遍历每一行，如果不是空字符，就拼接到ans
            for (char[] row : mat) {
                for (char ch : row) {
                    if (ch != 0) {
                        ans.append(ch);
                    }
                }
            }
            return ans.toString();
        }
    }
}
