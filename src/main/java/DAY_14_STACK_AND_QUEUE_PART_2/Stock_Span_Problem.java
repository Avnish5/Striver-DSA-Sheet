package DAY_14_STACK_AND_QUEUE_PART_2;

import java.util.Stack;

public class Stock_Span_Problem {

    // Stack stores pairs: [price, span]
    Stack<int[]> st;

    public Stock_Span_Problem() {
        st = new Stack<>();
    }

    public int next(int price) {

        // Start with span 1 (today itself)
        int span = 1;

        // While stack has prices less than or equal to current price
        // keep adding their spans (skip backward because span already known)
        while (!st.isEmpty() && st.peek()[0] <= price) {
            span += st.pop()[1];
        }

        // Push the current price with its calculated span
        st.push(new int[]{price, span});

        // Return the final computed span
        return span;
    }
}

