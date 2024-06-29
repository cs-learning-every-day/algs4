package algs4.chapter3;

import algs4.chapter1.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class SequentialSearchST<Key, Value> {
	private int n;
	private Node first;

	private class Node {
		Key key;
		Value val;
		Node next;

		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		if (isEmpty()) return null;
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}

	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

		if (val == null) {
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}

		first = new Node(key, val, first);
		n++;
	}

	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		first = delete(first, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null) return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}


	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<>();
		for (Node x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String key : st.keys()) {
			StdOut.println(key + " " + st.get(key));
		}
	}
}
