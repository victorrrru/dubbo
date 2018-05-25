package com.fww.arithmetic;

import java.util.Arrays;

/**
 * @author 范文武
 * @date 2018/05/24 17:50
 */
public class MergeSort {
    private static Integer[] aux; // 用于排序的辅助数组

    public static void main(String[] args) {
        sort(new Integer[]{
                1, 9, 18, 20, 14, 16, 56, 89
        });
    }
    public static void sort(Integer[] array)
    {
        aux = new Integer[array.length]; // 仅分配一次
        sort(array, 0, array.length - 1);
    }
    private static void sort(Integer[] array, int lo, int hi)
    {
        if (lo >= hi) {
            return; //如果下标大于上标，则返回
        }
        int mid = lo + (hi - lo) / 2;//平分数组
        sort(array, lo, mid);//循环对左侧元素排序
        sort(array, mid + 1, hi);//循环对右侧元素排序
        merge(array, lo, mid, hi);//对左右排好的序列进行合并
    }

    private static void merge(Integer[] array, int lo, int mid, int hi) {
        int[] aux = new int[8];
        int i = lo, j = mid + 1;
        //把元素拷贝到辅助数组中
        for (int k = lo; k < hi; k++) {
            aux[k] = array[k];
        }
        //然后按照规则将数据从辅助数组中拷贝回原始的array中
        for (int k = lo; k < hi; k++) {
            //如果左边元素没了， 直接将右边的剩余元素都合并到到原数组中
            if (i > mid) {
                array[k] = aux[j++];
            }//如果右边元素没有了，直接将所有左边剩余元素都合并到原数组中
            else if (j > hi) {
                array[k] = aux[i++];
            }//如果左边右边小，则将左边的元素拷贝到原数组中
            else if (aux[i] < aux[j]) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
        Arrays.asList(array).forEach(System.out::println);
    }
}
