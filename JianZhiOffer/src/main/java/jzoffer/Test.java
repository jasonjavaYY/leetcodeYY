package jzoffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(combine(nums));
    }

    public static List<List<Integer>> combine(int[] nums) {
        List<Integer> combine = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int num : nums) {
            combine.add(num);
        }
        backTracing(result, combine, n, 0);
        return result;
    }

    private static void backTracing(List<List<Integer>> result, List<Integer> combine, int n, int idx) {
        if (idx == n) {
            result.add(combine);
        }
        for (int i = idx; i < n; i++) {
            Collections.swap(combine,idx,i);
            backTracing(result, combine, n, idx+1);
            Collections.swap(combine,idx,i);
        }
    }
}
