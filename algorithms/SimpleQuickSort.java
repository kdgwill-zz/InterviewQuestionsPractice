/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coursera.sorting.Integer;

import com.coursera.sorting.Sorter;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author kyle
 */
public class SimpleQuickSort implements Sorter<Integer> {

    @Override
    public void sort(Integer[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        //Shuffling may prevents a strenous list of possible issues
        //Collections.shuffle(Arrays.asList(arr));
        
        sort(arr, 0, arr.length - 1);
    }

    private void sort(Integer[] arr, int low, int high) {
        int left = low;
        int right = high;
        int pivot = arr[low + (high - low) / 2];
        while (left <= right) {
            while (arr[left] < pivot) {
                left++;
            }
            while (arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }
        if (low < right) { //By impl right will now be 1 less than pivot
            sort(arr, low, right);//above swap decrement shifts into correct place
        }
        if (left < high) { //By impl right will now be 1 more than pivot
            sort(arr, left, high);//above swap increment shifts into correct place
        }
    }
}
