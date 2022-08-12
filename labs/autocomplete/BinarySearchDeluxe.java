import java.util.Comparator;

public class BinarySearchDeluxe {
    // Returns the index of the first key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (comparator.compare(key, a[mid]) > 0) { // key > a[mid]
                lo = mid + 1;
            }
            else { // key <= a[mid]
                hi = mid;
            }
        }

        if (comparator.compare(key, a[lo]) == 0) {
            return lo;
        }
        return -1;
    }

    // Returns the index of the last key in the sorted array a[]
    // that is equal to the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException();
        }
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int mid = (lo + hi + 1) >> 1;
            if (comparator.compare(key, a[mid]) < 0) { // key < a[mid]
                hi = mid - 1;
            }
            else { // key >= a[mid]
                lo = mid;
            }
        }
        if (comparator.compare(key, a[lo]) == 0) {
            return lo;
        }
        return -1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        var items = new Integer[] { 1, 2, 3, 4, 5, 6, 6, 7, 7, 7, 7, 8 };
        System.out.printf("4 = %d\n", firstIndexOf(items, 5, Integer::compareTo));
        System.out.printf("4 = %d\n", lastIndexOf(items, 5, Integer::compareTo));

        System.out.printf("5 = %d\n", firstIndexOf(items, 6, Integer::compareTo));
        System.out.printf("6 = %d\n", lastIndexOf(items, 6, Integer::compareTo));

        System.out.printf("7 = %d\n", firstIndexOf(items, 7, Integer::compareTo));
        System.out.printf("10 = %d\n", lastIndexOf(items, 7, Integer::compareTo));


    }
}
