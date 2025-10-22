package DAY_12_HEAPS;

import java.util.ArrayList;

public class MaxHeap {

    private ArrayList<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
    }

    boolean isEmpty() {
        return heap.isEmpty();
    }

    int getSize() {
       return heap.size();
    }

    int getMax() {
       if (isEmpty()) {
           throw new PriorityQueueException("heap is empty");
       }

       return heap.getFirst();
    }

    void insert(int element) {
        heap.add(element);
        int childIndex = heap.size() - 1;
        int parentIndex = (childIndex - 1) /2;

        while (childIndex > 0) {
            if (heap.get(childIndex) > heap.get(parentIndex)) {
                int temp = heap.get(childIndex);
                heap.set(childIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);

                childIndex = parentIndex;
                parentIndex = (childIndex - 1) /2;
            } else {
                return;
            }
        }
    }

    int removeMax() {
        if (isEmpty()) {
            throw new PriorityQueueException("heap is empty");
        }

        int remove = heap.getFirst();
        heap.set(0, heap.getLast());
        heap.removeLast();

        int index = 0;
        int maxIndex = index;
        int leftChildIndex = 1;
        int rightChildIndex = 2;

        while (leftChildIndex < heap.size()) {
            if (heap.get(leftChildIndex) > heap.get(maxIndex)) {
                maxIndex = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && heap.get(rightChildIndex) > heap.get(maxIndex)) {
                maxIndex = rightChildIndex;
            }

            if (maxIndex == index) {
                break;
            } else {
                int temp = heap.get(maxIndex);
                heap.set(maxIndex, heap.get(index));
                heap.set(index, temp);

                index = maxIndex;
                leftChildIndex = (2 * index) + 1;
                rightChildIndex = (2 * index) + 2;
            }
        }

        return remove;

    }
}
