package com.fww.arithmetic;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2018/02/28 14:12
 */
public class TwoSum {

    @Test
    public void test1() {
        System.out.println("1");
    }

    /**
     * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
     * @author fww
     */
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0, j = nums.length; i < j; i++) {
            for(int a = i + 1, b = nums.length; a < b; a++) {
                if (nums[i] + nums[a] == target) {
                    return new int[]{i, a};
                }
            }
        }
        return new int[]{};
    }
    /**
     * 最笨的方法
     * time complexity: O(n^2)
     * space complexity: O(1)
     */
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 引入map,哈希表支持几乎在恒定的时间内快速查找O（1），如果碰撞则退化为O（n）
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[] { i, map.get(complement) };
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 引入map,当不存在余数时，插入哈希表，时间复杂度更佳
     * time complexity: O(n)
     * space complexity: O(n)
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        //在读取网络数据流的时候，可以通过先用InputStream获取字节流、InputStreamReader将字节流转化成字符流、BufferedReader将字符流以缓存形式输出的方式来快速获取网络数据流。
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int[] ret = new TwoSum().twoSum(nums, target);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}