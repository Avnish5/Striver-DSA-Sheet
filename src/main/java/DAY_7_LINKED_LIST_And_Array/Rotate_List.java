package DAY_7_LINKED_LIST_And_Array;

import java.util.*;

public class Rotate_List {

    //1. Brute-force
    public ListNode rotateRight1(ListNode head, int k) {

        if (head == null || head.next == null) return head;

        List<Integer> list = new ArrayList<>();
       ListNode temp = head;

        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }

        for (int i = 0; i < k; i++) {
            int last = list.removeLast();
            list.addFirst(last);
        }

        ListNode dummy = new ListNode(0);
        temp = dummy;

        for (int val : list) {
            ListNode newNode = new ListNode(val);
            temp.next = newNode;
            temp = newNode;
        }

        return dummy.next;
    }

    //2. Optimal approach - Circular
    public ListNode rotateRight2(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find the length and tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Make it circular
        tail.next = head;

        // Step 3: Find new tail (n - k % n steps ahead)
        int stepsToNewTail = length - (k % length);
        ListNode newTail = tail;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 4: Break the circle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }


}


