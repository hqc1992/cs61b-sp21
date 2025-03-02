package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator cmp;

    public MaxArrayDeque(Comparator<T> c) {
        items = (T[]) new Object[8];
        size = 0;
        head = 0;
        tail = 1;
        cmp = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }

        int index = addOne(head);
        T max = items[index];
        for (int i = 0; i < size; i++) {
            if (cmp.compare(items[index], max) > 0) {
                max = items[index];
            }

            index = addOne(index);
        }

        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        int index = addOne(head);
        T max = items[index];
        for (int i = 0; i < size; i++) {
            if (c.compare(items[index], max) > 0) {
                max = items[index];
            }

            index = addOne(index);
        }

        return max;
    }

}
