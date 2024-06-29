/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] items;
    private int capacity = 16;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            resize((capacity >> 1) + capacity);
        }
        items[size++] = item;
    }

    private void resize(int newCapacity) {
        Item[] newItems = (Item[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        this.items = newItems;
        this.capacity = newCapacity;
    }

    private boolean isFull() {
        return size == capacity;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int deleteIdx = StdRandom.uniform(size);
        Item res = items[deleteIdx];
        items[deleteIdx] = items[size - 1];
        size--;
        if (size > 0 && size * 4 <= capacity) {
            resize(capacity >> 1);
        }
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return items[StdRandom.uniform(size)];
    }

    private class RandomIterator implements Iterator<Item> {
        Item[] copyItems;
        int count = 0;

        public RandomIterator() {
            copyItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                copyItems[i] = items[i];
            }
            StdRandom.shuffle(copyItems);
        }

        public boolean hasNext() {
            return count < size;
        }

        public Item next() {
            if (count >= size) {
                throw new NoSuchElementException();
            }
            return copyItems[count++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(4);

        System.out.println(q.sample());
        System.out.println(q.sample());

        for (int a : q) {
            System.out.printf("%d ", a);
        }
        System.out.println();

        q.dequeue();

        for (int a : q) {
            System.out.printf("%d ", a);
        }
        System.out.println();

        q.dequeue();
        q.dequeue();
        q.dequeue();
    }
}
