package DAY_16_String_Part_2;

public class Find_the_Index_of_the_First_Occurrence_in_a_String {

    /**
     * 1. Brute-Force
     *
     * Time Complexity:
     *   - Worst-case: O(n * m)
     *     Where n = length of haystack, m = length of needle.
     *     In the worst case, we may compare m characters for each of the n positions.
     *
     *   - Best-case: O(m), if the match is found at the very beginning.
     *
     * Space Complexity: O(1)
     *   - No additional data structures are used.
     */
    public int strStr1(String haystack, String needle) {
        int n = haystack.length();
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int k = 0;
                ans = i;

                for (int j = i; j < n && haystack.charAt(i) == needle.charAt(k); j++, k++) {
                    if (k == needle.length()-1) {
                        return ans;
                    }
                }

            }
        }

        return -1;
    }

    /**
     * Builds the LPS (Longest Prefix which is also Suffix) array for the needle string.
     * Used by the KMP algorithm to skip unnecessary comparisons.
     */
    private int[] computeLPS(String needle) {
        int m = needle.length();
        int[] LPS = new int[m];
        int len = 0;  // length of the previous longest prefix suffix
        int i = 1;

        LPS[0] = 0; // LPS[0] is always 0

        while (i < m) {
            if (needle.charAt(i) == needle.charAt(len)) {
                len++;
                LPS[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = LPS[len - 1]; // fallback in LPS
                } else {
                    LPS[i] = 0;
                    i++;
                }
            }
        }

        return LPS;
    }

    /**
     * 2. KMP algorithm.
     *
     * Time Complexity: O(n + m)
     * Space Complexity: O(m) — for the LPS array
     */
    public int strStr2(String haystack, String needle) {
        int n = haystack.length();
        int m = needle.length();

        // Edge case: empty needle should return 0
        if (m == 0) return 0;

        int[] LPS = computeLPS(needle);

        int i = 0; // pointer for haystack
        int j = 0; // pointer for needle

        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;

                // full match found
                if (j == m) {
                    return i - j;
                }
            } else {
                // mismatch after j matches
                if (j != 0) {
                    j = LPS[j - 1];
                } else {
                    i++;
                }
            }
        }

        return -1; // needle not found
    }


    public int[] getNoZeroIntegers(int n) {

        for (int i = 1; i < n; i++) {
            int a = i;
            int b = n - a;

            if (!String.valueOf(a).contains("0") && !String.valueOf(b).contains("0")) {
                return new int[]{a,b};
            }
        }
        return new int[]{-1,-1};
    }
}
