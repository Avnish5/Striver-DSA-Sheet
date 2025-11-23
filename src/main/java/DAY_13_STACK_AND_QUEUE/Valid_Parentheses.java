package DAY_13_STACK_AND_QUEUE;

import java.util.Stack;

public class Valid_Parentheses {
    /**
     * Time Complexity:    O(n)
     *   We iterate through each character exactly once.
     *
     * Space Complexity:   O(n)
     *   In the worst case (all opening brackets), the stack can hold n characters.
     */
    public boolean isValid(String s) {

        Stack<Character> st = new Stack<>();

        for (char c : s.toCharArray()) {

            // Push all opening brackets
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            }
            else {
                // If we meet a closing bracket, stack must not be empty
                if (st.isEmpty()) return false;

                char top = st.pop();

                // Check matching pair
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }

        // If stack is empty → all brackets matched correctly
        return st.isEmpty();
    }
}
