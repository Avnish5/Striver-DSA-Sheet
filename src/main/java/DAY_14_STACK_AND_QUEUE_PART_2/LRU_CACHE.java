package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LRU_CACHE {
}


class LRU_CACHE_Brute_Force {
    // List to store cache entries as {key, value} pairs
    // The most recently used element is kept at the end of the list
    List<int[]> cache;

    // Maximum capacity of cache
    int capacity;

    /**
     * Constructor to initialize the LRU Cache
     * Time Complexity: O(1)
     * Space Complexity: O(1) (excluding cache storage)
     */
    public LRU_CACHE_Brute_Force(int capacity) {
        this.cache = new ArrayList<>();
        this.capacity = capacity;
    }

    /**
     * Retrieves the value of the given key if present in cache.
     * Also marks the key as most recently used by moving it to the end.
     *
     * Time Complexity: O(n)  -> Linear search in worst case
     * Space Complexity: O(1)
     */
    public int get(int key) {

        // Traverse the cache to find the key
        for(int i = 0; i < cache.size(); i++) {

            if(cache.get(i)[0] == key) {

                // Extract value
                int value = cache.get(i)[1];

                // Move the accessed element to the end (MRU position)
                int[] temp = cache.get(i);
                cache.remove(i);
                cache.add(temp);

                return value;
            }
        }

        // Key not found
        return -1;
    }

    /**
     * Inserts or updates the value of the key.
     * If key exists, update value and mark as most recently used.
     * If key does not exist:
     *    - Remove least recently used element if cache is full
     *    - Insert new key-value pair
     *
     * Time Complexity: O(n)  -> Linear search + possible shift on remove
     * Space Complexity: O(1)
     */
    public void put(int key, int value) {

        // Check if key already exists
        for(int i = 0; i < cache.size(); i++) {

            if(cache.get(i)[0] == key) {

                // Update value
                cache.get(i)[1] = value;

                // Move to most recently used position
                int[] temp = cache.get(i);
                cache.remove(i);
                cache.add(temp);

                return;
            }
        }

        // If cache is full, remove least recently used element (front)
        if(cache.size() == capacity) {
            cache.removeFirst();
        }

        // Add new key-value pair as most recently used
        cache.add(new int[]{key, value});
    }
}

class LRU_CACHE_Optimized {

    /*
     * Node class for Doubly Linked List
     */
    class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    // HashMap to store key -> Node mapping
    private Map<Integer, Node> map;

    // Capacity of cache
    private int capacity;

    // Dummy head and tail nodes
    private Node head, tail;

    /*
     * Constructor
     * Time Complexity: O(1)
     * Space Complexity: O(capacity)
     */
    public LRU_CACHE_Optimized(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        // Initialize dummy nodes
        head = new Node(0, 0); // LRU side
        tail = new Node(0, 0); // MRU side

        head.next = tail;
        tail.prev = head;
    }

    /*
     * Get value by key
     * Moves accessed node to MRU position
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Move node to MRU position
        remove(node);
        insert(node);

        return node.value;
    }

    /*
     * Insert or update key-value pair
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void put(int key, int value) {

        // If key already exists → remove old node
        if (map.containsKey(key)) {
            remove(map.get(key));
        }

        // Create new node
        Node node = new Node(key, value);

        // Insert at MRU position
        insert(node);

        // Add to map
        map.put(key, node);

        // If capacity exceeded → remove LRU node
        if (map.size() > capacity) {
            Node lru = head.next; // first real node
            remove(lru);
            map.remove(lru.key);
        }
    }

    /*
     * Removes a node from the DLL
     * Time Complexity: O(1)
     */
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /*
     * Inserts a node right before tail (MRU position)
     * Time Complexity: O(1)
     */
    private void insert(Node node) {
        Node prev = tail.prev;

        prev.next = node;
        node.prev = prev;

        node.next = tail;
        tail.prev = node;
    }
}
