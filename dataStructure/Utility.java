/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import chap05_Bit_Manipulation.chap05_Quest2;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Utility {

    /**
     * <pre>
     * {@code
     * Convert a 2D grid to 1D
     * concept [1,1] [1,2] -> [1,1] [2,2] [2,1] [2,2]
     *         [2,1] [2,2]
     * meaning [1] [2] [3] [4]
     * so say row * length_of_row + column
     *             or
     *        1 * 2 + 1 = 3
     * but 1,1 should be 0
     * so say row-1 * length_of_row + column-1
     *         (1-1)*2+(1-1)=0
     * likewise
     *         (2-1)*2+(2-1)=3
     * check
     * [1,1] [2,2] [2,1] [2,2]
     * [ 0 ] [ 1 ] [ 2 ] [ 3 ]
     * }
     * </pre>
     *
     * @param row
     * @param column
     * @return
     */
    public static int gridToRow(int width, int row, int column) {
        return (width * (row - 1)) + (column - 1);
    }

    public static Pair<Integer, Integer> rowToGrid(int i, int width) {
        int row = i / width;
        int col = i % width;
        return new Pair(row, col);
    }

    public static String intToBitsLSD(int num) {
        StringBuilder str = new StringBuilder();
        while (num != 0) {
            str.append(num & 1);
            num >>= 1;
        }
        return str.reverse().toString();
    }

    public static String intToBitsMSD(int num) {
        StringBuilder str = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            byte j = (byte) ((num & (1 << i)) != 0 ? 1 : 0);
            str.append(j);
        }
        return str.toString();
    }

    public static String doubleToBits(double num) {
        int n = (int) num;
        String s = intToBitsLSD(n);
        num -= n;
        String s2 = chap05_Quest2.fracToBits(num);
        return s + s2.substring(1);
    }

    public static class Pair<A, B> {

        public A x;
        public B y;

        public Pair(A x, B y) {
            this.x = x;
            this.y = y;
        }
    }
}
