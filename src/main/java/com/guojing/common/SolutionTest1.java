package com.guojing.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个数字的序列，返回其所有可能的全排列。
 * <p>
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author: guojing
 * @date: 2021-06-21
 * @time: 下午5:49
 */
public class SolutionTest1 {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = premute(nums);
        System.out.println(result);
    }


    public static List<List<Integer>> premute(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        // 组合排列后的数组
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        // 声明一个缓存数组用，
        // 作用是：当从 data 中取完元素后，将剩余元素copy 到其中,因为已经取出了一个元素，所以缓存数组大小要-1
        List<Integer> temps = new ArrayList<Integer>();
        helper(nums, result, temps);
        return result;
    }

    /**
     * @param nums   原始待排序数组
     * @param result 排序后的数组
     * @param temps
     */
    public static void helper(int[] nums, List<List<Integer>> result, List<Integer> temps) {
        if (temps.size() == nums.length) {
            result.add(new ArrayList<Integer>(temps));
        }
        for (int i = 0; i < nums.length; i++) {
            if (!temps.contains(nums[i])) {
                temps.add(nums[i]);
                helper(nums, result, temps);
                temps.remove(temps.size() - 1);
            }
        }

    }

}
