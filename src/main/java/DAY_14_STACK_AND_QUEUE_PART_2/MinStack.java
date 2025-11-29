package DAY_14_STACK_AND_QUEUE_PART_2;


public class MinStack {

    private Node head;
    public MinStack() {

    }

    public void push(int val) {
        if (head == null) {
            head = new Node(val, val, null);
        } else {
            head = new Node(val, Math.min(val, head.val), head);
        }

    }

    public void pop() {
      head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
      return head.minimum;
    }

    private class Node {
        int val;
        int minimum;
        Node next;

        public Node(int val, int minimum, Node next) {
            this.val = val;
            this.minimum = minimum;
            this.next = next;
        }
    }
}
