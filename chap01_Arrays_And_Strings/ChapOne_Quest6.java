/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chap01_Arrays_And_Strings;

/**
 * Given an image represented by an NxN matrix, where each pixel in the image is
 * 4 bytes, write, a method to rotate the image by 90 degrees.
 *
 * Can you do this in place?
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class ChapOne_Quest6 {

    public static void transpose(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = i; j < image[i].length; j++) {
                int temp = image[i][j];
                image[i][j] = image[j][i];
                image[j][i] = temp;
            }
        }
    }

    public static void swapCol(int[][] image) {
        int N = image.length;
        for (int j = 0; j < N / 2; j++) {
            for (int i = 0; i < N; i++) {
                int k = N - 1;
                int temp = image[i][j];
                image[i][j] = image[i][k - j];
                image[i][k - j] = temp;
            }
        }
    }

    public static void swapRow(int[][] image) {
        int N = image.length;
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N; j++) {
                int k = N - 1;
                int temp = image[i][j];
                image[i][j] = image[k - i][j];
                image[k - i][j] = temp;
            }
        }
    }

    /**
     * What This will do is basic linear Rotation Transpose then swap column to
     * turn clockwise or swap row to turn counterclockwise
     *
     * @param image
     * @param i if greater than 0 rotate right else rotate left
     */
    public static void rotate(int[][] image, int i) {
        if (i == 0) {
            swapRow(image);
            swapCol(image);
            return;
        }
        transpose(image);
        if (i > 0) {//Clockwise
            swapCol(image);
        } else {//Counter-Clockwise
            swapRow(image);
        }
    }

    public static void printImage(int[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.printf("%2d ", image[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //It says 4 bytes = 32 bits use Java int represent 4 byte pixel
        //use array easy to visual interpret
        int[][] image = {
            {1, 2, 3, 4, 5},
            {6, 7, 8, 9, 1},
            {2, 3, 4, 5, 6},
            {7, 8, 9, 1, 2},
            {3, 4, 5, 6, 7},};
        System.out.println("Original Image");
        printImage(image);
        System.out.println("Image Rotated Clockwise");
        rotate(image, 1);
        printImage(image);
        System.out.println("Image Rotated Counter-Clockwise = Original Image");
        rotate(image, -1);
        printImage(image);
        System.out.println("Image Rotated 180");
        rotate(image, 0);
        printImage(image);
        System.out.println("Rotated Clockwise x2");
        rotate(image, 1);
        rotate(image, 1);
        printImage(image);
        System.out.println("Transpose");
        transpose(image);
        printImage(image);

    }
}
