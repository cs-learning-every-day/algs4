/* *****************************************************************************
 *  Name: Deque
 *  Date: 4/14 2022
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node prev;
        Node next;

        public Node(Item item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node() {
        }
    }

    private int size;
    private Node guard;


    // construct an empty deque
    public Deque() {
        guard = new Node();
        guard.next = guard;
        guard.prev = guard;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }
        Node newNode = new Node(item, guard, guard.next);
        guard.next.prev = newNode;
        guard.next = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }
        Node newNode = new Node(item, guard.prev, guard);
        guard.prev.next = newNode;
        guard.prev = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty array");
        }
        Node deleteNode = guard.next;
        Item res = deleteNode.item;
        guard.next = deleteNode.next;
        deleteNode.next.prev = guard;
        size--;
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty array");
        }
        Node deleteNode = guard.prev;
        Item res = deleteNode.item;
        deleteNode.prev.next = guard;
        guard.prev = deleteNode.prev;
        size--;
        return res;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node p = guard.next;

            public boolean hasNext() {
                return p != guard;
            }

            public Item next() {
                if (p == guard) {
                    throw new NoSuchElementException();
                }
                Item item = p.item;
                p = p.next;
                return item;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);

        q.addFirst(7);
        q.addLast(8);

        System.out.println("Size: " + q.size());
        q.removeFirst();
        q.removeLast();

        for (int a : q) {
            System.out.println(a);
        }
        System.out.println("---------");

        for (int i = 0; i < 6; i++) {
            q.removeFirst();
        }
        for (int a : q) {
            System.out.println(a);
        }
        System.out.println("---------");

        for (int i = 0; i < 6; i++) {
            q.addLast(i);
            q.addFirst(i * 2);
        }
        for (int i = 0; i < 6; i++) {
            q.removeLast();
        }
        for (int a : q) {
            System.out.println(a);
        }
        // Iterator<Integer> iterator = q.iterator();
        // iterator.next();
    }
}
