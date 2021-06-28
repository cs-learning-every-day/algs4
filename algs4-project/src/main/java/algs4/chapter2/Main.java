package algs4.chapter2;

import java.util.Arrays;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Main {
    public static void main(String[] args) {
        int N = 1000000;
        // 高级排序
        Integer[] array = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] array1 = Arrays.copyOf(array, N);
        Integer[] array2 = Arrays.copyOf(array, N);
        Integer[] array3 = Arrays.copyOf(array, N);
        Integer[] array4 = Arrays.copyOf(array, N);
        Integer[] array5 = Arrays.copyOf(array, N);
        Integer[] array6 = Arrays.copyOf(array, N);
        Integer[] array7 = Arrays.copyOf(array, N);
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");
        SortTestHelper.testSort("algs4.chapter2.Merge", array);
        SortTestHelper.testSort("algs4.chapter2.MergeX", array1);
        SortTestHelper.testSort("algs4.chapter2.MergeBU", array2);
        SortTestHelper.testSort("algs4.chapter2.MergeBUX", array3);
        SortTestHelper.testSort("algs4.chapter2.Quick", array4);
        SortTestHelper.testSort("algs4.chapter2.QuickX", array5);
        SortTestHelper.testSort("algs4.chapter2.Quick3way", array6);
        SortTestHelper.testSort("algs4.chapter2.Heap", array7);

/*        // 测试2 测试近乎有序的数组
        int swapTimes = 100;
        assert swapTimes >= 0;
        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);
        array = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        array1 = Arrays.copyOf(array, N);
        array2 = Arrays.copyOf(array, N);
        array3 = Arrays.copyOf(array, N);
        array4 = Arrays.copyOf(array, N);
        array5 = Arrays.copyOf(array, N);
        array6 = Arrays.copyOf(array, N);
        SortTestHelper.testSort("algs4.chapter2.Merge", array);
        SortTestHelper.testSort("algs4.chapter2.MergeX", array1);
        SortTestHelper.testSort("algs4.chapter2.MergeBU", array2);
        SortTestHelper.testSort("algs4.chapter2.MergeBUX", array3);
        SortTestHelper.testSort("algs4.chapter2.Quick", array4);
        SortTestHelper.testSort("algs4.chapter2.QuickX", array5);
        SortTestHelper.testSort("algs4.chapter2.Quick3way", array6);

        // 测试3 测试存在包含大量相同元素的数组
        System.out.println("Test for random array, size = " + N + " , random range [0,10]");
        array = SortTestHelper.generateRandomArray(N, 0, 10);
        array1 = Arrays.copyOf(array, N);
        array2 = Arrays.copyOf(array, N);
        array3 = Arrays.copyOf(array, N);
        array4 = Arrays.copyOf(array, N);
        array5 = Arrays.copyOf(array, N);
        array6 = Arrays.copyOf(array, N);
        SortTestHelper.testSort("algs4.chapter2.Merge", array);
        SortTestHelper.testSort("algs4.chapter2.MergeX", array1);
        SortTestHelper.testSort("algs4.chapter2.MergeBU", array2);
        SortTestHelper.testSort("algs4.chapter2.MergeBUX", array3);
        SortTestHelper.testSort("algs4.chapter2.Quick", array4);
        SortTestHelper.testSort("algs4.chapter2.QuickX", array5);
        SortTestHelper.testSort("algs4.chapter2.Quick3way", array6);*/


        // 低级排序
 /*       // 测试1 一般性测试
        System.out.println("Test for random array, size = " + N + " , random range [0, " + N + "]");
        Integer[] array = SortTestHelper.generateRandomArray(N, 0, N);
        Integer[] array1 = Arrays.copyOf(array, N);
        Integer[] array2 = Arrays.copyOf(array, N);
        Integer[] array3 = Arrays.copyOf(array, N);
        Integer[] array4 = Arrays.copyOf(array, N);
        SortTestHelper.testSort("algs4.chapter2.Selection", array);
        SortTestHelper.testSort("algs4.chapter2.Insertion", array1);
        SortTestHelper.testSort("algs4.chapter2.InsertionX", array2);
        SortTestHelper.testSort("algs4.chapter2.InsertionXX", array3);
        SortTestHelper.testSort("algs4.chapter2.Shell", array4);

        // 测试2 测试近乎有序的数组
        int swapTimes = 100;
        assert swapTimes >= 0;
        System.out.println("Test for nearly ordered array, size = " + N + " , swap time = " + swapTimes);
        array = SortTestHelper.generateNearlyOrderedArray(N, swapTimes);
        array1 = Arrays.copyOf(array, N);
        array2 = Arrays.copyOf(array, N);
        array3 = Arrays.copyOf(array, N);
        array4 = Arrays.copyOf(array, N);
        SortTestHelper.testSort("algs4.chapter2.Selection", array);
        SortTestHelper.testSort("algs4.chapter2.Insertion", array1);
        SortTestHelper.testSort("algs4.chapter2.InsertionX", array2);
        SortTestHelper.testSort("algs4.chapter2.InsertionXX", array3);
        SortTestHelper.testSort("algs4.chapter2.Shell", array4);

        // 测试3 测试存在包含大量相同元素的数组
        System.out.println("Test for random array, size = " + N + " , random range [0,10]");
        array = SortTestHelper.generateRandomArray(N, 0, 10);
        array1 = Arrays.copyOf(array, N);
        array2 = Arrays.copyOf(array, N);
        array3 = Arrays.copyOf(array, N);
        array4 = Arrays.copyOf(array, N);
        SortTestHelper.testSort("algs4.chapter2.Selection", array);
        SortTestHelper.testSort("algs4.chapter2.Insertion", array1);
        SortTestHelper.testSort("algs4.chapter2.InsertionX", array2);
        SortTestHelper.testSort("algs4.chapter2.InsertionXX", array3);
        SortTestHelper.testSort("algs4.chapter2.Shell", array4);*/
    }
}
