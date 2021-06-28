package algs4.chapter1;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 仅支持 增加和迭代的数据类型
 *
 * @author xmchx on 2020/3/21 13:58
 */
public class Bag<Item> implements Iterable<Item> {
    private Node<Item> firstItem;
    private int size;

    private static class Node<Item> {
        private Item item;
        private Node<Item> nextItem;
    }

    public Bag() {
        firstItem = null;
        size = 0;
    }

    public void add(Item item) {
        Node<Item> oldFirst = firstItem;
        firstItem = new Node<>();
        firstItem.item = item;
        firstItem.nextItem = oldFirst;
        size++;
    }

    public boolean contains(Item item) {
        Iterator<Item> iterator = this.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(item))
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return firstItem == null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<>(firstItem);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> currentItem;

        public ListIterator(Node<Item> firstItem) {
            currentItem = firstItem;
        }

        @Override
        public boolean hasNext() {
            return currentItem != null;
        }

        @Override
        public Item next() {
            while (!hasNext())
                throw new NoSuchElementException();
            Item item = currentItem.item;
            currentItem = currentItem.nextItem;
            return item;
        }
    }

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        bag.add("1");
        bag.add("1");
        bag.add("2");

        System.out.println("size of bag = " + bag.size());
        for (String s:
             bag) {
            System.out.println(s);
        }
        System.out.println(bag.contains(null));
        System.out.println(bag.contains("2"));
        System.out.println(bag.contains("3"));
    }
}
