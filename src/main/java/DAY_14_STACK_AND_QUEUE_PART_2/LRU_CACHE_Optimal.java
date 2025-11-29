package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.HashMap;
import java.util.Map;

class LRU_CACHE_OPTIMAL {

    /**
     * Node for doubly linked list.
     * Stores key, value, and pointers to previous and next nodes.
     */
    private class Node {
        int val;
        int key;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // HashMap for O(1) access to nodes by key
    private Map<Integer, Node> cache;

    // Maximum allowed capacity
    private int capacity;

    // Dummy head (oldest/LRU side)
    private Node oldest;

    // Dummy tail (most recently used side)
    private Node latest;

    /**
     * Initialize LRU Cache with given capacity.
     *
     * TC: O(1)
     * SC: O(1) — initial structure only
     */
    public LRU_CACHE_OPTIMAL(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();

        // Dummy nodes to avoid null checks
        this.oldest = new Node(0, 0);
        this.latest = new Node(0, 0);

        // Connect head and tail
        this.oldest.next = this.latest;
        this.latest.prev = this.oldest;
    }

    /**
     * Return the value for a given key.
     *
     * Logic:
     *  - If found, move node to the "latest" (MRU side).
     *  - If not found, return -1.
     *
     * Time Complexity: O(1)
     *  - HashMap lookup + fixed pointer updates
     *
     * Space Complexity: O(1)
     */
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);

            // Move to MRU position
            remove(node);
            insert(node);

            return node.val;
        }
        return -1;
    }

    /**
     * Remove a node from the doubly linked list.
     *
     * Time Complexity: O(1)
     *  - Just pointer manipulation
     *
     * Space Complexity: O(1)
     */
    public void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Insert a node right before the 'latest' dummy node.
     * This marks it as the most recently used.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     */
    public void insert(Node node) {
        Node prev = latest.prev;
        Node next = latest;

        // Connect new node between prev and latest
        prev.next = node;
        next.prev = node;

        node.prev = prev;
        node.next = next;
    }

    /**
     * Insert or update a key-value pair into LRU cache.
     *
     * Logic:
     * 1. If key exists → remove old node.
     * 2. Insert new node as most recently used.
     * 3. If over capacity → remove least recently used (oldest.next).
     *
     * Time Complexity: O(1)
     *  - HashMap insert/remove + fixed DLL operations
     *
     * Space Complexity: O(1) extra
     *  - Cache storage grows to capacity only
     */
    public void put(int key, int value) {

        // If key exists, remove its current node
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        // Create new node and insert at MRU
        Node node = new Node(key, value);
        cache.put(key, node);
        insert(node);

        // If capacity exceeded → remove LRU
        if (cache.size() > this.capacity) {
            Node lru = oldest.next;  // first real node (least recently used)
            remove(lru);
            cache.remove(lru.key);
        }
    }
}

