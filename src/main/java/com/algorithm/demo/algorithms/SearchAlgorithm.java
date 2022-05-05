package com.algorithm.demo.algorithms;

/**
 * 查找算法
 */
public class SearchAlgorithm {

    /**
     * 二分查找
     * @param array 正序数组
     * @return
     */
    public static int binary_search(int[] array, int num)
    {
        int min = 0;
        int max = array.length-1;

        while(min <= max)
        {
            int mid = (min + max)/2;
            int cur = array[mid];
            if(cur == num)
                return mid;
            if(cur > num)
                max = mid - 1;
            else
                min = mid + 1;
        }

        return -1;
    }
}
