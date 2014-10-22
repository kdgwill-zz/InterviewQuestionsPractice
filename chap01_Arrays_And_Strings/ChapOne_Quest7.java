/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap01_Arrays_And_Strings;

/**
 * Write an algorithm such that if an element in an MXN matrix is 0, its enire
 * row and column are set to 0
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest7 {

    /**
     * Assuming Array is not jagged first loop through the array and find the
     * rows and columns in which their is a 0 then loop through the array a
     * second time replacing any row or column marked true with a 0
     *
     * @param array
     */
    public static void function(int[][] array) {
        boolean[] rows = new boolean[array.length];
        boolean[] cols = new boolean[array[0].length];
        //First check for 0s
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 0) {
                    rows[i] = cols[j] = true;
                }
            }
        }
        //Next set 0s
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (rows[i] || cols[j]) {
                    array[i][j] = 0;
                }
            }
        }
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("%2d ", array[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] array = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 1},
            {2, 3, 0, 5, 6},
            {7, 8, 9, 1, 2},
            {3, 4, 5, 6, 7},};
        System.out.println("Original Array");
        printArray(array);
        System.out.println("After Function:");
        function(array);
        printArray(array);
    }
}