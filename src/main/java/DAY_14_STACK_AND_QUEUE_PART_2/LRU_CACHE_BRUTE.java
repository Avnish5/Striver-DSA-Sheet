package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an LRU Cache using a simple ArrayList.
 * This is the "Brute Force" approach because both get() and put()
 * operations require a linear search (O(N)) through the list,
 * which makes it inefficient for large caches.
 */
public class LRU_CACHE_BRUTE {

    int capacity;
    // The list holds the cache items. The Least Recently Used (LRU) item
    // is always at index 0, and the Most Recently Used (MRU) item is at the end.
    List<Pair> cache = new ArrayList<>();

    // Inner class to hold the key-value pair for the cache element.
    static class Pair {
        int key;
        int value;

        Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    public LRU_CACHE_BRUTE(int capacity) {
        this.capacity = capacity;
    }

    /**
     * GET Method (O(N) complexity due to linear search):
     * 1. Iterates through the entire list to find the key.
     * 2. If found, it updates the "recency" by moving the item to the end (MRU).
     * 3. Returns the value.
     */
    public int get(int key) {
        // O(N) operation: Linear search for the key
        for(int i = 0; i < cache.size(); i++) {
            if (cache.get(i).key == key) {
                // Found the item. Now mark it as Most Recently Used (MRU).
                Pair temp = cache.get(i);

                // O(N) operation: Removing an element in the middle of an ArrayList
                // requires shifting all subsequent elements, which takes linear time.
                cache.remove(i);

                // O(1) operation: Adding to the end makes it the MRU item.
                cache.add(temp);

                return temp.value;
            }
        }

        return -1; // Key not found
    }

    /**
     * PUT Method (O(N) complexity due to linear search and list manipulation):
     * 1. Checks if the key exists (O(N) search). If so, updates value and moves to MRU.
     * 2. If new, adds it. If full, removes the LRU item first.
     */
    public void put(int key, int value) {
        // O(N) operation: Linear search to see if the key exists for updating.
        for(int i = 0; i < cache.size(); i++) {
            if (cache.get(i).key == key) {
                // Key found: Update value and move to MRU position (end of list).
                cache.get(i).value = value;
                Pair temp = cache.get(i);

                // O(N): Remove from current spot
                cache.remove(i);

                // O(1): Add to the end (MRU)
                cache.add(temp);
                return;
            }
        }

        // Key is new: Check capacity and add.
        if (cache.size() == capacity) {
            // Capacity full: Evict the LRU item.
            // O(N) operation: Removing the element at index 0 requires shifting
            // all subsequent elements, which is the time complexity bottleneck.
            cache.remove(0);

            // Add the new item to the end (MRU).
            cache.add(new Pair(key, value));
        } else {
            // Capacity not full: Just add the new item to the end (MRU).
            cache.add(new Pair(key, value));
        }
    }
}