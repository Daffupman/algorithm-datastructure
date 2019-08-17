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
		// while循环条件：确保有成对的节点
		while( p.next != null && p.next.next != null) {
			ListNode node1 = p.next;
			ListNode node2 = node1.next;
			// 留好后路
			ListNode next = node2.next;

			//交换node1和node2
			node2.next = node1;
			node1.next = next;
			p.next = node2;

			// p指向下一对需要交换的节点【之前】的位置
            // 此时的node1已经和node2交换好位置了
			p = node1;
		}

//		ListNode retNode = dummyHead.next;
//		dummyHead.next = null;
//
//		return retNode;

        // 由于Java的垃圾回收机制，可以不用手动销毁对象
        return dummyHead.next;
	}

}
