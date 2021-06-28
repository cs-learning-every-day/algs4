package algs4.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class MergeBU {
    private MergeBU() {

    }

    private static void merge(Comparable[] a,
                              int lo, int mid, int hi) {
        Comparable[] aux = Arrays.copyOfRange(a, lo, hi + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j - lo];
                j++;
            } else if (j > hi) {
                a[k] = aux[i - lo];
                i++;
            } else if (less(aux[j - lo], aux[i - lo])) {
                a[k] = aux[j - lo];
                j++;
            } else {
                a[k] = aux[i - lo];
                i++;
            }
        }
    }


    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int sz = 1; sz < n; sz += sz) {
            for (int lo = 0; lo < n - sz; lo += sz + sz) {
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
            }
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

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
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
