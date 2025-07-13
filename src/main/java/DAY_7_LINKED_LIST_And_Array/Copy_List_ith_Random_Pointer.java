package DAY_7_LINKED_LIST_And_Array;


import java.util.HashMap;

public class Copy_List_ith_Random_Pointer {

    // 1. Using Hash-Map
    public ListNode copyRandomList1(ListNode head) {

        ListNode curr = head;
        HashMap<ListNode, ListNode> map = new HashMap<>();

        while( curr != null) {
            map.put(curr, new ListNode(curr.val));
            curr = curr.next;
        }

        curr = head;

        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }

    //2.Optimal approach without hashmap
    public ListNode copyRandomList2(ListNode head) {
        if (head == null) return null;
        ListNode curr = head;

        // Step 1: Clone each node and insert it next to the original
        while (curr != null) {
            ListNode nextNode = new ListNode(curr.val);
            ListNode currNextNode = curr.next;
            curr.next = nextNode;
            nextNode.next = currNextNode;
            curr = currNextNode;
        }

        curr = head;

        // Step 2: Assign random pointers for the copied nodes
        while (curr != null) {
            if (curr.random == null) {
                curr.next.random = null;
            } else {
                curr.next.random = curr.random.next;
            }
            curr = curr.next.next;
        }

        // Step 3: Separate the two lists
        ListNode ans = head.next;
        curr = head;
        ListNode newCurr = curr.next;

        while (curr != null && newCurr != null) {
            curr.next = curr.next == null ? null : curr.next.next;
            newCurr.next = newCurr.next == null ? null: newCurr.next.next;

            curr = curr.next;
            newCurr = newCurr.next;
        }
        return ans;

    }


}
