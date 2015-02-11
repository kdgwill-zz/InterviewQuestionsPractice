package com.coursera.sorting.Integer;

import com.coursera.sorting.Sorter;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * This QuickSort Only Makes sense if their are a large amount of duplicate items
 * 
 * @author kyle
 */
public class StableQuickSort implements Sorter<Integer> {

    @Override
    public void sort(Integer[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Integer[] arr, int low, int high) {
        if (high <= low) {
            return;
        }
        
        int left = low;
        int right = high;
        int i = left + 1;
        while (i <= right) {
            if (arr[i] < arr[left]) {
                swap(arr, left, i);
                left++;
                i++;
            } else if (arr[i] > arr[left]) {
                swap(arr, i, right);
                right--;
            } else {
                i++; //this is what fixes quadratic and makes function stable
            }
        }
        sort(arr, low, left - 1);
        sort(arr, right + 1, high);
    }

    private static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
