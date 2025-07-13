package DAY_5_LINKED_LIST;

public class Delete_Node_in_a_LL {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
