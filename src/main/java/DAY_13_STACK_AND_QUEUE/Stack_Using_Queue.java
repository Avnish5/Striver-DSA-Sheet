package DAY_13_STACK_AND_QUEUE;

import java.util.LinkedList;
import java.util.Queue;

public class Stack_Using_Queue {

    private Queue<Integer> q;

    public Stack_Using_Queue() {
           q = new LinkedList<>();
    }

    public void push(int x) {
        q.add(x);

        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.remove());
        }
    }

    public int pop() {
        if (empty()) return -1;
        return q.remove();
    }

    public int top() {
      if(empty()) return -1;
      return q.peek();
    }

    public boolean empty() {
     return q.isEmpty();
    }
}
