package algo.Ch05_linkedlist;

/**
 * @author Daffupman
 * @description leetcode: reverse linked list
 * @date 2019/8/2 18:27
 */
public class Example01_id206 {

	public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

	public ListNode reverseList(ListNode head) {

		ListNode prev = null;
		ListNode curr = head;
		while(curr != null) {
			ListNode next = curr.next;

			curr.next = prev;
			prev = curr;
			curr = next;
		}

		//curr指针最终指向的是null，所有应该返回的是curr之前的指针prev
		return prev;
	}

}
