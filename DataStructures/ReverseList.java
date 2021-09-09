package DataStructures;

import java.util.Stack;

public class ReverseList {
    public class ListNode {
        int val;
        ListNode next = null;
        ListNode(int val) {
            this.val = val;
        }
    }
    //Method 2
    public ListNode ReverseList2(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        while (!stack.isEmpty()){
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        node.next = null;
        return dummy;
    }

    // Method 1
    public ListNode ReverseList1(ListNode head){
        ListNode current = head, pre = null;
        while(null != current) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    // Method 3
    public ListNode ReverseList3(ListNode head) {
        ListNode newHead = null;
        while(head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }
}
