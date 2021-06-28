package algs4.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 希尔排序
 *
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Shell {
    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            // h-sort the array (use insertion sort)
            for (int i = h; i < n; i++) {
                int j = i;
                Comparable e = a[i];
                for (; j >= h && less(e, a[j - h]); j -= h) {
                    a[j] = a[j - h];
                }
                a[j] = e;
            }
            h /= 3;
        }
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
