package algs4.chapter3;

import algs4.chapter1.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private static final int INIT_CAPACITY = 2;
	private Key[] keys;
	private Value[] vals;
	private int n;

	public BinarySearchST() {
		this(INIT_CAPACITY);
	}

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
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
		int i = rank(key);
		if (i < n && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}

	public int rank(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");

		int lo = 0, hi = n - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else return mid;
		}

		return lo;
	}

	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		int i = rank(key);

		// key is already in table
		if (i < n && keys[i].compareTo(key) == 0) {
			vals[i] = val;
			return;
		}

		// insert new key-value pair
		if (n == keys.length) resize(2 * keys.length);

		for (int j = n; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (isEmpty()) return;


		int i = rank(key);
		if (i == n || keys[i].compareTo(key) != 0) return;

		for (int j = i; j < n - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}
		n--;
		keys[n] = null;
		vals[n] = null;

		if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[n - 1];
	}

	public Key select(int k) {
		return keys[k];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}

	public Key floor(Key key) {
		int i = rank(key);
		if (i < n && key.compareTo(keys[i]) == 0) return keys[i];
		if (i == 0) return null;
		else return keys[i - 1];
	}

	public Iterable<Key> keys(){
		return keys(min(),max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

		Queue<Key> q = new Queue<>();

		if(lo.compareTo(hi) > 0) return q;

		for (int i = rank(lo); i < rank(hi); i++) {
			q.enqueue(keys[i]);
		}

		if(contains(hi))
			q.enqueue(keys[rank(hi)]);
		return q;
	}

	public boolean contains(Key key) {
		if(key==null)throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}


	private void resize(int capacity) {
		keys = Arrays.copyOf(keys, capacity);
		vals = Arrays.copyOf(vals, capacity);

//		Key[]   tempk = (Key[])   new Comparable[capacity];
//		Value[] tempv = (Value[]) new Object[capacity];
//		for (int i = 0; i < n; i++) {
//			tempk[i] = keys[i];
//			tempv[i] = vals[i];
//		}
//		vals = tempv;
//		keys = tempk;
	}

	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}

}
