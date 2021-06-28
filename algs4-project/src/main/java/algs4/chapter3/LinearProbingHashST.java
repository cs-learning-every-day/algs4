package algs4.chapter3;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;
	private int n; //符号表中键值对的总数
	private int m;//线性探测表的大小
	private Key[] keys;
	private Value[] vals;

	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashST(int capacity) {
		m = capacity;
		n = 0;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public void put(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");

		if (val == null) {
			delete(key);
			return;
		}


		if (n >= m / 2) resize(2 * m);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		n++;
	}

	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				return vals[i];
			}
		}
		return null;
	}

	public void delete(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to delete() is null");
		if (!contains(key)) return;

		int i = hash(key);
		while (!key.equals(keys[i]))
			i = (i + 1) % m;

		keys[i] = null;
		vals[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % m;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i]=null;
			vals[i]=null;
			n--;
			put(keyToRehash,valToRehash);
			i = (i + 1) % m;
		}

		n--;
		if (n > 0 && n <= m/8) resize(m/2);
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	private void resize(int capacity) {
		final LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m = temp.m;
	}

}
