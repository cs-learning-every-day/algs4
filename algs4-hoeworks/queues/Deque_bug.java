/* *****************************************************************************
 *  Name: Deque
 *  Date: 4/14 2022
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque_bug<Item> implements Iterable<Item> {
    private static final int DEFAULT_CAPACITY = 8;
    private int head, tail;
    private int size, capacity;
    private Item[] items;

    // construct an empty deque
    public Deque_bug() {
        head = tail = DEFAULT_CAPACITY / 2;
        size = 0;
        capacity = DEFAULT_CAPACITY;
        items = (Item[]) new Object[DEFAULT_CAPACITY];
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
        if (isFull()) {
            expanCapacity();
        }
        head = nextIdx(head - 1);
        items[head] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be not null");
        }
        if (isFull()) {
            expanCapacity();
        }
        tail = nextIdx(tail + 1);
        items[tail] = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty array");
        }
        Item res = items[head];
        head = nextIdx(head + 1);
        size--;
        return res;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty array");
        }
        Item res = items[tail];
        tail = nextIdx(tail - 1);
        size--;
        return res;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int emptyIdx = capacity / 2;
            int front = head, end = capacity / 2 + 1;
            int count = 0;

            public boolean hasNext() {
                return front != emptyIdx || end <= tail || count != size;
            }

            public Item next() {
                Item res;
                if (front != emptyIdx) {
                    res = items[front];
                    front = nextIdx(front + 1);
                }
                else if (end <= tail) {
                    res = items[end];
                    end = nextIdx(end + 1);
                }
                else {
                    throw new NoSuchElementException();
                }
                count++;
                return res;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private void expanCapacity() {
        int newCapacity = capacity << 1;
        Item[] newItems = (Item[]) new Object[newCapacity];

        int newHead = newCapacity / 2;

        int count = 0;
        // copy头部
        int emptyIdx = capacity / 2;
        int i = emptyIdx - 1;
        while (i != emptyIdx && count <= size) {
            newHead = (newHead - 1 + newCapacity) % newCapacity;
            newItems[newHead] = items[i];
            i = nextIdx(i - 1);
            count++;
        }
        // copy尾部
        i = capacity / 2 + 1;
        while (i != emptyIdx && count <= size) {
            newHead = (newHead - 1 + newCapacity) % newCapacity;
            newItems[newHead] = items[i];
            i = nextIdx(i + 1);
            count++;
        }

        this.items = newItems;
        this.capacity = newCapacity;
        this.head = newHead;
        this.tail = newCapacity / 2;
    }

    private int nextIdx(int idx) {
        return (idx + capacity) % capacity;
    }

    private boolean isFull() {
        return size == capacity - 1;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque_bug<Integer> q = new Deque_bug<>();
        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);

        q.addFirst(7);
        // q.addLast(8);

        for (int a : q) {
            System.out.println(a);
        }

    }
}
