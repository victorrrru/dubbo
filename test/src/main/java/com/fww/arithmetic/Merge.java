package com.fww.arithmetic;

import java.util.Arrays;

/**
 * @author 范文武
 * @date 2018/05/24 17:26
 */
public class Merge {
    public static void main(String[] args) {
        merge(new Integer[]{
                1, 9, 18, 20, 14, 16, 56, 89
        }, 0, 3, 8);
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
