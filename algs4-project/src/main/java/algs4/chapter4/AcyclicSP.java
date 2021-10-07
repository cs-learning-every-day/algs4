package algs4.chapter4;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class AcyclicSP {
	private DirectedEdge[] edgeTo;
	private double[] distTo;

	public AcyclicSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];

		for (int v = 0; v < G.V(); v++) {
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;

		Topological top = new Topological(G);

		for (int v : top.order()) {
			relax(G, v);
		}
	}

	private void relax(EdgeWeightedDigraph g, int v) {
		for (DirectedEdge e : g.adj(v)) {
			int w = e.to();
			if (distTo[w] > distTo[v] + e.weight()) {
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
			}
		}
	}
}
