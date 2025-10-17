package DAY_6_LINKED_LIST_PART_2;

public class Reverse_Nodes_In_K_Group {

    public int findLength(ListNode head) {
        int l = 0;
        while(head != null) {
            l++;
            head = head.next;
        }

        return l;
    }

    //1. Brute-Force Recursion
    public ListNode reverseKGroup1(ListNode head, int k) {

        ListNode temp = head;
        int cnt = 0;

        while (cnt < k) {
            if (temp == null) return head;
            cnt++;
            temp = temp.next;
        }

        ListNode prevNode = reverseKGroup1(temp, k);
        temp = head;
        cnt = 0;

        while (cnt < k) {
            ListNode next = temp.next;
            temp.next = prevNode;
            prevNode = temp;
            temp = next;
            cnt++;
        }

        return prevNode;

    }

    /**
     * 2. Optimal
     *
     * Time Complexity: O(N)
     *      - Each node is visited and processed once during reversal.
     *
     * Space Complexity: O(1)
     *      - Only pointers are used for manipulation, no extra data structures.
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        int N = findLength(head);           // Find total nodes
        int groupSize = N / k;              // Number of full groups of size k

        ListNode prevHead = null;           // Tail of previous reversed group
        ListNode currHead = head;           // Start of current group to reverse
        ListNode ansNode = null;            // Final head after complete reversal

        // Process each group
        for (int i = 0; i < groupSize; i++) {
            ListNode prev = null;
            ListNode curr = currHead;
            ListNode next = null;

            // Reverse current group of size 'k'
            for (int j = 0; j < k; j++) {
                next = curr.next;   // Save next node
                curr.next = prev;   // Reverse link
                prev = curr;        // Move prev ahead
                curr = next;        // Move curr ahead
            }

            // If this is the first reversed group, set answer head
            if (prevHead == null) {
                ansNode = prev;
            } else {
                prevHead.next = prev; // Link previous group to current reversed group
            }

            prevHead = currHead;    // Update prevHead to connect next group later
            currHead = curr;        // Move to next group start
        }

        // Attach the remaining part which is less than 'k' nodes
        prevHead.next = currHead;

        return ansNode; // Final head of modified list
    }



}
