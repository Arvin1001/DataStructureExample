package org.example;

import java.util.Arrays;

/**
 * @author Administrator
 */
public class InsertionSortExample {

    public static void main(String[] args) {
        int[] arr = {8, 7, 10, -2, 18};
        insertSort(arr);

        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        if (arr.length <= 2) {
            return;
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];

            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

    }
}
