package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<LochNess> implements Deque<LochNess>, Iterable<LochNess> {
    private class StuffNode {
        public LochNess item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(LochNess i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(LochNess x) {
        sentinel = new StuffNode(null, null, null);
        StuffNode node = new StuffNode(x, null, null);
        sentinel.next = node;
        sentinel.prev = node;
        node.prev = sentinel;
        node.next = sentinel;

        size = 1;
    }

    public void addFirst(LochNess x) {
        StuffNode node = new StuffNode(x, null, null);
        sentinel.next.prev = node;
        sentinel.next = node;
        node.next = sentinel.next;
        node.prev = sentinel;
        size += 1;
    }

    public LochNess getFirst() {
        return sentinel.next.item;
    }

    public LochNess removeFirst() {
        if (isEmpty()) {
            return null;
        }

        LochNess item = getFirst();
        StuffNode firstNode = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        firstNode.next = null;
        firstNode.prev = null;
        size -= 1;
        return item;
    }

    public void addLast(LochNess x) {
        size += 1;
        StuffNode node = new StuffNode(x, null, null);
        node.prev = sentinel.prev;
        sentinel.prev.next = node;
        node.next = sentinel;
        sentinel.prev = node;
    }

    public LochNess getLast() {
        return sentinel.prev.item;
    }

    public LochNess removeLast() {
        if (isEmpty()) {
            return null;
        }

        LochNess item = getLast();
        StuffNode lastNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        lastNode.prev = null;
        lastNode.next = null;
        size -= 1;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode iter = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(iter.item + " ");
            iter = iter.next;
        }
        System.out.print("\n");
    }

    public LochNess get(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }

        StuffNode iter = sentinel.next;
        for (int i = 0; i < index; i++) {
            iter = iter.next;
        }

        return iter.item;
    }

    private LochNess recursive(int index, StuffNode head) {
        if (index == 0) {
            return head.next.item;
        }

        return recursive(index - 1, head.next);
    }

    public LochNess getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size) {
            return null;
        }

        return recursive(index, sentinel);
    }

    public Iterator<LochNess> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<LochNess> {
        private StuffNode ptr;
        public LinkedListDequeIterator() {
            ptr = sentinel.next;
        }

        public boolean hasNext() {
            return ptr != sentinel;
        }

        public LochNess next() {
            LochNess item = ptr.item;
            ptr = ptr.next;
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

        StuffNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (!p.item.equals(other.get(i))) {
                return false;
            }
            p = p.next;
        }

        return true;
    }
}
