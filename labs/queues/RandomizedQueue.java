/* *****************************************************************************
 *  Name:    Xmchx Coder
 *  NetID:   xmchx
 *  Precept: P00
 *
 *  Description: None
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int n;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        checkItemNotNull(item);
        if (n == items.length) resize(2 * n);
        items[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        checkDequeNotEmpty();
        int idx = StdRandom.uniform(n);

        Item item = items[idx];
        items[idx] = items[n - 1];
        items[--n] = null;

        if (n <= (items.length / 4) && (items.length / 2) > 0) {
            resize(items.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        checkDequeNotEmpty();
        return items[StdRandom.uniform(n)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] random;
        private int idx;

        public RandomizedQueueIterator() {
            // 随机重置原数据 使看起来是随机访问的
            random = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                random[i] = items[i];
            }

            for (int i = 0; i < n; i++) {
                // [i,n)
                int r = StdRandom.uniform(n - i) + i;
                Item tmp = random[i];
                random[i] = random[r];
                random[r] = tmp;
            }
        }

        public boolean hasNext() {
            return idx < n;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return random[idx++];
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

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("Creating new RandQueue...");
        RandomizedQueue<Double> testRandQueue = new RandomizedQueue<Double>();

        System.out.println("Current RandQueue size: " + testRandQueue.size());

        if (testRandQueue.isEmpty()) {
            System.out.println("RandQueue empty.");
        }
        else {
            System.out.println("RandQueue not empty.");
        }

        System.out.println("\nAdding five doubles to the RandQueue");
        testRandQueue.enqueue(1.0);
        testRandQueue.enqueue(2.0);
        testRandQueue.enqueue(3.0);
        testRandQueue.enqueue(4.0);
        testRandQueue.enqueue(5.0);

        System.out.println("\nRandom printing once...");
        for (double i : testRandQueue) {
            System.out.println("Item: " + i);
        }

        System.out.println("\nRandom printing twice...");
        for (double i : testRandQueue) {
            System.out.println("Item: " + i);
        }

        System.out.println("\nOne more for good luck...");
        for (double i : testRandQueue) {
            System.out.println("Item: " + i);
        }

        System.out.println("\nCurrent RandQueue size: " + testRandQueue.size());

        if (testRandQueue.isEmpty()) {
            System.out.println("RandQueue empty.");
        }
        else {
            System.out.println("RandQueue not empty.");
        }

        System.out.println("\nRemoving two doubles from the RandQueue");
        System.out.println("Removing " + testRandQueue.dequeue());
        System.out.println("Removing " + testRandQueue.dequeue());
        System.out.println("Current RandQueue size: " + testRandQueue.size());

        System.out.println("\nRandom printing once...");
        for (double i : testRandQueue) {
            System.out.println("Item: " + i);
        }

        System.out.println("Random Sample: " + testRandQueue.sample());
        System.out.println("Random Sample: " + testRandQueue.sample());

        if (testRandQueue.isEmpty()) {
            System.out.println("RandQueue empty.");
        }
        else {
            System.out.println("RandQueue not empty.");
        }

        System.out.println("\nRemoving two doubles from the RandQueue");
        System.out.println("Removing " + testRandQueue.dequeue());
        System.out.println("Removing " + testRandQueue.dequeue());
        System.out.println("Current RandQueue size: " + testRandQueue.size());

        System.out.println("Removing " + testRandQueue.dequeue());
        System.out.println("Current RandQueue size: " + testRandQueue.size());

        if (testRandQueue.isEmpty()) {
            System.out.println("RandQueue empty.");
        }
        else {
            System.out.println("RandQueue not empty.");
        }
    }


}
