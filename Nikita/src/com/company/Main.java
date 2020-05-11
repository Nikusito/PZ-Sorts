package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        int size;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите длину массива: ");
        size = Integer.parseInt(reader.readLine());
        System.out.println("Введите массив: ");

        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(reader.readLine());
        }

        PrintArray("Исходный массив: ", arr);

        BubbleSort(arr);
        int[] sorted = InsertSort(arr);

        System.out.println("Введите какой элемент найти: ");
        int tofind = Integer.parseInt(reader.readLine());
        int found = Arrays.binarySearch(sorted, tofind);
        if (found < 0) {
            System.out.println("Такого элемента нет");
        } else {
            System.out.println("Индекс искомого элемента: " + found);
        }

        int[] arr2 = arr;
        QuickSort(arr2, 0, arr.length - 1);
        PrintArray("Быстрая сортировка: ", arr2);

        MergeSort(arr, 0, arr.length - 1);
        PrintArray("Сортировка слиянием: ", arr);
    }

    static void PrintArray(String message, int[] arr) {
        System.out.println("--------------------------------------------------------------");
        System.out.println(message + Arrays.toString(arr));
        System.out.println("--------------------------------------------------------------");
    }

    static void BubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        PrintArray("Сортировка пузырьком", array);
    }

    static int[] InsertSort(int[] array) {
        for (int left = 0; left < array.length; left++) {
            int value = array[left];
            int i = left - 1;
            while (i >= 0) {
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    break;
                }
                i--;
            }
            array[i + 1] = value;
        }
        PrintArray("Сортировка вставками: ", array);

        return array;
    }

    static void QuickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        int support = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < support) {
                i++;
            }

            while (array[j] > support) {
                j--;
            }

            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            QuickSort(array, low, j);

        if (high > i)
            QuickSort(array, i, high);
    }

    static void MergeSort(int[] array, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        MergeSort(array, lo, mid);
        MergeSort(array, mid + 1, hi);

        int[] buf = Arrays.copyOf(array, array.length);

        if (hi + 1 - lo >= 0)
            System.arraycopy(array, lo, buf, lo, hi + 1 - lo);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {

            if (i > mid) {
                array[k] = buf[j];
                j++;
            } else if (j > hi) {
                array[k] = buf[i];
                i++;
            } else if (buf[j] < buf[i]) {
                array[k] = buf[j];
                j++;
            } else {
                array[k] = buf[i];
                i++;
            }
        }
    }
}