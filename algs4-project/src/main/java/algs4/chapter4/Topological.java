package algs4.chapter4;


import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.DepthFirstOrder;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Topological {
	private Iterable<Integer> order;

	public Topological(Digraph g) {
		DirectedCycle cyclefinder = new DirectedCycle(g);

		if (!cyclefinder.hasCycle()) {
			algs4.chapter4.DepthFirstOrder dfs = new algs4.chapter4.DepthFirstOrder(g);

			order = dfs.reversePost();
		}
	}

	public Topological(EdgeWeightedDigraph G) {
		EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
		if (!finder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}

	public Iterable<Integer> order() {
		return order;
	}

	public boolean isDAG() {
		return order != null;
	}

}
