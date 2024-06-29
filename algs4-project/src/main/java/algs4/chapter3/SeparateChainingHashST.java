package algs4.chapter3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class SeparateChainingHashST<Key, Value> {
	private int n; // 键值对总数
	private int m; // 散列表的大小
	private SequentialSearchST<Key, Value>[] st; // 存放链表对象的数组

	public SeparateChainingHashST() {
		this(997);
	}

	public SeparateChainingHashST(int m) {
		this.m = m;
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
		for (int i = 0; i < m; i++)
			st[i] = new SequentialSearchST<>();
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public Value get(Key key) {
		int i = hash(key);
		return st[i].get(key);
	}

	public void put(Key key, Value val) {
		int i = hash(key);
		st[i].put(key, val);
	}

	public void delete(Key key) {
		int i = hash(key);
		st[i].delete(key);
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				queue.offer(key);
			}
		}
		return queue;
	}

}
