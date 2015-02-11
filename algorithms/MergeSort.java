/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coursera.sorting.Integer;

import com.coursera.sorting.Sorter;

/**
 *
 * @author kyle
 */
public class MergeSort implements Sorter<Integer> {

    @Override
    public void sort(Integer[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] t = new int[arr.length];
        sort(arr, t, 0, arr.length - 1);
    }

    private static void sort(Integer[] arr, int[] t, int low, int high) {
        if (high <= low) {
            return;
        }
        //improvement one: Insertion sort is faster for smaller arrays consider using it to alleviate overhead
        int mid = low + (high - low) / 2;
        sort(arr, t, low, mid);
        sort(arr, t, mid + 1, high);
        //imporovement two: if mid is less than or equal to mid+1 means its already sorted so final merge not needed
        //merge(arr, t, low, mid, high);
        for (int i = low; i <= high; i++) {
            t[i] = arr[i];
        }
        for (int left = low,
                right = mid + 1,
                i = low; i <= high; i++) {
            if (left > mid) {
                //if left side past limit empty right side
                arr[i] = t[right++];
            } else if (right > high) {
                //if right side past limit empty right side
                arr[i] = t[left++];
            } else if (t[left] <= t[right]) {
                arr[i] = t[left++];
            } else {
                arr[i] = t[right++];
            }
        }

    }
}
