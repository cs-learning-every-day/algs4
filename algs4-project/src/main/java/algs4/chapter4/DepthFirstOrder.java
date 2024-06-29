package algs4.chapter4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> pre; // 前序排列
	private Queue<Integer> post; // 后序排列
	private Stack<Integer> reversePost; // 后序排列

	public DepthFirstOrder(Digraph g) {
		pre = new LinkedList<>();
		post = new LinkedList<>();
		reversePost = new Stack<>();
		marked = new boolean[g.V()];

		for (int v = 0; v < g.V(); v++) {
			if (!marked[v]) dfs(g, v);
		}
	}

	private void dfs(Digraph g, int v) {
		pre.offer(v);

		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w])
				dfs(g, w);
		}
		post.offer(v);
		reversePost.push(v);
	}

	public Iterable<Integer> pre() {
		return pre;
	}

	public Iterable<Integer> post() {
		return post;
	}

	public Iterable<Integer> reversePost() {
		return reversePost;
	}
}
