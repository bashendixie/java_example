package com.algorithm.demo.algorithms;

import static com.algorithm.demo.algorithms.SortUtils.*;

/**
 * 排序算法
 */
public class SortAlgorithm {

    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public static <T extends Comparable<T>> T[] bubble_sort(T[] array) {
        for (int i = 1, size = array.length; i < size; ++i) {
            boolean swapped = false;
            for (int j = 0; j < size - i; ++j) {
                if (greater(array[j], array[j + 1])) {
                    swap(array, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return array;
    }


    /**
     * 归并排序
     * @param array
     * @return
     */
    public static int[] merge_sort(int[] array)
    {
        return null;
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static <T extends Comparable<T>> T[] insertion_sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T insertValue = array[i];
            int j;
            for (j = i - 1; j >= 0 && less(insertValue, array[j]); j--) {
                array[j + 1] = array[j];
            }
            if (j != i - 1) {
                array[j + 1] = insertValue;
            }
        }
        return array;
    }

    /**
     * 希尔排序
     * @param array
     * @return
     */
    public static int[] shell_sort(int[] array)
    {
        return null;
    }

    /**
     * 选择排序
     * @param arr
     * @return
     */

    public static <T extends Comparable<T>> T[] selection_sort(T[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex].compareTo(arr[j]) > 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
        return arr;
    }
}
