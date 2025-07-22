package DAY_15_String_Part_1;

import java.util.HashMap;

public class Roman_to_integer {

    /**
     * 1. Approach scanning left to right with adjustment for subtractive notation.
     *
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(1) - only a fixed-size HashMap and variables used
     */
    public int romanToInt1(String s) {
        HashMap<Character, Integer> romanMap = new HashMap<>();

        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        if (s.length() == 1) return romanMap.get(s.charAt(0));

        int largestBefore = romanMap.get(s.charAt(0));
        int sum = romanMap.get(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            int val = romanMap.get(s.charAt(i));

            if (largestBefore < val){
                // Subtractive case, subtract previous value twice (once to undo previous add)
                sum += val - largestBefore;
                sum -= largestBefore;
            } else {
                sum += val;
            }

            largestBefore = val;
        }

        return sum;
    }

    /**
     * 2. Optimal approach scanning right to left,
     *    subtracting smaller values before larger ones.
     *
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(1) - only a fixed-size HashMap and variables used
     */
    public int romanToInt2(String s) {
        HashMap<Character, Integer> romanMap = new HashMap<>();

        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        if (s.length() == 1) return romanMap.get(s.charAt(0));

        int total = 0;
        int prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = romanMap.get(s.charAt(i));
            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }
            prev = curr;
        }

        return total;
    }

}
