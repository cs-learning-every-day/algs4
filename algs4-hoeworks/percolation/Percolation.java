/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final static int[] dx = { 0, 1, -1, 0 }, dy = { 1, 0, 0, -1 };
    // head与tail用来判断是否percolate
    private final int head;
    private final int tail;

    private int n;
    private int openSize;
    private boolean[] states;

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF fullUf;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be great than 0");

        this.n = n;
        openSize = 0;
        head = 0;
        tail = n * n + 1;
        states = new boolean[n * n + 2];

        uf = new WeightedQuickUnionUF(n * n + 2);
        fullUf = new WeightedQuickUnionUF(n * n + 2);
        // 第一行的元素与头部相连 表示第一行所有都可以full
        int idx;
        for (int col = 1; col <= n; col++) {
            idx = calculateIdx(1, col);
            fullUf.union(head, idx);
            uf.union(head, idx);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkIdx(row, col);

        if (!isOpen(row, col)) {
            int idx = calculateIdx(row, col);
            states[idx] = true;
            openSize++;

            // 将最后一行的元素与tail相连
            if (row == n) {
                uf.union(tail, idx);
            }
        }

        renderForNeighbours(row, col);
    }

    private void renderForNeighbours(int row, int col) {
        int newRow, newCol, newIdx, originIdx = calculateIdx(row, col);
        for (int i = 0; i < 4; i++) {
            newRow = dx[i] + row;
            newCol = dy[i] + col;
            if (!validateIdx(newRow, newCol))
                continue;
            if (isOpen(newRow, newCol)) {
                newIdx = calculateIdx(newRow, newCol);
                fullUf.union(originIdx, newIdx);
                uf.union(originIdx, newIdx);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkIdx(row, col);
        return states[calculateIdx(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkIdx(row, col);
        return isOpen(row, col) &&
                fullUf.find(head) == fullUf.find(calculateIdx(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(head) == uf.find(tail);
    }

    private boolean validateIdx(int row, int col) {
        if (row < 1 || col < 1 || row > n || col > n) return false;
        return true;
    }

    private void checkIdx(int row, int col) {
        if (!validateIdx(row, col))
            throw new IllegalArgumentException("idx out of range");
    }

    /**
     * 多加两个一个开头一个结尾  默认为相连 所以idx需要+1
     * 此处row和col都是从一开始转换时需要-1
     */
    private int calculateIdx(int row, int col) {
        return ((row - 1) * n + (col - 1)) + 1;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 2);
        p.open(2, 2);
        p.open(1, 3);
        p.open(3, 3);
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());

        p.open(3, 2);
        StdOut.println(p.percolates());
        StdOut.println(p.numberOfOpenSites());
    }
}
