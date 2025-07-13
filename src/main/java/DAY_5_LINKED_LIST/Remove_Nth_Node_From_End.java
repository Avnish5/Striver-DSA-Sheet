package DAY_5_LINKED_LIST;

public class Remove_Nth_Node_From_End {

    private int findLength(ListNode head) {
        int length = 0;

        while(head != null) {
            length++;
            head = head.next;
        }

        return length;
    }

    // 1. Using findLength
    public ListNode removeNthFromEnd1(ListNode head, int n) {

        int k = findLength(head) - n;
        /*
          Test Case 1:
          Input:
          head = [1]
          n = 1

         Test Case 2:
         Input:
         head = [1, 2]
         n = 2
  Below condition is for these two edge cases
*/
        if (k == 0) return head.next;

        ListNode temp = head;

        for (int i = 1; i < k; i++) {
            temp = temp.next;
        }


        if (temp.next != null) {
            temp.next = temp.next.next;
        }

        return head;
    }

    //2.using two pointers
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode fast = head, slow = head;

        for (int i = 0; i< n; i++) {
            fast = fast.next;
        }

        //handling the case where we want to remove head
        if (fast == null) return head.next;

        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode deleteNode = slow.next;
        slow.next = deleteNode.next;
        deleteNode.next = null;

        return head;

    }





}
