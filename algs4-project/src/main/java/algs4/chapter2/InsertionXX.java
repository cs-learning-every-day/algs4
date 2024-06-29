package algs4.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 插入排序
 *
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class InsertionXX {
    public static void sort(Comparable[] a) {
        int n = a.length;

        // put smallest element in position to serve as sentinel
        int exchanges = 0;
        for (int i = n - 1; i > 0; i--) {
            if (less(a[i], a[i - 1])) {
                exch(a, i, i - 1);
                exchanges++;
            }
        }
        if (exchanges == 0) return;


        for (int i = 2; i < n; i++) {
            int j = i;
            Comparable e = a[i];
            while (less(e, a[j - 1])) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = e;
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
        InsertionXX.sort(a);
        show(a);
    }


}
