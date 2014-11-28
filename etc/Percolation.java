/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etc;

import dataStructure.UnionFind;

/**
 *
 * @author Kyle D. Williams <kyledgwilliams@gmail.com>
 */
public class Percolation {

    private final int N;
    private final UnionFind uf;
    private final boolean[] openedPath;
    private final boolean[] isFull;
    private final int top;
    private boolean percolates;

    /**
     * create N-by-N grid, with all sites blocked
     *
     * total gridsize should be N*N+2 to accomodate the imaginary bottom and top
     *
     * @param N
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        this.N = N;
        this.percolates = false;
        //need extra room for top and bottom making top 0 and bottom N*N causes issues
        int gridSize = N * N;
        //for the top and the bottom
        this.uf = new UnionFind(gridSize + 1);
        //Create imaginary top and bottom and connect corresponding cells
        this.top = gridSize;

        openedPath = new boolean[gridSize + 1];
        isFull = new boolean[gridSize + 1];
        //default for boolean is already false only check top and bottom
        openedPath[top] = true;
        isFull[top] = true;
    }

    /**
     * open site (row i, column j) if it is not already
     *
     * @param i
     * @param j
     */
    public void open(int i, int j) {
        //Bounds check in isopen
        if (isOpen(i, j)) {
            return;
        }
        int coord = gridToRow(i, j);
        this.openedPath[coord] = true;

        // top
        if (i > 1 && isOpen(i - 1, j)) {
            int cellTop = gridToRow(i - 1, j);
            uf.union(coord, cellTop);
            //instead of automatically connected top row to virtual Top connect dynamically
        } else if (i == 1) {
            uf.union(top, coord);
        }

        // bottom
        if (i < N && isOpen(i + 1, j)) {
            int cellBottom = gridToRow(i + 1, j);
            uf.union(coord, cellBottom);
        }

        // left
        if (j > 1 && isOpen(i, j - 1)) {
            int cellLeft = gridToRow(i, j - 1);
            uf.union(coord, cellLeft);
        }
        // right
        if (j < N && isOpen(i, j + 1)) {
            int cellRight = gridToRow(i, j + 1);
            uf.union(coord, cellRight);
        }

    }

    /**
     * is site (row i, column j) open?
     *
     * @param i
     * @param j
     * @return
     */
    public boolean isOpen(int i, int j) {
        checkBounds(i, j);
        return openedPath[gridToRow(i, j)];
    }

    /**
     * is site (row i, column j) full?
     *
     * Full means this cell is connected to the top
     */
    public boolean isFull(int i, int j) {
        checkBounds(i, j);
        int index = gridToRow(i, j);
        //try dynamic allocation
        if (!isFull[index]) {
            isFull[index] = isOpen(i, j) && uf.connected(top, index);
        }
        return isFull[index];
    }

    /**
     * does the system percolate?
     *
     * @return
     */
    public boolean percolates() {
        //By manually checking if the bottom sites are open at top
        //backwash can be avoided
        if (!percolates) {
            for (int i = 1; i <= N; i++) {
                if (isFull(N, i)) {
                    percolates = true;
                }
            }
        }
        return percolates;
    }

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
    private int gridToRow(int row, int column) {
        checkBounds(row, column);
        return (N * (row - 1)) + (column - 1);
    }

    /**
     * are the indicies out of range?
     *
     * By convention, the indices i and j are integers between 1 and N, where
     * (1, 1) is the upper-left site
     *
     * @throws IndexOutOfBoundsException
     */
    private void checkBounds(int i, int j) {
        if (i < 1 || i > N) {
            throw new IndexOutOfBoundsException("I[1 > " + i + " > " + N + "]");
        } else if (j < 1 || j > N) {
            throw new IndexOutOfBoundsException("J[1 > " + j + " > " + N + "]");
        }
    }
}
