package com.guojing.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 全排列
 *
 * @author: guojing
 * @date: 2021-06-22
 * @time: 上午8:14
 */
public class SolutionTest2 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutes = permute(nums);
        System.out.println(permutes);
    }

    /**
     * 数组全排列
     *
     * @param nums 初始数组
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<Integer> selectableNums = new ArrayList<Integer>();
        for (int num : nums) {
            selectableNums.add(num);
        }
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backtrack(new ArrayList<Integer>(), selectableNums, result, selectableNums.size());
        return result;
    }

    /**
     * 递归填充值
     *
     * @param selectedNums   当前已选解
     * @param selectableNums 初始可选解
     * @param result         输出结果
     * @param n              列表最大值
     */
    private static void backtrack(List<Integer> selectedNums, List<Integer> selectableNums, List<List<Integer>> result, int n) {
        // 满足的条件，加入结果集
        if (selectedNums.size() == n) {
            // selectedNums会频繁变化，必须用新的对象记录选择的路径
            result.add(new ArrayList<Integer>(selectedNums));
            return;
        }
        // 遍历当前可选结果
        for (int i = 0; i < selectableNums.size(); i++) {
            Integer num = selectableNums.get(i);
            if (selectedNums.contains(num)) {
                continue;
            }
            // 选择当前阶段其中一个解
            selectedNums.add(num);
            // 选完之后再进入下个阶段遍历
            backtrack(selectedNums, selectableNums, result, n);
            // 回溯,换一个解继续遍历
            selectedNums.remove(num);
        }
    }
}
