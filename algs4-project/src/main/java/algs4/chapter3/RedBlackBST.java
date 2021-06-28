package algs4.chapter3;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	private class Node {
		private Key key; // key
		private Value val; // associated data
		private Node left, right; // links to left and right subtrees
		private boolean color = BLACK; // color of parent link
		private int size; // subtree count

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}

	public RedBlackBST() {
	}

	/*****************************************
	 * Node Helper methods.
	 *****************************************/
	// is node x red? false if x is null
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}

	// number of node in subtree rooted at x; 0 if x is null
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 *
	 * @return
	 */
	public int size() {
		return size(root);
	}

	/**
	 * Is this symbol table empty?
	 *
	 * @return
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/*****************************************
	 * Standard BST search.
	 *****************************************/

	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.val;
		}
		return null;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	/*****************************************
	 * Red-black tree insertion.
	 *****************************************/
	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		root = put(root, key, val);
		root.color = BLACK;
	}

	private Node put(Node h, Key key, Value val) {
		if (h == null)
			return new Node(key, val, BLACK, 1);

		int cmp = key.compareTo(h.key);
		if (cmp < 0) h.left = put(h.left, key, val);
		else if (cmp > 0) h.right = put(h.right, key, val);
		else h.val = val;

		if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if (isRed(h.left) && !isRed(h.right)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}


	/*****************************************
	 * Red-black tree helper functions.
	 *****************************************/
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}


}
