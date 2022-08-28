package lc.lc4;

/*
* 转换成小写字母
*
* 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
示例 1：
输入：s = "Hello"
输出："hello"
示例 2：
输入：s = "here"
输出："here"
示例 3：
输入：s = "LOVELY"
输出："lovely"
*
* 可以发现，由于 [65,96] 对应的二进制表示为 [(01000001)_2, (01011010)_2]，32 二进制表示为 (00100000)_2，对于
* [(01000001)_2, (01011010)_2]内所有数，表示 32 的二进制位都是 0，因此可以对 ch 的 ASCII 码与 32 按位或，替代与 32 的加法运算。
* */
//将字符串S中大写字母转换成相同的小写字母，返回新字符串
public class _709 {
    public String toLowerCase(String s) {
        //用于存储新字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            //遍历原字符串每个字符
            char ch = s.charAt(i);
            //如果asc码是[65 90]，就+32，也就是|=32
            if (ch >= 65 && ch <= 90) {
                ch |= 32;
            }
            sb.append(ch);//将字符依次加入sb
        }//返回转换后的字符串
        return sb.toString();
    }
}
