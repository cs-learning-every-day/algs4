package algs4.chapter4;


/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Topological {
	private Iterable<Integer> order;

	public Topological(Digraph g) {
		DirectedCycle cyclefinder = new DirectedCycle(g);

		if (!cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(g);

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
