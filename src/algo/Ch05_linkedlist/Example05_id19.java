package algo.Ch05_linkedlist;

/**
 * @author Daffupman
 * @description leetcode : Remove Nth Node From End of List
 * @date 2019/8/2 19:38
 */
public class Example05_id19 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	//指针对撞
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummyHead = new ListNode( -1 );
		dummyHead.next = head;

		ListNode p = dummyHead;
		ListNode q = dummyHead;
		for (int i = 0; i < n + 1; i++) {
			assert q != null;
			q = q.next;
		}

		while( p != null ) {
			p = p.next;
			q = q.next;
		}

		ListNode delNode = p.next;
		p.next = delNode.next;
		delNode.next = null;

		ListNode retNode = dummyHead.next;
		dummyHead = null;

		return retNode;

	}

}
