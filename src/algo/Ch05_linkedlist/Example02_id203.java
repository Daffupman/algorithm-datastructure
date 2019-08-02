package algo.Ch05_linkedlist;

/**
 * @author Daffupman
 * @description leetcode: 203 Reverse Linked List Elements
 * @date 2019/8/2 18:55
 */
public class Example02_id203 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public ListNode removeElements(ListNode head, int val) {

		//设置虚拟头结点
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;

		ListNode prev = dummyHead;

		while( prev.next != null ) {
			if( prev.next.val == val ) {
				ListNode delNode = prev.next;
				prev.next = delNode.next;
				delNode.next = null;
			} else {
				prev = prev.next;
			}
		}
		return dummyHead.next;

	}

}
