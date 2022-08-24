package lc.lc1;

/*
* 整数转罗马数字
*
* 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
* 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给你一个整数，将其转为罗马数字。
*
* 思路一：贪心
贪心法则：我们每次尽量使用最大的数来表示。 比如对于 1994 这个数，如果我们每次尽量用最大的数来表示，依次选 1000，900，90，4，
* 会得到正确结果 MCMXCIV。所以，我们将哈希表按照从大到小的顺序排列，然后遍历哈希表，直到表示完整个输入。
* */
public class _12 {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1}; //保存1-4-5-9到1000整数数值
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"}; //保存对应罗马数值

    public String intToRoman(int num) {
        StringBuffer roman = new StringBuffer(); //构造结果字符串
        for (int i = 0; i < values.length; ++i) { //遍历values数组
            int value = values[i];
            String symbol = symbols[i];
            //取出小于num的最大数，注意这里要用while，因为num-value之后可能还大于value
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {//num为0是退出条件
                break;
            }
        }
        return roman.toString();
    }
}
