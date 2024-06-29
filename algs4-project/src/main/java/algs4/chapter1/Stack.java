package algs4.chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author xmchx on 2020/3/22 21:03
 */
public class Stack<Item> implements Iterable<Item> {
    private Node<Item> first;
    private int n;

    public Stack() {
        first = null;
        n = 0;
    }

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void push(Item item) {
        Node<Item> newNode = new Node<>();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> st = new Stack<>();
        st.push("123");
        st.push("456");
        st.push("789");
        System.out.println(st);
    }
}
