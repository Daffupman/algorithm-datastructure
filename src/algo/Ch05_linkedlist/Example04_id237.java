package algo.Ch05_linkedlist;

/**
 * @author Daffupman
 * @description leetcode:  delete node in a linked list
 * @date 2019/8/2 19:28
 */
public class Example04_id237 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	public void deleteNode(ListNode node) {
		if( node == null ) {
			return;
		}
		if( node.next == null ) {
			node = null;
			return;
		}
		node.val = node.next.val;
		ListNode delNode = node.next;
		node.next = delNode.next;

		delNode.next = null;
	}

}
