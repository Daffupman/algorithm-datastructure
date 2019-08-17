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
		// 设置虚拟头结点
		ListNode dummyHead = new ListNode( -1 );
		dummyHead.next = head;

		// 指针p和q相隔固定的距离：n+1
		ListNode p = dummyHead;
		ListNode q = dummyHead;
		for (int i = 0; i < n + 1; i++) {
			assert q != null;
			q = q.next;
		}

		while( q != null ) {
			// 把q移动到末尾的null处
			p = p.next;
			q = q.next;
		}

		// 删除节点
		p.next = p.next.next;

		return dummyHead.next;

	}

}
