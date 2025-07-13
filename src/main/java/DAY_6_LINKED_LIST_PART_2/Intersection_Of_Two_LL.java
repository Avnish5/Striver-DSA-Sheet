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

    // 1. Brute force -  Using loop
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        while (l1 != null) {
            ListNode temp = l2;

            while(temp != null) {
                if (temp == l1) {
                    return temp;
                }
                temp = temp.next;
            }
            l1 = l1.next;
        }

        return null;
    }

    // 2. Hashset - Optimal
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        Set<ListNode> set = new HashSet<>();

        while (l1 != null) {
            set.add(l1);
            l1 = l1.next;
        }

        while (l2 != null) {
            if (set.contains(l2)) {
                return l2;
            }

            l2 = l2.next;
        }

        return null;
    }

    // 3.
    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        int l1 = findLength(headA);
        int l2 = findLength(headB);

        int diff = Math.abs((l1 - l2));

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

        while( headA != headB) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    //4.Optimal approach
    public ListNode getIntersectionNode4(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;

        if(l1 == null || l2 == null) return null;

        while (l1 != l2) {
            l1 = (l1 != null) ? l1 = l1.next : headB;
            l2 = (l2 != null) ? l2 = l2.next : headA;
        }

        return l1;
    }
}
