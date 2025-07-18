package DAY_4_Arrays_Part_4;

import java.util.HashMap;
import java.util.Map;

public class Count_Subarrays_with_given_XOR {

    /**
     * 1. Brute-force Approach using Nested Loops
     *
     * Time Complexity: O(n^2)
     *   - Outer loop selects starting index.
     *   - Inner loop computes XOR for all subarrays starting at that index.
     *
     * Space Complexity: O(1)
     *   - Only a few integer variables are used.
     */
    public long subarrayXorBruteForce(int[] arr, int k) {
        int count = 0;  // Total number of subarrays with XOR = k
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int xor = arr[i];

            // Single element subarray
            if (xor == k) {
                count++;
            }

            // Check all subarrays starting at index i
            for (int j = i + 1; j < n; j++) {
                xor ^= arr[j];  // Compute XOR from i to j

                if (xor == k) {
                    count++;
                }
            }
        }

        return count;  // Return total subarrays with XOR = k
    }

    /**
     * 2. Optimal Approach using Prefix XOR and HashMap
     *
     * Time Complexity: O(n)
     *   - We iterate through the array once.
     *   - HashMap operations (get/put) are O(1) on average.
     *
     * Space Complexity: O(n)
     *   - The HashMap stores prefix XORs and their frequencies.
     *   - In the worst case, all prefix XORs are distinct.
     */
    public long subarrayXor(int[] arr, int k) {
        long count = 0;  // Total number of subarrays with XOR = k
        Map<Integer, Integer> map = new HashMap<>();  // Stores frequency of prefix XORs
        int prefixXOR = 0;  // Running XOR of elements from start to current index

        map.put(0, 1);  // Base case: empty prefix has XOR 0

        for (int i = 0; i < arr.length; i++) {
            prefixXOR ^= arr[i];  // Update prefix XOR with current element

            // Check if there is a prefix XOR such that: prefixXOR ^ target = existing XOR
            if (map.containsKey(prefixXOR ^ k)) {
                count += map.get(prefixXOR ^ k);  // Add count of matching prefix XORs
            }

            // Update frequency of current prefix XOR in the map
            map.put(prefixXOR, map.getOrDefault(prefixXOR, 0) + 1);
        }

        return count;  // Return total subarrays with XOR = k
    }




}
