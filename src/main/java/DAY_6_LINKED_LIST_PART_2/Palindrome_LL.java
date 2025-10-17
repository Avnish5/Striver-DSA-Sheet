package DAY_6_LINKED_LIST_PART_2;

public class Palindrome_LL {

    //Fail-code
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next= prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }


    public boolean isPalindrome1(ListNode head) {
        ListNode l1 = head;
        ListNode l2 = reverseList(head);

        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }

        return true;
    }

    public ListNode cloneList(ListNode head) {
        // If the list is empty, return null as there's nothing to clone
        if (head == null) return null;

        // Create the head of the cloned list
        ListNode newHead = new ListNode(head.val);
        ListNode currOld = head.next;
        ListNode currNew = newHead;

        // Traverse original list and create copies of each node
        while (currOld != null) {
            currNew.next = new ListNode(currOld.val); // Create new node with same value
            currOld = currOld.next; // Move in original list
            currNew = currNew.next; // Move in cloned list
        }

        return newHead; // Return the head of the cloned list
    }

    /**
     * 2. Fixing the failed code 1.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N) — due to cloned list
     */
    public boolean isPalindrome2(ListNode head) {
        // Pointer to traverse the original list
        ListNode l1 = head;

        // Clone the list so the original remains unchanged
        ListNode cloned = cloneList(head);

        // Reverse the cloned list to compare
        ListNode l2 = reverseList(cloned);

        // Traverse both lists and compare values
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false; // Mismatch found, not a palindrome
            l1 = l1.next; // Move in original list
            l2 = l2.next; // Move in reversed cloned list
        }

        return true; // All values matched, it's a palindrome
    }

    public ListNode findMiddle(ListNode head) {
        // Two-pointer technique: slow moves 1 step, fast moves 2 steps
        ListNode slow = head;
        ListNode fast = head;

        // When fast reaches end, slow will be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // Middle node
    }

    /**
     * 3.Optimized approach
     *
     * Time Complexity: O(N) — Finding middle O(N/2), reversing O(N/2), comparing O(N/2)
     * Space Complexity: O(1) — Reversal done in-place without extra data structure
     */
    public boolean isPalindrome3(ListNode head) {
        // First half pointer
        ListNode firstHalf = head;

        // Find the middle of the list
        ListNode middle = findMiddle(head);

        // Reverse the second half starting from middle
        ListNode secondHalf = reverseList(middle);

        // Compare both halves
        while (secondHalf != null) {
            if (secondHalf.val != firstHalf.val) return false; // Mismatch found
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true; // All nodes matched, it's a palindrome
    }

}
