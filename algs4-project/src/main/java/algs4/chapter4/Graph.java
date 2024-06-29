package algs4.chapter4;

import algs4.chapter1.Bag;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Graph {
	private final int V; // 顶点数目
	private int E; // 边的数目
	private Bag<Integer>[] adj; // 邻接表

	public Graph(int V) {
		this.V = V;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<>();
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}
