package algs4.chapter4;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Edge implements Comparable<Edge> {
	private final int v;
	private final int w;
	private final double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int either() {
		return v;
	}

	public int other(int vertex) {
		if (vertex == v) return w;
		else if (vertex == w) return v;
		else throw new RuntimeException("inconsistent edge");
	}

	@Override
	public int compareTo(Edge that) {
		if (this.weight() < that.weight()) return -1;
		else if (this.weight() > that.weight()) return 1;
		else return 0;
	}

	public double weight() {
		return weight;
	}

	@Override
	public String toString() {
		return String.format("%d-%d %.2f", v, w, weight);
	}
}
