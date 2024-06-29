package algs4.chapter2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Merge {
    private Merge() {

    }

    private static void merge(Comparable[] a, Comparable[] aux,
                              int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. lo] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        // merge back to a[]
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
    }

    private static void sort(Comparable[] a, Comparable[] aux,
                             int lo, int hi) {
        if (hi <= lo) return;
        int mid = (hi - lo) / 2 + lo;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] arr) {
        int n = arr.length;
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, n - 1);
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
        String[] aux = new String[a.length];
        sort(a, aux, 0, a.length - 1);
        show(a);
    }


}
