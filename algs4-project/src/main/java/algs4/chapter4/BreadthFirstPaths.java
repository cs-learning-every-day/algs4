package algs4.chapter4;

import algs4.chapter1.Queue;
import algs4.chapter1.Stack;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s; // 起点

	public BreadthFirstPaths(Graph G, int s) {
		this.s = s;
		this.marked = new boolean[G.V()];
		this.edgeTo = new int[G.V()];
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<>();
		marked[s] = true;
		queue.enqueue(s);
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
			}
		}
	}


	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<Integer> stack = new Stack<>();
		for (int x = v; x != s; x = edgeTo[x]) {
			stack.push(x);
		}
		stack.push(s);
		return stack;
	}
}
