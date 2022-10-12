package com.algorithm.demo;

import com.algorithm.demo.algorithms.*;
//import com.algorithm.demo.examples.LargestSumContiguousSubarray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.zip.CRC32;

@SpringBootTest
public class AlgorithmTests {

    @Test
    void bubble_buffer()
    {
        try {
            Enumeration<NetworkInterface> list = NetworkInterface.getNetworkInterfaces();
            while(list.hasMoreElements())
            {
                NetworkInterface i = list.nextElement();
                System.out.println(i.getMTU());
                System.out.println(i.getDisplayName());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        //StringBuffer a = new StringBuffer();

        java.nio.
        ByteBuffer.allocate(100);
    }


    @Test
    void bubble_sort()
    {
        //Integer[] integers = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        Integer[] integers = {4, 23, 22, 21, 20, 19, 231, 9, 12};
        Integer[] sorts = SortAlgorithm.bubble_sort(integers);
        SortUtils.print(sorts);
    }

    @ Test
    void quick_sort()
    {
        Integer[] array = {3, 4, 1, 32, 0, 1, 5, 12, 2, 5, 7, 8, 9, 2, 44, 111, 5};
        SortUtils.print(QuickSortAlgorithm.sort(array));
    }

    @ Test
    void selection_sort()
    {
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SortUtils.print(SortAlgorithm.selection_sort(arr));
    }

    @ Test
    void insertion_sort()
    {
        ByteCodeTest.InitAndAdd();
        Integer[] arr = {4, 23, 6, 78, 1, 54, 231, 9, 12};
        SortUtils.print(SortAlgorithm.insertion_sort(arr));
    }

    @ Test
    void palindrome_find_max()
    {
        //boolean isornot = PalindromeAlgorithm.is_or_not_palindrome("daad");
        PalindromeAlgorithm.find_all_palindrome_string2("ahklbjblkldda");

        //String text = "abbd";
        //String text = "abba";
        //String text = "ahklbjblklddadppoaaka2aagahagaa1agaa";
        String text = "ahklbjblkldda";
        String max = PalindromeAlgorithm.find_max_string_1(text);
        System.out.println(max);

    }


    @Test
    int gcd(int p, int q)
    {
        if(q==0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    @Test
    public void gcd123()
    {
        float arr[] = { (float)0.897, (float)0.565,
                (float)0.656, (float)0.1234,
                (float)0.665, (float)0.3434 };

        int n = arr.length;
        BucketSort.bucketSort(arr, n);

        System.out.println("Sorted array is ");
        for (float el : arr) {
            System.out.print(el + " ");
        }

    }


    @Test
    public void gcd1234()
    {
        //java.util.concurrent.Semaphore
        java.util.concurrent.CompletableFuture a = new CompletableFuture();

//        Deadlock deadlock = new Deadlock();
//        deadlock.test();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void maxgcd1234()
    {
        int anInt1 = 123_45_6;

//        Integer[] a = {1, 2, 3, 4, -9, 9, 2};
//        LargestSumContiguousSubarray l = new LargestSumContiguousSubarray(a);
//        l.FindSubarrayBy();
        //l.FindSubarrayByViolence();
    }

}

