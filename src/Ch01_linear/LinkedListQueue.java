package Ch01_linear;

/**
 * @program: data-structure
 * @description: 使用链表作为底层实现队列
 * @author: Daffupman
 * @create: 2019-03-08 14:20
 **/
public class LinkedListQueue<E> implements Queue<E> {

	//定义链表的节点
	private class Node {
		private E e;
		private Node next;

		public Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}
		public Node(E e) {
			this(e, null);
		}
		@Override
		public String toString() {
			return e.toString();
		}
	}

	private Node head, tail;
	private int size = 0;

	public LinkedListQueue() {
		head = null;
		tail = null;
		size = 0;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(E e) {
		//由于没有设置dummyHead,当链表为空就需要单独处理
		if(tail == null) {
			tail = new Node(e);
			head = tail;
		} else {
			tail.next = new Node(e);
			tail = tail.next;
		}
		size ++;
	}

	@Override
	public E dequeue() {
		if(isEmpty()){
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		}
		Node retNode = head;
		//head指针后移
		head = head.next;
		retNode.next = null;
		if(head == null) {
			tail = null;
		}
		size --;
		return retNode.e;
	}

	@Override
	public E getFront() {
		if(isEmpty()){
			throw new IllegalArgumentException("Empty Queue.");
		}
		return head.e;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: front ");

		Node curr = head;
		while(curr != null) {
			res.append(curr + "->");
			curr = curr.next;
		}
		res.append("NULL tail");
		return res.toString();
	}

	public static void main(String[] args) {
		LinkedListQueue<Integer> queue = new LinkedListQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			System.out.println(queue);
			if(i % 3 == 1) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
		while(!queue.isEmpty()) {
			System.out.println(queue);
			queue.dequeue();
		}
	}
}
