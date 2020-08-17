package org.example;

import java.util.Arrays;

public class QuickSortExample2 {

    public static void main(String[] args) {

        int[] arr = {6, 5, 2, 7, 3, 9, 8, 4, 10, 1};
        quickSort(arr, 0, arr.length - 1);
    }

    static void quickSort(int[] arr, int left, int right) {
        if (left < right) {

            int pi = partition(arr, left, right);
            System.out.println("arr = " + Arrays.toString(arr));
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);

        }
    }

    static int partition(int[] arr, int left, int right) {
        int base = arr[left];

        System.out.println("Begin Arrays.toString(arr) = " + Arrays.toString(arr));

        int i = left, j = right;
        for (; i < j; ) {
            // 问题出在 需要先从右边出发

            while (i < j && arr[j] >= base) {
                j--;
            }

            while (i < j && arr[i] <= base) {
                i++;
            }

            swap(arr, i, j);
        }

        swap(arr, left, i);
        System.out.println("End Arrays.toString(arr) = " + Arrays.toString(arr));
        System.out.println("i = " + i);
        return i;
    }

    public static int partition_2(int[] n, int l, int r) {
        // p为基数，即待排序数组的第一个数
        int p = n[l];
        int i = l;
        int j = r;
        while (i < j) {
            // 从右往左找第一个小于基数的数
            while (n[j] >= p && i < j) {
                j--;
            }
            // 从左往右找第一个大于基数的数
            while (n[i] <= p && i < j) {
                i++;
            }
            // 找到后交换两个数
            swap(n, i, j);
        }
        // 使划分好的数分布在基数两侧
        swap(n, l, i);
        return i;
    }

    public static int partition_3(int[] arr, int left, int right) {
        int pivot = left;
        int index = left + 1;

        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }

        swap(arr, pivot, index - 1);
        return index - 1;
    }



    private static void swap(int[] n, int i, int j) {
        if (i == j) return;

        int temp = n[i];
        n[i] = n[j];
        n[j] = temp;
    }

}
