package ds.Ch01_linear.$3.queue.impl;

import ds.Ch01_linear.$3.queue.Queue;

/**
 * @description 使用链表作为底层实现队列
 * @author Daffupman
 * @date 2019-03-08 14:20
 */
public class LinkedListQueue<E> implements Queue<E> {

	//定义链表的节点
	private class Node {
		private E e;				//数据域
		private Node next;			//指针域
		
		Node(E e) {
			this(e, null);
		}
		
		Node(E e, Node next) {
			this.e = e;
			this.next = next;
		}

		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node front, rear;		//头尾指针
	private int size;				//队列中的元素
	
	public LinkedListQueue() {
		front = rear =null;
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//从队尾添加元素
	@Override
	public void enqueue(E e) {
		if(rear == null) {
			//队空
			rear = new Node(e);
			front = rear;
		} else {
			rear.next = new Node(e);
			rear = rear.next;
		}
		size ++;
	}

	//出队队首元素
	@Override
	public E dequeue() {
		if(isEmpty()) {
			throw new RuntimeException("Empty Queue!");
		}
		Node retNode = front;
		front = front.next;
		if(front == null)	rear = null;
		size --;
		
		return retNode.e;
	}

	//返回队首元素
	@Override
	public E getFront() {
		if(isEmpty()) {
			throw new RuntimeException("Empty Queue!");
		}
		return front.e;
	}
	
	@Override
	public String toString() {
		if(isEmpty())	return "Empty Queue!";
		StringBuilder res = new StringBuilder(50);
		Node curr = front;
		res.append("Queue:\t(front)");
		while(curr != null) {
			res.append(curr.e);
			if(curr.next != null) {
				res.append(" -> ");
			} else {
				res.append("(tail)");
			}
			curr = curr.next;
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedListQueue<>();
		System.out.println(queue);
		for (int i = 0; i < 5; i++) {
			queue.enqueue(i);
		}
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
	}

}
