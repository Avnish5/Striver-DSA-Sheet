package DAY_12_HEAPS;

import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap {

    private ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int getSize() {
        return heap.size();
    }

    public int getMin() {
        if (isEmpty()) {
            throw new PriorityQueueException("heap is empty");
        }

        return heap.getFirst();
    }

    public void insert(int element) {
        heap.add(element);
        int childIndex = heap.size() - 1;
        int parentIndex = (childIndex - 1) / 2;

        while (childIndex > 0) {

            if (heap.get(childIndex) < heap.get(parentIndex)) {
                int temp = heap.get(childIndex);
                heap.set(childIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;
            } else {
                return;
            }
        }
    }

    public int remove() {
        if (isEmpty()) {
            throw new PriorityQueueException("heap is empty");
        }

        int remove = heap.get(0);
        heap.set(0, heap.getLast());
        heap.removeLast();

        int index = 0;
        int minIndex = index;
        int leftChildIndex = 1;
        int rightChildIndex = 2;

        while (leftChildIndex < heap.size()) {
            if (heap.get(leftChildIndex) < heap.get(minIndex)) {
                minIndex = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) < heap.get(minIndex)) {
                minIndex = rightChildIndex;
            }

            if (minIndex == index) {
                break;
            } else {
                int temp = heap.get(index);
                heap.set(index, heap.get(minIndex));
                heap.set(minIndex, temp);

                index = minIndex;
                leftChildIndex = (2 * index) + 1;
                rightChildIndex = (2 * index) + 2;
            }
        }

        return remove;

    }
}
