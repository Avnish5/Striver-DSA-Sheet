package DAY_6_LINKED_LIST_PART_2;

import java.util.*;

public class LL_Cycle_2 {

    /**
     * 1.Brute Force: Hash-Set
     * Time Complexity: O(N)
     *      - Each node is visited once
     * Space Complexity: O(N)
     *      - HashSet stores visited nodes
     */
    public ListNode detectCycle1(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();

        // Traverse through the linked list
        while (head != null) {
            // If the node is already in the set, cycle detected
            if (visited.contains(head)) {
                return head; // Entry point of the cycle
            }

            // Otherwise, store the node and move forward
            visited.add(head);
            head = head.next;
        }

        // If no cycle found, return null
        return null;
    }


    //2. Optimal
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast =  fast.next.next;

            if (slow == fast) {
                while (slow != entry) {
                    slow = slow.next;
                    entry = entry.next;
                }

                return entry;
            }
        }

        return null;
    }

    public ListNode detectCycle(ListNode head) {
           if (head == null || head.next == null) return null;

           ListNode f = head, s = head, entry = head;

           while (f != null && f.next != null) {
               s = s.next;
               f = f.next.next;

               if (s == f) {
                   while (entry != s) {
                       s = s.next;
                       entry = entry.next;
                   }

                   return entry;
               }
           }

           return null;
    }

    public int findSmallestInteger(int[] nums, int value) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i / value);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        boolean flag = false;

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) != list.get(i) - 1) {
                flag = true;
                return list.get(i) - 1;
            }
        }

        return !flag ? list.getLast() : -1;
    }
}
