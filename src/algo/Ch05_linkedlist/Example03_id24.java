package algo.Ch05_linkedlist;

/**
 * @author Daffupman
 * @description leetcode: 24 Swap Nodes in Pairs
 * @date 2019/8/2 19:10
 */
public class Example03_id24 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}

	//设立四个节点：需要交换位置的两个节点（node1，node2），指针p指向node1和node2的左边的位置
	//           指针next指向两节点的右边
	public ListNode swapPairs(ListNode head) {
		ListNode dummyHead = new ListNode( 0 );
		dummyHead.next = head;

		ListNode p = dummyHead;
		while( p.next != null && p.next.next != null) {
			ListNode node1 = p.next;
			ListNode node2 = node1.next;
			ListNode next = node2.next;

			//交换node1和node2
			node2.next = node1;
			node1.next = next;
			p.next = node2;

			//p指向下一对需要交换的节点之前的位置
			p = node1;
		}

		ListNode retNode = dummyHead.next;
		dummyHead.next = null;

		return retNode;
	}

}
