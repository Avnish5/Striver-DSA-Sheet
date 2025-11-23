package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.HashMap;
import java.util.Map;

// The Node class represents a single item in our cache.
// It is a Doubly Linked List node so we can remove it from anywhere in O(1) time.
class Node {
    int key;
    int value;
    Node next;
    Node prev;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev = null;
    }
}

public class LRU_CACHE_OPTIMAL {

    int capacity;
    // HashMap allows us to find a Node instantly (O(1)) using its key.
    Map<Integer, Node> cache;

    // 'oldest' and 'latest' are Dummy/Sentinel nodes.
    // They act as permanent boundaries (Head and Tail).
    // Real data is always stored BETWEEN these two.
    // Structure: [oldest] <-> [Real Data] <-> [latest]
    Node oldest;
    Node latest;

    public LRU_CACHE_OPTIMAL(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();

        // Initialize dummy nodes with value 0
        this.oldest = new Node(0, 0);
        this.latest = new Node(0, 0);

        // Connect them: oldest points to latest, latest points to oldest.
        // Initially, the list is empty.
        this.oldest.next = this.latest;
        this.latest.prev = this.oldest;
    }

    /**
     * GET Method:
     * 1. Checks if key exists.
     * 2. If it exists, move it to the 'latest' position (mark as recently used).
     * 3. Return the value.
     */
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);

            // Key logic: To mark as "recently used", we physically move
            // the node to the end of the list.
            remove(node); // Snip it out of its current spot
            insert(node); // Paste it at the front (near latest)

            return node.value;
        }

        return -1; // Key not found
    }

    /**
     * Helper Method: Removes a node from the Doubly Linked List.
     * It connects the node's previous neighbor directly to its next neighbor,
     * effectively skipping over the current node.
     */
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * Helper Method: Inserts a node at the "Most Recently Used" position.
     * We always insert RIGHT BEFORE the 'latest' dummy node.
     */
    private void insert(Node node) {
        Node prev = latest.prev; // The node currently at the end
        Node next = latest;      // The dummy tail marker

        // Wire the new node in between 'prev' and 'next'
        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    /**
     * PUT Method:
     * 1. If key exists, update value and move to front.
     * 2. If key is new, create node and add to front.
     * 3. If capacity is full, delete the LRU (Least Recently Used) node.
     */
    public void put(int key, int value) {
        // Case 1: The key already exists.
        // We remove the old version so we can insert the updated one as new.
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        // Create the new node and add it to Map and List (as MRU)
        Node node = new Node(key, value);
        cache.put(key, node);
        insert(node);

        // Case 2: Capacity Exceeded.
        // We must evict the Least Recently Used item.
        // The LRU item is always the one right after the 'oldest' dummy node.
        if (cache.size() > this.capacity) {
            Node lru = oldest.next; // Identify the victim

            remove(lru);            // Remove from Linked List
            cache.remove(lru.key);  // Remove from HashMap
        }
    }
}