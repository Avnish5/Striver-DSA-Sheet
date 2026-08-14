package DAY_15_String_Part_1;

public class Haystack_And_Needle {

    /**
     * 1. Brute Force (Character-by-Character Matching)
     *
     * Time Complexity: O((N - M + 1) * M)
     *   - N = length of haystack
     *   - M = length of needle
     *   - In the worst case, for every possible starting index in haystack,
     *     we compare up to M characters.
     *   - Overall: O((N - M + 1) * M), which is commonly written as O(N * M).
     *
     * Space Complexity: O(1)
     *   - No extra space is used apart from a few variables.
     *
     */
    public int strStr1(String haystack, String needle) {

        int n = haystack.length();
        int m = needle.length();

        int ans = -1;

        for (int i = 0; i < n; i++) {

            // Check if the first character matches
            if (haystack.charAt(i) == needle.charAt(0)) {

                ans = i;
                int k = 0;

                // Compare remaining characters
                for (int j = i;
                     j < n && k < m && haystack.charAt(j) == needle.charAt(k);
                     j++, k++) {

                    // Entire needle matched
                    if (k == m - 1) {
                        return ans;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * 2. Rabin-Karp Algorithm (Rolling Hash)
     *
     * Time Complexity: O(N + M) Average Case
     *   - O(M) to compute the initial hash of the needle and first window.
     *   - O(N - M) to slide the window across the haystack.
     *   - Hash comparison takes O(1).
     *   - In case of a hash collision, character comparison takes O(M).
     *   - Worst Case: O(N * M) due to hash collisions.
     *
     * Space Complexity: O(1)
     *   - Uses only a few extra variables for hashing.
     */
    public int strStr2(String haystack, String needle) {

        int n = haystack.length();
        int m = needle.length();

        if (m == 0) return 0;
        if (m > n) return -1;

        long base = 31;
        long mod = 1_000_000_007;
        long power = 1;

        // Compute base^(m-1)
        for (int i = 1; i < m; i++) {
            power = (power * base) % mod;
        }

        long windowHash = 0;
        long needleHash = 0;

        // Compute hash of first window and needle
        for (int i = 0; i < m; i++) {
            windowHash = (windowHash * base + haystack.charAt(i)) % mod;
            needleHash = (needleHash * base + needle.charAt(i)) % mod;
        }

        // Check first window
        if (windowHash == needleHash && haystack.regionMatches(0, needle, 0, m)) {
            return 0;
        }

        // Slide the window
        for (int i = m; i < n; i++) {

            // Remove leftmost character
            windowHash = (windowHash - (power * haystack.charAt(i - m)) % mod + mod) % mod;

            // Add new character
            windowHash = (windowHash * base + haystack.charAt(i)) % mod;

            int start = i - m + 1;

            // Verify match to avoid false positives due to hash collisions
            if (windowHash == needleHash && haystack.regionMatches(start, needle, 0, m)) {
                return start;
            }
        }

        return -1;
    }
}
