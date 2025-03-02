package deque;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<LochNess> implements Deque<LochNess>, Iterable<LochNess>{
    protected LochNess[] items;
    protected int size;
    protected int head;
    protected int tail;

    public ArrayDeque() {
        items = (LochNess[]) new Object[8];
        size = 0;
        head = 0;
        tail = 1;
    }

    protected int addOne(int index) {
        return (index + 1) % items.length;
    }

    protected int minusOne(int index) {
        return (index + items.length - 1) % items.length;
    }

    protected void checkMul() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    protected void checkDiv() {
        if (items.length >= 16 && size < items.length / 4) {
            resize(items.length / 4);
        }
    }

    protected void resize(int capacity) {
        LochNess[] a = (LochNess[]) new Object[capacity];

        int index = addOne(tail);
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            index = addOne(tail);
        }
        tail = size;
        head = capacity - 1;
        items = a;
    }

    public void addFirst(LochNess x) {
        checkMul();
        items[head] = x;
        head = minusOne(head);
        size += 1;
    }

    public void addLast(LochNess x) {
        checkMul();
        items[tail] = x;
        tail = addOne(tail);
        size += 1;
    }

    public LochNess removeFirst() {
        if (size == 0) {
            return null;
        }

        checkDiv();

        head = addOne(head);
        LochNess item = items[head];
        items[head] = null;
        size -= 1;
        return item;
    }

    public LochNess removeLast() {
        if (size == 0) {
            return null;
        }

        checkDiv();

        tail = minusOne(tail);
        LochNess item = items[tail];
        items[tail] = null;
        size -= 1;
        return item;
    }

    public LochNess get (int index) {
        if (index >= size || index < 0) {
            return null;
        }

        return items[(head + 1 + index) % items.length];
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = addOne(head);
        for (int i = 0; i < size; ++i) {
            System.out.print(items[index] + " ");
            index = addOne(index);
        }

        System.out.println();
    }

    public Iterator<LochNess> iterator() {
        return new ArrayDequeIterator();
    }

    protected class ArrayDequeIterator implements Iterator<LochNess> {
        private int ptr;
        public ArrayDequeIterator() {
            ptr = addOne(head);
        }

        public boolean hasNext() {
            return ptr != tail;
        }

        public LochNess next() {
            LochNess item = items[ptr];
            ptr = addOne(ptr);
            return item;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Deque other = (Deque) obj;
        if (this.size() != other.size()) {
            return false;
        }

        int index = addOne(head);
        for (int i = 0; i < size; i++) {
            if (!(items[index].equals(other.get(i)))) {
                return false;
            }
            index = addOne(index);
        }

        return true;
    }
}
