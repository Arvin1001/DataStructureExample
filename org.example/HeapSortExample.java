package org.example;


import java.util.Arrays;

public class HeapSortExample {


    public static void main(String[] args) {
        int[] tree = {35, 33, 42, 110, 14, 19, 27, -144, 26, 31};

        heapSort(tree, false);

        System.out.println("tree = " + Arrays.toString(tree));

    }

    public static void heapSort(int[] tree, boolean isAsc) {
        buildHeap(tree, tree.length, isAsc);
        System.out.println("buildHeap tree = " + Arrays.toString(tree));

        for (int i = tree.length - 1; i >= 0; i--) {
            swap(tree, i, 0);
            heapify(tree, i, 0, isAsc);
        }
    }

    public static void buildHeap(int[] tree, int size, boolean isAsc) {
        int last = size - 1;
        int parent = (last - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(tree, size, i, isAsc);
            System.out.println("heapify tree = " + Arrays.toString(tree));
        }
    }

    public static void heapify(int[] tree, int size, int index, boolean isAsc) {
        if (index >= size) {
            return;
        }

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        int middle = index;

        if (left < size && (isAsc ? (tree[left] > tree[middle]) : (tree[left] < tree[middle]))) {
            middle = left;
        }
        if (right < size && (isAsc ? (tree[right] > tree[middle]) : (tree[right] < tree[middle]))) {
            middle = right;
        }

        if (middle != index) {
            swap(tree, middle, index);
            heapify(tree, size, middle, isAsc);
        }
    }

    private static void swap(int[] tree, int max, int index) {
        int temp = tree[max];
        tree[max] = tree[index];
        tree[index] = temp;
    }
}