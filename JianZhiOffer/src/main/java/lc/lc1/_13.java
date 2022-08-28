package lc.lc1;

import java.util.HashMap;
import java.util.Map;

/*
* 罗马数字转整数
*
* 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
* 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。
*
* 方法一：模拟
思路
通常情况下，罗马数字中小的数字在大的数字的右边。若输入的字符串满足该情况，那么可以将每个字符视作一个单独的值，累加每个字符对应的数值即可。
例如 XXVII 可视作 X+X+V+I+I=10+10+5+1+1=27。若存在小的数字在大的数字的左边的情况，根据规则需要减去小的数字。
* 对于这种情况，我们也可以将每个字符视作一个单独的值，若一个数字右侧的数字比它大，则将该数字的符号取反。
* 例如XIV 可视作 X−I+V=10−1+5=14。
* */
//数学 罗马数字转整数
public class _13 {
    Map<Character, Integer> symbolValues = new HashMap<Character, Integer>() {{
        put('I', 1);put('V', 5);put('X', 10);put('L', 50);
        put('C', 100);put('D', 500);put('M', 1000);
    }}; //映射表只需要记录1-5，因为4=IV也是由1和5组成的，只不过是5的符号-1的符号

    public int romanToInt(String s) {
        int ans = 0;
        int n = s.length(); //获取传入字符串长度
        for (int i = 0; i < n; ++i) { //逐步取出字符串中每位字符，一定是映射表中的一个
            int value = symbolValues.get(s.charAt(i)); //计算字符对应的值
            //如果第i位的值小于i+1(它后面一位)，说明要把i位的值减去，否则要加上
            if (i < n - 1 && value < symbolValues.get(s.charAt(i + 1))) {
                ans -= value;
            } else {
                ans += value;
            }
        }
        return ans; //最后返回结果
    }
}
