package DAY_16_String_Part_2;

import java.util.stream.IntStream;

public class Check_for_Anagrams {

    /**
     * 1.Brute Force
     *
     * Time Complexity: O(n)
     *  - We traverse both strings once, where n is the length of the strings.
     *
     * Space Complexity: O(1)
     *  - We use a fixed-size array of 26 elements to store character frequencies,
     *    so the space used does not depend on input size.
     */
    public boolean isAnagram1(String s, String t) {
        // If the lengths are not equal, they cannot be anagrams
        if (s.length() != t.length()) return false;

        // Create an array to store frequency of each character (a-z)
        int[] freq = new int[26];

        // Increment frequency based on characters in string s
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Decrement frequency based on characters in string t
        for (char c : t.toCharArray()) {
            freq[c - 'a']--;
        }

        // Check if all frequencies are zero
        for (int count : freq) {
            if (count != 0) return false; // Not an anagram if any count is non-zero
        }

        // If we reach here, all counts are zero => s and t are anagrams
        return true;
    }

    /**
     * 2. Using Java 8+ lambda and streams.
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) - since we use a fixed array of size 26
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] freq = new int[26];

        // Process both strings using streams
        s.chars().forEach(c -> freq[c - 'a']++);
        t.chars().forEach(c -> freq[c - 'a']--);

        // Use IntStream to check if all counts are zero
        return IntStream.of(freq).allMatch(count -> count == 0);
    }


}
