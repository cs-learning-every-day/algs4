package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author xmchx on 2020/3/27 8:43
 */
public class WeightedQuickFindUF {
    private int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickFindUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }

    private int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) throw new IllegalArgumentException("index " + p +
                " is not between 0 and " + (n - 1));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickFindUF uf = new WeightedQuickFindUF(N);
        long startTime = System.currentTimeMillis();
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            //StdOut.println(p+" "+q);
        }
        long endTime = System.currentTimeMillis();

        StdOut.println((endTime - startTime) / 1000 + "s " + uf.count() + " components");
    }
}
