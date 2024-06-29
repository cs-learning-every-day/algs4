package algs4.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class QuickX {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;

        if (hi - lo + 1 < 8) {
            insertionSort(a, lo, hi);
            return;
        }
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }


    private static int partition(Comparable[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int m = median3(a, lo, lo + n / 2, hi);
        exch(a, m, lo);

        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];

        // a[lo] is unique largest element
        while (less(a[++i], v)) {
            if (i == hi) {
                exch(a, lo, hi);
                return hi;
            }
        }
        // a[lo] is unique smallest element
        while (less(v, a[--j])) {
            if (j == lo + 1) {
                return lo;
            }
        }
        while (i < j) {
            exch(a, i, j);
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
        }
        exch(a, lo, j);
        // a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            Comparable e = a[i];
            int j = i;
            for (; j > lo && less(e, a[j - 1]); j--)
                a[j] = a[j - 1];
            a[j] = e;
        }
    }

    // return the index of the median element among a[i], a[j] and a[k]
    private static int median3(Comparable[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        show(a);
    }


}
