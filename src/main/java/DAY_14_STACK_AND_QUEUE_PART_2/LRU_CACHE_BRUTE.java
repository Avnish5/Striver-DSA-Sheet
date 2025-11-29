package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.ArrayList;
import java.util.List;

class LRU_CACHE_BRUTE {

    // Each element in the list is: [key, value]
    List<int[]> cache;
    int size = 0;

    public LRU_CACHE_BRUTE(int capacity) {
        this.size = capacity;
        cache = new ArrayList<>();
    }

    /**
     * Time Complexity: O(n)
     *   - Linear search through the ArrayList.
     *
     * Space Complexity: O(1)
     *   - No extra space used apart from variables.
     */
    public int get(int key) {

        int n = cache.size();

        // Search for the key linearly
        for (int i = 0; i < n; i++) {
            if (cache.get(i)[0] == key) {

                // Store the element
                int[] temp = cache.get(i);

                // Remove it from the current position
                cache.remove(i);

                // Add it at the end (mark as recently used)
                cache.add(temp);

                return temp[1];
            }
        }

        return -1; // Key not found
    }

    /**
     * Time Complexity: O(n)
     *   - Linear search to find key.
     *   - remove(0) is O(n) because it shifts the whole list.
     *
     * Space Complexity: O(1)
     *   - Extra space used is constant.
     */
    public void put(int key, int value) {

        int n = cache.size();

        // Search if key already exists
        for (int i = 0; i < n; i++) {
            if (cache.get(i)[0] == key) {

                // Get existing pair
                int[] temp = cache.get(i);

                // Update value
                temp[1] = value;

                // Remove old position
                cache.remove(i);

                // Add to end (most recently used)
                cache.add(temp);

                return;
            }
        }

        // If cache is full, remove least recently used (first element)
        if (cache.size() == size) {
            cache.removeFirst();
            cache.add(new int[]{key, value});
        } else {
            // Otherwise, simply add new entry
            cache.add(new int[]{key, value});
        }
    }
}

