import java.util.ArrayList;
import java.util.Comparator;

public class MinHeap<E extends Comparable<E>> {

    private ArrayList<E> list;
    private Comparator<E> comparator;

    public MinHeap() {
        this.list = new ArrayList<>();
        this.comparator = null;
    }

    public MinHeap(ArrayList<E> items) {
        this.list = items;
        this.comparator = null;
        buildHeap();
    }

    public MinHeap(ArrayList<E> items, Comparator<E> comparator) {
        this.list = items;
        this.comparator = comparator;
        buildHeap();
    }

    private int compare(E a, E b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return ((Comparable<E>) a).compareTo(b);
        }
    }

    public void insert(E item) {
        list.add(item);
        int i = list.size() - 1;
        int parent = parent(i);
        while (parent != i && compare(list.get(i), list.get(parent)) < 0) {
            swap(i, parent);
            i = parent;
            parent = parent(i);
        }
    }

    public void buildHeap() {
        for (int i = list.size() / 2 - 1; i >= 0; i--) {
            minHeapify(i);
        }
    }

    public E extractMin() {
        if (list.size() == 0) {
            throw new IllegalStateException("MinHeap is EMPTY");
        } else if (list.size() == 1) {
            return list.remove(0);
        }
        E min = list.get(0);
        list.set(0, list.remove(list.size() - 1));
        minHeapify(0);
        return min;
    }

    public void decreaseKey(int i, E key) {
        if (compare(key, list.get(i)) > 0) {
            throw new IllegalArgumentException("New key is larger than the original key");
        }
        list.set(i, key);
        int parent = parent(i);
        while (i > 0 && compare(list.get(parent), list.get(i)) > 0) {
            swap(i, parent);
            i = parent;
            parent = parent(parent);
        }
    }

    private void minHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;

        if (left <= list.size() - 1 && compare(list.get(left), list.get(smallest)) < 0) {
            smallest = left;
        }

        if (right <= list.size() - 1 && compare(list.get(right), list.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    public E getMin() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int parent(int i) {
        if (i % 2 == 1) {
            return i / 2;
        } else {
            return (i - 1) / 2;
        }
    }

    private void swap(int i, int parent) {
        E temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }
}
