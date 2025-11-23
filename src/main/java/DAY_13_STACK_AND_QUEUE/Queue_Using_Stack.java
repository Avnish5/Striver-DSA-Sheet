package DAY_13_STACK_AND_QUEUE;

import java.util.Stack;

public class Queue_Using_Stack {

    private Stack<Integer> in;
    private Stack<Integer> out;

    public Queue_Using_Stack() {
         in = new Stack<>();
         out  = new Stack<>();
    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {
       peek();
       return  out.pop();
    }

    public int peek() {
        if (out.empty()) {
            while (!in.empty()) {
                out.add(in.pop());
            }
        }

        return out.peek();
    }

    public boolean empty() {
      return in.empty() && out.empty();
    }
}
