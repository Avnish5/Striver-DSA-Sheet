package DAY_16_String_Part_2;

public class Count_And_Say {

    /**
     * 1. Recursive Solution
     *
     * Time Complexity: O(2^n)
     * - We make n recursive calls (linear depth)
     * - At each level k, we process a string of length L(k)
     * - String lengths grow exponentially: L(k) ≈ 2^k in worst case
     * - Total work: L(1) + L(2) + ... + L(n) ≈ 2^1 + 2^2 + ... + 2^n = O(2^n)
     *
     * Space Complexity: O(2^n)
     * - Recursive call stack depth: O(n)
     * - At each recursive level, we store strings from previous levels
     * - Total string storage: L(1) + L(2) + ... + L(n) ≈ O(2^n)
     * - Overall: O(n + 2^n) = O(2^n)
     *
     * Note: In practice, garbage collection might reduce space to O(n + L(n)),
     * but worst-case analysis assumes all intermediate strings remain in memory.
     */
    public String countAndSay1(int n) {
        // Base case: The first term in the sequence is always "1"
        if (n == 1) {
            return "1";
        }

        // Recursive case: Get the (n-1)th term first
        // This builds the sequence from bottom up: 1 → 2 → 3 → ... → n
        String smallAns = countAndSay1(n - 1);

        // StringBuilder to efficiently build the nth term
        // Using StringBuilder instead of String concatenation for O(1) amortized append
        StringBuilder sb = new StringBuilder();

        // Process the (n-1)th term character by character to generate nth term
        for (int i = 0; i < smallAns.length(); i++) {
            // Get the current character/digit we're examining
            char currentChar = smallAns.charAt(i);

            // Initialize count for consecutive occurrences of this character
            int count = 1;

            // Count how many consecutive times this character appears
            // We use (i < smallAns.length() - 1) to avoid index out of bounds
            while (i < smallAns.length() - 1 && smallAns.charAt(i) == smallAns.charAt(i + 1)) {
                count++;        // Increment count for each consecutive occurrence
                i++;           // Move to next character
            }
            // Note: The outer for loop will increment i again, so we end up at the
            // correct position (last occurrence of the current character)

            // Build the "count and say" representation
            // Format: [count][digit] - e.g., "3" appears 2 times → "23"
            sb.append(String.valueOf(count));  // Append the count
            sb.append(currentChar);            // Append the actual character
        }

        // Convert StringBuilder to String and return
        return sb.toString();
    }

    /**
     * 2. Iteratively builds the nth term of the Count and Say sequence.
     *
     * Time Complexity: O(2^n)
     *   - Because the length of each term roughly doubles each time.
     *
     * Space Complexity: O(2^n)
     *   - Due to the StringBuilder holding the full result string.
     */
    public String countAndSay2(int n) {
        // Base case: first term is "1"
        String result = "1";

        // Build terms from 2 to n
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            char c = result.charAt(0);

            // Process the current term (result) to build the next term
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == c) {
                    count++;
                } else {
                    sb.append(count).append(c);
                    c = result.charAt(j);
                    count = 1;
                }
            }

            // Append the last group
            sb.append(count).append(c);

            // Update result to the next term
            result = sb.toString();
        }

        return result;
    }

}
