package ds.Ch01_linear.$3.queue;

import ds.Ch01_linear.$3.queue.impl.LinkedListQueue;

/**
 * @description 循环队列
 * 				1.生成的数组总是会多申请一个单位的空间，用于队满检查
 * 				2.队空：front == tail;
 * 				3.队满：(tail+1)%data.length == front;
 * 				4.入队：front = (front+1)%data.length;
 * 				5.出队：tail = (tail+1)%data.length;
 * @author Daffupman
 * @date 2019/07/08
 */
public class LoopQueue<E> implements Queue<E> {

	private E[] data;
	private int size;
	private int front,tail;
	
	public LoopQueue() {
		this(10);
	}
	
	public LoopQueue(int capacity) {
		data = (E[])new Object[capacity+1];
		size = 0;
		front = 0;
		tail = 0;
	}
	
	//将数组调整为指定大小的容量
	private void resize(int newCapacity) {
		E[] newData = (E[])new Object[newCapacity+1];
		//数据迁移
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void enqueue(E e) {
		if((tail+1)%data.length == front) {
			resize(2 * size);
		}
		data[tail] = e;
		tail = (tail+1)%data.length;
	}

	@Override
	public E dequeue() {
		E ret = data[front];
		data[front] = null;
		front = (front+1)%data.length;
		return ret;
	}

	@Override
	public E getFront() {
		return data[front];
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
