package DAY_5_LINKED_LIST;

public class Merge_Two_Sorted_Lists {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode head = null;
        ListNode tail = null;

        if (list1.val > list2.val) {
            head = list2;
            tail = list2;
            list2 = list2.next;
        } else {
            head = list1;
            tail = list1;
            list1 = list1.next;
        }

        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            } else {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            }

        }

        if (list1 != null) {
            tail.next = list1;

        } else {
            tail.next = list2;

        }

        return head;

    }
}
