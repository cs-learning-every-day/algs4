package algs4.chapter1;

import java.util.Scanner;

/**
 * @author xmchx on 2020/3/21 13:08
 */
public class BinarySearch {
    public static void printFunctionDetail(int lo, int hi, int deep) {
        for (int i = 0; i < deep; i++) {
            System.out.print(" ");
        }
        System.out.printf("rankRecursion(key, a, %d, %d, %d)\n", lo, hi, deep);
    }

    public static int rankRecursion(int key, int[] a, int lo, int hi, int deep) {
        printFunctionDetail(lo, hi, deep);
        if (lo > hi) return -1;
        int mid = (hi - lo) / 2 + lo;

        if (key < a[mid]) return rankRecursion(key, a, lo, mid - 1, deep + 1);
        else if (key > a[mid]) return rankRecursion(key, a, mid + 1, hi, deep + 1);
        else return mid;
    }

    public static int rank(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            if (a[mid] < key) {
                lo = mid + 1;
            } else if (a[mid] > key) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int gcd(int p, int q) {
        if (p % q == 0) return q;
        int c = p % q;
        return gcd(q, c);
    }

    public static void main(String[] args) {
        int N = 20;
        int[] whitelist = new int[N];
        for (int i = 0; i < N; i++) {
            whitelist[i] = (int) (Math.random() * 10);
        }
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int key = sc.nextInt();
            if (rankRecursion(key, whitelist, 0, N - 1, 0) < 0)
                System.out.println(key);
        }
    }
}
