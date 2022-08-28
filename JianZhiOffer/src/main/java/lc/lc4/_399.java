package lc.lc4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 除法求值
*
* 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i]
* 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。另有一些以数组 queries 表示的问题，
* 其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
返回所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，
* 也需要用 -1.0 替代这个答案。注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
示例 1：
输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
解释：
条件：a / b = 2.0, b / c = 3.0
问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
示例 2：
输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
输出：[3.75000,0.40000,5.00000,0.20000]
示例 3：
输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
输出：[0.50000,2.00000,-1.00000,-1.00000]
*
* 请大家注意看一下题目中的「注意」和「数据范围」，例如：每个 Ai 或 Bi 是一个表示单个变量的字符串。所以用例 equation = ["ab", "cd"] ，
* 这里的 ab 视为一个变量，不表示 a * b。如果面试中遇到这样的问题，一定要和面试官确认清楚题目的条件。
* 可以将题目给出的 equation 中的两个变量所在的集合进行「合并」，同在一个集合中的两个变量就可以通过某种方式计算出它们的比值。
* 具体说，可以把不同变量比值转换成为相同变量的比值，就可以消去相同变量，计算转换成相同变量以后的系数的比值，可以以 O(1) 时间复杂度完成计算。
如果两个变量不在同一个集合中， 返回 −1.0。并且根据题目的意思，如果至少有一个变量没出现在所有 equations 出现的字符集合中，也返回 −1.0。
*
* 通过例 1 分析，我们知道题目给出的 equations 和 values 可以表示成一个图，equations 中出现的变量就是图的顶点，「分子」于「分母」
* 的比值可以表示成一个有向关系（有序的，不可以对换），并且这个图是一个带权图，values 就是对应的有向边的权值。
* 为了避免并查集所表示的树形结构高度过高。「路径压缩」的效果是：在查询一个结点 a 的根结点同时，把结点 a 到根结点沿途所有结点的父结点都指向根。
* 由于有「路径压缩」的优化，两个同在一个连通分量中的不同的变量，它们分别到根结点（父亲结点）的权值的比值，就是题目的要求的结果。
* 如何在「查询」操作的「路径压缩」优化中维护权值变化:在结点 a 执行一次「查询」操作。路径压缩会先一层一层向上先找到根结点 d，
* 然后依次把 c、b 、a 的父亲结点指向根结点 d。c 的父结点已经是根，权值不改；b 的父结点要修改成根结点，权值就是从当前结点到根结点经过的所有
* 有向边权值乘积，a 的父亲结点要修改成根结点，它的权值依然是从当前结点到根结点经过的所有有向边的权值的乘积.
* 合并」操作基于这样一个 很重要的前提：我们将要合并的两棵树的高度最多为 22，换句话说两棵树都必需是「路径压缩」以后的效果，
* */
//未完成 一个变量对数组equations和实数值数组values为已知条件，其中equations[i]=[Ai, Bi]和values[i]
//表示Ai / Bi = values[i]。Ai 或 Bi 是表示单个变量的字符串。另有一些以数组 queries 表示的问题，
//queries[j] = [Cj, Dj]，根据已知条件找出 Cj / Dj结果作为答案。返回所有问题答案 。如果无法确定用-1.0
public class _399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int equationsSize = equations.size();//的到方程数
        UnionFind unionFind = new UnionFind(2 * equationsSize);
        // 第 1 步：变量值与id映射
        Map<String, Integer> hashMap = new HashMap<>(2 * equationsSize);
        int id = 0;
        for (int i = 0; i < equationsSize; i++) {//遍历方程
            List<String> equation = equations.get(i);//获取方程中的Ai和Bi字符串
            String var1 = equation.get(0);
            String var2 = equation.get(1);
            if (!hashMap.containsKey(var1)) {//如果映射表不含Ai，将其和id放入
                hashMap.put(var1, id);
                id++;//id++，保证所有变量对应唯一的id
            }//如果映射表不含Bi，将其和id放入
            if (!hashMap.containsKey(var2)) {
                hashMap.put(var2, id);
                id++;
            }
            unionFind.union(hashMap.get(var1), hashMap.get(var2), values[i]);
        }

        // 第 2 步：做查询
        int queriesSize = queries.size();
        double[] res = new double[queriesSize];
        for (int i = 0; i < queriesSize; i++) {
            String var1 = queries.get(i).get(0);
            String var2 = queries.get(i).get(1);
            Integer id1 = hashMap.get(var1);
            Integer id2 = hashMap.get(var2);

            if (id1 == null || id2 == null) {
                res[i] = -1.0d;
            } else {
                res[i] = unionFind.isConnected(id1, id2);
            }
        }
        return res;
    }

    private class UnionFind {
        private int[] parent;
        /**
         * 指向的父结点的权值
         */
        private double[] weight;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.weight = new double[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                weight[i] = 1.0d;
            }
        }
        //x和y代表一组Ai和Bi的id，value是Ai/Bi的值
        public void union(int x, int y, double value) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parent[rootX] = rootY;
            // 关系式的推导请见「参考代码」下方的示意图
            weight[rootX] = weight[y] * value / weight[x];
        }

        /**
         * 路径压缩
         * @param x
         * @return 根结点的 id
         */
        public int find(int x) {
            if (x != parent[x]) {
                int origin = parent[x];
                parent[x] = find(parent[x]);
                weight[x] *= weight[origin];
            }
            return parent[x];
        }

        public double isConnected(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return weight[x] / weight[y];
            } else {
                return -1.0d;
            }
        }
    }
}
