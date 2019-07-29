package ua.edu.chmnu.fks.oop.recursion;

public class SimpleRecursions {

    private static long sum(int[] a, int i) {
        if (i >= a.length) {
            return 0L;
        }
        return a[i] + sum(a, i + 1);
    }

    private static long sumTail(int[] a, int i, long sum) {
        if (i >= a.length) {
            return sum;
        }
        return sumTail(a, i + 1, sum + a[i]);
    }

    public static long sum(int[] a) {
        if (a == null || a.length == 0) {
            return 0L;
        }
        return sum(a, 0);
    }

    public static long sumTail(int[] a) {
        if (a == null || a.length == 0) {
            return 0L;
        }
        return sumTail(a, 0, 0L);
    }

    private static int binarySearch(int[] a, int key, int left, int right) {
        if (left >= right) {
            return -1;
        }
        int mid = left + (right - left)/2;
        if (a[mid] == key) {
            return mid;
        }
        if (a[mid] < key) {
            return binarySearch(a, key, mid + 1, right);
        } else {
            return binarySearch(a, key, left, mid - 1);
        }
    }

    public static int binarySearch(int[] a, int key) {
        return binarySearch(a, key, 0, a.length - 1);
    }
}
