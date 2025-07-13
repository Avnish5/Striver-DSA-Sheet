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

    //fixing the 1st approach
    public ListNode cloneList(ListNode head) {
        if (head == null) return null;

        ListNode newHead = new ListNode(head.val);
        ListNode currOld = head.next;
        ListNode currNew = newHead;

        while (currOld != null) {
            currNew.next = new ListNode(currOld.val);
            currOld = currOld.next;
            currNew = currNew.next;
        }

        return newHead;
    }
    public boolean isPalindrome2(ListNode head) {
        // Clone the original list before reversing
        ListNode l1 = head;
        ListNode cloned = cloneList(head);
        ListNode l2 = reverseList(cloned);  // Reverse the clone, not the original

        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) return false;
            l1 = l1.next;
            l2 = l2.next;
        }

        return true;
    }

    //Optimal approach
    public ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while( fast!=null && fast.next != null) {
            slow= slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public boolean isPalindrome3(ListNode head) {

        ListNode firstHalf = head;
        ListNode middle = findMiddle(head);
        ListNode secondtHalf = reverseList(middle);

        while (secondtHalf != null) {
            if (secondtHalf.val != firstHalf.val) return false;
            firstHalf = firstHalf.next;
            secondtHalf = secondtHalf.next;

        }

        return true;



    }




}
