/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

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

    public static class Pair<A, B> {

        public A x;
        public B y;

        public Pair(A x, B y) {
            this.x = x;
            this.y = y;
        }
    }
}
