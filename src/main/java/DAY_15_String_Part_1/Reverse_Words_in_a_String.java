package DAY_15_String_Part_1;

public class Reverse_Words_in_a_String {

    /**
     * 1. Reverse words using split with "\\s+" (handles multiple spaces and whitespace).
     *
     * Time Complexity: O(n) - where n is the length of the input string.
     *   - One pass to split the string.
     *   - One reverse loop through the array.
     *   - Appending each word to a StringBuilder.
     *
     * Space Complexity: O(n) - for the split array and StringBuilder result.
     */
    public String reverseWords1(String s) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }

        return sb.toString().trim();
    }

    /**
     * 2. Reverse words using split with " " and filtering empty strings.
     *    (Manually handles multiple spaces, requires cleanup).
     *
     * Time Complexity: O(n) - where n is the length of the input string.
     *   - One pass to split.
     *   - One pass to reverse the array.
     *   - One pass to build the final string, ignoring empty words.
     *
     * Space Complexity: O(n) - for the array from split and the final StringBuilder result.
     */
    public String reverseWords2(String s) {
        String[] words = s.split(" ");
        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                if (sb.length() > 0) {
                    sb.append(" ");
                }
                sb.append(word);
            }
        }

        return sb.toString();
    }


}
