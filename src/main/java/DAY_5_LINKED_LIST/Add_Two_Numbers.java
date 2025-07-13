package DAY_5_LINKED_LIST;

public class Add_Two_Numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0, carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (l1 != null || l2 != null || carry == 1) {
            sum = 0;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            sum += carry;

            carry = sum/10;
            ListNode newNode = new ListNode(sum % 10);
            temp.next = newNode;
            temp = newNode;

        }
        return dummy.next;

    }
}
