package DAY_12_HEAPS;

import java.util.Collections;
import java.util.PriorityQueue;

public class Find_Median_from_Data_Stream {
    /**
     *Time complexity: O(log n)
     * Space complexity: O(n)
     */
    PriorityQueue<Integer> left_max_heap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> right_min_heap = new PriorityQueue<>();

    public Find_Median_from_Data_Stream() {

    }

    public void addNum(int num) {
      if (left_max_heap.isEmpty() || num < left_max_heap.peek()) {
          left_max_heap.offer(num);
      } else {
          right_min_heap.offer(num);
      }

      if (Math.abs(left_max_heap.size() - right_min_heap.size()) > 1) {
          left_max_heap.add(right_min_heap.poll());
      } else if (left_max_heap.size() < right_min_heap.size()) {
          left_max_heap.add(right_min_heap.poll());
      }
    }

    public double findMedian() {
       if (left_max_heap.size() == right_min_heap.size()) {
           return (left_max_heap.peek() + right_min_heap.peek()) / 2.0;
       }

       return left_max_heap.peek();
    }

}
