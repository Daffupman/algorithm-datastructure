package Ch01_linear.$3.queue.impl;

import Ch01_linear.$1.array.Array;
import Ch01_linear.$3.queue.Queue;

/**
 * @description 使用数组底层实现队列
 * @author Daffupman
 * @date 2019-03-07 18:02
 */
public class ArrayQueue<E> implements Queue<E> {

	private Array<E> arr;
	
	public ArrayQueue() {
		arr = new Array<>();
	}
	
	public ArrayQueue(int capacity) {
		arr = new Array<>(capacity);
	}
	
	@Override
	public int size() {
		return arr.size();
	}

	@Override
	public boolean isEmpty() {
		return arr.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		arr.addLast(e);
	}

	@Override
	public E dequeue() {
		return arr.removeFirst();
	}

	@Override
	public E getFront() {
		return arr.getFirst();
	}

	@Override
	public String toString() {
		if(isEmpty())	return "Empty Queue!";
		StringBuilder res = new StringBuilder(50);
		res.append("Queue:\t(front)[");
		for (int i = 0; i < size(); i++) {
			res.append(" ").append(arr.get(i));
			if(i != size()-1) {
				res.append(',');
			}
		}
		res.append("](tail)");
		return res.toString();
	}
	
	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayQueue<>();
		System.out.println(queue);
		for (int i = 0; i < 5; i++) {
			queue.enqueue(i);
		}
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
	}
	
}
