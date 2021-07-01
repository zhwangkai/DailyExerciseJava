public class ListReverse {
	class Node {
		int data;
		Node next;
		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		ListReverse lr = new ListReverse();
		Node head, tail;
		head = tail = lr.new Node(0);
		for (int i = 1; i < 10; i++) {
			Node p = lr.new Node(i);
			tail.next = p;
			tail = p;
		}
		tail = head;
		while(tail != null) {
			System.out.println(tail.data);
			tail = tail.next;
		}
		head = reverse(head);
		while(head != null) {
			System.out.println(head.data);
			head = head.next;
		}
	}

	private static Node reverse(Node head) {
		Node p1, p2 = null;
		p1 = head;
		while(head.next != null) {
			p2 = head.next;
			head.next = p2.next;
			p2.next = p1;
			p1 = p2;
		}
		return p2;
	}
}