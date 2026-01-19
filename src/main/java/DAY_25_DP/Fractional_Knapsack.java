package DAY_25_DP;

import java.util.Arrays;

public class Fractional_Knapsack {

    class Item {
        int value;
        int weight;
        double ratio;

        // Constructor to initialize item value, weight,
        // and compute value-to-weight ratio
        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    /*
     * Fractional Knapsack Problem
     *
     * Time Complexity (TC):
     *   O(n log n)
     *   - O(n) to create item list
     *   - O(n log n) to sort items by value/weight ratio
     *
     * Space Complexity (SC):
     *   O(n)
     *   - Extra space used to store Item objects
     */
    public double fractionalKnapsack(int[] val, int[] wt, int capacity) {

        int n = wt.length;
        Item[] items = new Item[n];

        // Create items with value, weight, and ratio
        for (int i = 0; i < n; i++) {
            items[i] = new Item(val[i], wt[i]);
        }

        // Sort items by decreasing value-to-weight ratio
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0.0;
        int remainingCapacity = capacity;

        // Select items greedily
        for (Item item : items) {

            // Stop if knapsack is full
            if (remainingCapacity == 0) break;

            // Take full item if possible
            if (item.weight <= remainingCapacity) {
                maxValue += item.value;
                remainingCapacity -= item.weight;
            }
            // Take fractional part of item
            else {
                maxValue += item.ratio * remainingCapacity;
                remainingCapacity = 0;
            }
        }

        return maxValue;
    }
}
