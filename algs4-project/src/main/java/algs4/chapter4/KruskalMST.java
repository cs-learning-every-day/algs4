package algs4.chapter4;


import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class KruskalMST {
	private Queue<Edge> mst;

	public KruskalMST(EdgeWeightedGraph G) {
		mst = new Queue<>();

		Edge[] edges = new Edge[G.E()];
		int i = 0;
		for (Edge edge : G.edges()) {
			edges[i++] = edge;
		}

		MinPQ<Edge> pq = new MinPQ<>(edges);
		UF uf = new UF(G.V());

		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge edge = pq.delMin();

			int v = edge.either(), w = edge.other(v);
			if (uf.connected(v, w)) continue;

			uf.union(v, w);
			mst.enqueue(edge);
		}
	}

	public Iterable<Edge> edges() {
		return mst;
	}
}
