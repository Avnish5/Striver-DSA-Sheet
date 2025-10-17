package DAY_6_LINKED_LIST_PART_2;

import java.util.HashSet;
import java.util.Set;

public class Intersection_Of_Two_LL {

    public int findLength(ListNode head) {
        int  l =0;

        while(head != null) {
            l++;
            head = head.next;
        }

        return l;
    }

    /**
     * 1. Brute Force
     *
     * Time Complexity: O(m * n)
     *      - m = number of nodes in List A
     *      - n = number of nodes in List B
     *      - For each node in A, we iterate over B
     *
     * Space Complexity: O(1)
     *      - No extra data structures are used, only pointers
     */
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        // Traverse through List A
        while (l1 != null) {
            // For each node in A, start traversing List B
            ListNode temp = l2;

            // Check every node in List B to see if there's a match
            while (temp != null) {
                if (temp == l1) { // Intersection found (reference equality)
                    return temp;
                }
                temp = temp.next;
            }
            // Move to the next node in List A
            l1 = l1.next;
        }

        // If no intersection is found, return null
        return null;
    }


    /**
     * 2. Using Hash-Set
     *
     * Time Complexity: O(m + n)
     *      - m = number of nodes in List A (to insert into set)
     *      - n = number of nodes in List B (to check against set)
     *
     * Space Complexity: O(m)
     *      - Extra space is used to store nodes of List A in a HashSet
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        // HashSet to store nodes of List A
        Set<ListNode> set = new HashSet<>();

        // Add all nodes of List A into the HashSet
        while (l1 != null) {
            set.add(l1);
            l1 = l1.next;
        }

        // Traverse List B and check if any node exists in the HashSet
        while (l2 != null) {
            if (set.contains(l2)) { // Found intersection based on reference equality
                return l2;
            }
            l2 = l2.next;
        }

        // If no intersection is found
        return null;
    }

    /**
     * 3. Brute Force
     *
     * Time Complexity: O(m + n)
     *      - m = length of List A (for length calculation + traversal)
     *      - n = length of List B (for length calculation + traversal)
     *
     * Space Complexity: O(1)
     *      - Only pointer variables are used, no extra data structures
     */
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        int l1 = findLength(headA); // Find length of List A
        int l2 = findLength(headB); // Find length of List B

        int diff = Math.abs((l1 - l2)); // Calculate length difference

        // Move the head of the longer list ahead by 'diff' steps
        if (l1 < l2) {
            while (diff != 0) {
                headB = headB.next;
                diff--;
            }
        } else {
            while (diff != 0) {
                headA = headA.next;
                diff--;
            }
        }

        // Move both pointers together until they meet
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        // Either intersection node or null if no intersection
        return headA;
    }


    /**
     * 4. Optimal Two-Pointer Approach:
     * Time Complexity: O(m + n)
     *      - Each pointer traverses both lists once.
     *
     * Space Complexity: O(1)
     *      - No extra space used, just pointer variables.
     */
    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        // If either list is empty, there can't be an intersection
        if (l1 == null || l2 == null) return null;

        // Traverse both lists, switching to the other list when end is reached
        while (l1 != l2) {
            l1 = (l1 != null) ? l1.next : headB; // If l1 reaches end, jump to headB
            l2 = (l2 != null) ? l2.next : headA; // If l2 reaches end, jump to headA
        }

        // l1 (or l2) will be either the intersection node or null
        return l1;
    }

}
