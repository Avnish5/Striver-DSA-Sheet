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

    public ListNode reverseKGroup2(ListNode head, int k) {
       int N = findLength(head);
       int groupSize = N / k;

        ListNode prevHead = null;
        ListNode currHead = head;
        ListNode ansNode = null;

        for (int i = 0; i < groupSize; i++) {
            ListNode prev = null;
            ListNode curr = currHead;
            ListNode next = null;

            for (int j = 0; j < k; j++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;

            }

            if (prevHead == null) {
                ansNode = prev;
            } else {
                prevHead.next = prev;
            }

            prevHead = currHead;
            currHead = curr;
        }

        prevHead.next = currHead;
        return ansNode;
    }


}
