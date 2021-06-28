/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int gridSize;
    private int openSites;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF wqfGrid;
    private WeightedQuickUnionUF wqfFull;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        grid = new boolean[n][n];
        gridSize = n;
        int gridSquared = n * n;
        wqfGrid = new WeightedQuickUnionUF(gridSquared + 2); // includes virtual top bottom
        wqfFull = new WeightedQuickUnionUF(gridSquared + 1); // includes virtual top
        virtualTop = gridSquared;
        virtualBottom = gridSquared + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) return;

        int flatIndex = xyTo1D(row, col);

        // open site
        grid[row][col] = true;
        openSites++;

        if (row == 0) { // Top Row
            wqfGrid.union(virtualTop, flatIndex);
            wqfFull.union(virtualTop, flatIndex);
        }

        if (row == gridSize - 1) { // Bottom Row
            wqfGrid.union(virtualBottom, flatIndex);
        }

        // Check and Open Up
        if (isValid(row - 1, col) && isOpen(row - 1, col)) {
            wqfGrid.union(flatIndex, xyTo1D(row - 1, col));
            wqfFull.union(flatIndex, xyTo1D(row - 1, col));
        }
        // Check and Open Down
        if (isValid(row + 1, col) && isOpen(row + 1, col)) {
            wqfGrid.union(flatIndex, xyTo1D(row + 1, col));
            wqfFull.union(flatIndex, xyTo1D(row + 1, col));
        }
        // Check and Open Left
        if (isValid(row, col - 1) && isOpen(row, col - 1)) {
            wqfGrid.union(flatIndex, xyTo1D(row, col - 1));
            wqfFull.union(flatIndex, xyTo1D(row, col - 1));
        }
        // Check and Open Right
        if (isValid(row, col + 1) && isOpen(row, col + 1)) {
            wqfGrid.union(flatIndex, xyTo1D(row, col + 1));
            wqfFull.union(flatIndex, xyTo1D(row, col + 1));
        }


        if (percolates()) {
            // backwash
            if (isFull(row, col)) {
                fixBackwash();
            }
        }
    }

    private void fixBackwash() {
        int bottomRowIndex = gridSize * (gridSize - 1);

        int row = gridSize - 1;
        for (int col = 0; col < gridSize; col++) {
            if (isOpen(row, col)) {
                backwash(row, col);
            }
        }
    }

    private void backwash(int row, int col) {
        if (!isValid(row, col)) return;
        if (wqfGrid.connected(virtualBottom, xyTo1D(row, col))) {
            wqfFull.union(virtualTop, xyTo1D(row, col));
            return;
        }
        backwash(row - 1, col);
        backwash(row + 1, col);
        backwash(row, col - 1);
        backwash(row, col + 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return wqfFull.connected(virtualTop, xyTo1D(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wqfGrid.connected(virtualTop, virtualBottom);
    }

    private void validate(int row, int col) {
        if (!isValid(row, col)) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
    }

    private boolean isValid(int row, int col) {
        return (row >= 0 && row < gridSize &&
                col >= 0 && col < gridSize);
    }

    /**
     * row,col是从0开始
     *
     * @param row
     * @param col
     * @return
     */
    private int xyTo1D(int row, int col) {
        return row * gridSize + col;
    }

    // unit testing (required)
    public static void main(String[] args) {
        if (args.length < 1)
            throw new IllegalArgumentException("请输入文件名！");

        In in = new In(args[0]);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);

        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            StdOut.printf("Adding row: %d  col: %d %n", row, col);
            percolation.open(row, col);
            if (percolation.percolates()) {
                StdOut.printf("%nThe System percolates %n");
            }
        }
        if (!percolation.percolates()) {
            StdOut.print("Does not percolate %n");
        }
    }
}
