package ds.Ch01_linear.$3.queue;

/**
 * @description 循环队列
 * 				1.生成的数组总是会多申请一个单位的空间，用于队满检查
 * 				2.队空：front == rear;
 * 				3.队满：(rear+1)%data.length == front;
 * 				4.入队：front = (front+1)%data.length;
 * 				5.出队：rear = (rear+1)%data.length;
 * @author Daffupman
 * @date 2019/07/08
 */
public class LoopQueue<E> implements Queue<E> {

	private E[] data;
	private int size;
	private int front, rear;

	public LoopQueue() {
		this(10);
	}
	
	public LoopQueue(int capacity) {
		data = (E[])new Object[capacity+1];
		size = 0;
		front = 0;
		rear = 0;
	}
	
	//将数组调整为指定大小的容量
	private void resize(int newCapacity) {
		E[] newData = (E[])new Object[newCapacity+1];
		//数据迁移
		for (int i = 0; i < size; i++) {
			newData[i] = data[(i+front)%data.length];
		}
		data = newData;
	}

	private int getCapacity() {
		return data.length;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return front == rear;
	}

	@Override
	public void enqueue(E e) {
		if((rear +1)%getCapacity() == front) {
			resize(2 * size);
		}
		data[rear] = e;
		rear = (rear +1)%getCapacity();
		size ++;
	}

	@Override
	public E dequeue() {
		if(front == rear) {
			throw new IllegalArgumentException("Empty Queue!");
		}

		E ret = data[front];
		data[front] = null;
		front = (front+1)%getCapacity();
		size --;

		if(size == getCapacity() / 4 && getCapacity() / 2 != 0) {
			resize(getCapacity() / 2);
		}

		return ret;
	}

	@Override
	public E getFront() {
		return data[front];
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(50);
		res.append("Queue: [");
		for(int i = front; i != rear; i = (i+1)%getCapacity()) {
			res.append(data[i]);
			if((i+1)%getCapacity() != rear) {
				res.append(", ");
			}
		}
		res.append("]");
		return res.toString();
	}

	public static void main(String[] args) {
		LoopQueue<Integer> queue = new LoopQueue<>();
		System.out.println(queue);
		for (int i = 0; i < 5; i++) {
			queue.enqueue(i);
		}
		System.out.println(queue);
		System.out.println(queue.dequeue());
		System.out.println(queue);
	}

}
