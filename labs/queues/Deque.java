/* *****************************************************************************
 *  Name:    Xmchx Coder
 *  NetID:   xmchx
 *  Precept: P00
 *
 *  Description:  None
 *
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node head;
    private Node tail;

    // construct an empty deque
    public Deque() {
    }


    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItemNotNull(item);

        Node newNode = new Node(item);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        checkItemNotNull(item);

        Node newNode = new Node(item);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        }
        else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        checkDequeNotEmpty();
        Node removeNode = head;

        if (size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }

        size--;
        return removeNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        checkDequeNotEmpty();

        Node removeNode = tail;

        if (size == 1) {
            tail = null;
            head = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }

        size--;
        return removeNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // check item not null
    private void checkItemNotNull(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("");
        }
    }

    // check deque not empty
    private void checkDequeNotEmpty() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
    }

    private class Node {
        Item item;
        Node next = null;
        Node prev = null;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Creating new Deque...");
        Deque<String> testDeque = new Deque<String>();

        System.out.println("Current deque size: " + testDeque.size());

        if (testDeque.isEmpty()) {
            System.out.println("Deque empty.");
        }
        else {
            System.out.println("Deque not empty.");
        }

        System.out.println("\nAdding three strings to the Deque");
        testDeque.addFirst("String 1");
        testDeque.addFirst("String 2");
        testDeque.addLast("String 3");

        for (String i : testDeque) {
            System.out.println("Item: " + i);
        }
        System.out.println("Current deque size: " + testDeque.size());

        if (testDeque.isEmpty()) {
            System.out.println("Deque empty.");
        }
        else {
            System.out.println("Deque not empty.");
        }

        System.out.println("\nRemoving two strings from the Deque");
        System.out.println("Removing " + testDeque.removeFirst());
        System.out.println("Removing " + testDeque.removeLast());
        System.out.println("Current deque size: " + testDeque.size());

        if (testDeque.isEmpty()) {
            System.out.println("Deque empty.");
        }
        else {
            System.out.println("Deque not empty.");
        }

        System.out.println("\nRemoving one string from the Deque");
        System.out.println("Removing " + testDeque.removeFirst());

        System.out.println("\nAdding one string to the Deque");
        testDeque.addFirst("String 4");
        System.out.println("Current deque size: " + testDeque.size());

        System.out.println("\nRemoving one string from the Deque");
        System.out.println("Removing " + testDeque.removeLast());

        System.out.println("Current deque size: " + testDeque.size());

        if (testDeque.isEmpty()) {
            System.out.println("Deque empty.");
        }
        else {
            System.out.println("Deque not empty.");
        }
    }


}
