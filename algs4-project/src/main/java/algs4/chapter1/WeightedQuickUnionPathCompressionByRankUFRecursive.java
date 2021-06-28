package algs4.chapter1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author xmchx on 2020/3/27 9:37
 */
public class WeightedQuickUnionPathCompressionByRankUFRecursive {
    private int[] parent; // parent[i]表示第i个元素所指向的父节点
    private int[] rank;  // rank[i]表示以i为根的集合所表示的树的层数
    private int count; // 数据分量个数

    public WeightedQuickUnionPathCompressionByRankUFRecursive(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }


    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        // 将元素高度低的集合合并到元素高度高的集合上
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootP] = rootQ;
            rank[rootQ] += 1;
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
        // path compression 递归
        if (p != parent[p])
            parent[p] = find(parent[p]);
        return parent[p];
    }

    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) throw new IllegalArgumentException("index " + p +
                " is not between 0 and " + (n - 1));
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionPathCompressionByRankUFRecursive uf = new WeightedQuickUnionPathCompressionByRankUFRecursive(N);
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
