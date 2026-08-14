package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.Stack;

public class Implement_Min_Stack {
}

class Implement_Min_Stack_Using_Stack{
    // Pair class stores:
    // val -> actual value
    // minimum -> minimum value in stack up to this element
    class Pair {
        int val;
        int minimum;

        public Pair(int val, int minimum) {
            this.val = val;
            this.minimum = minimum;
        }
    }

    Stack<Pair> st;

    // Constructor
    // TC: O(1)
    // SC: O(1)
    public Implement_Min_Stack_Using_Stack() {
        st = new Stack<>();
    }

    // Push element onto stack
    // TC: O(1)
    // SC: O(1) extra per push
    public void push(int val) {
        // If stack is empty, min = val itself
        if (st.isEmpty()) {
            st.push(new Pair(val, val));
            return;
        }

        // Store current value and min so far
        int currentMin = Math.min(val, st.peek().minimum);
        st.push(new Pair(val, currentMin));
    }

    // Remove top element
    // TC: O(1)
    // SC: O(1)
    public void pop() {
        if (st.isEmpty()) return;
        st.pop();
    }

    // Get top element
    // TC: O(1)
    // SC: O(1)
    public int top() {
        if (st.isEmpty()) return -1; // edge case handling
        return st.peek().val;
    }

    // Get minimum element in stack
    // TC: O(1)
    // SC: O(1)
    public int getMin() {
        if (st.isEmpty()) return -1; // edge case handling
        return st.peek().minimum;
    }
}

class Implement_Min_Stack_Using_LL{
    // Node structure for our custom stack
    // val -> actual value
    // min -> minimum value up to this node
    // next -> pointer to next node (like stack link)
    class Node {
        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    Node head; // top of stack

    // Constructor
    // TC: O(1)
    // SC: O(1)
    public Implement_Min_Stack_Using_LL() {
        head = null;
    }

    // Push element onto stack
    // TC: O(1)
    // SC: O(1) per operation
    public void push(int val) {
        if (head == null) {
            // First element → min is itself
            head = new Node(val, val, null);
        } else {
            // Store min so far at this node
            int currentMin = Math.min(val, head.min);
            head = new Node(val, currentMin, head);
        }
    }

    // Remove top element
    // TC: O(1)
    // SC: O(1)
    public void pop() {
        if (head == null) return;
        head = head.next;
    }

    // Get top element
    // TC: O(1)
    // SC: O(1)
    public int top() {
        if (head == null) return -1; // edge case
        return head.val;
    }

    // Get minimum element in stack
    // TC: O(1)
    // SC: O(1)
    public int getMin() {
        if (head == null) return -1; // edge case
        return head.min;
    }
}
