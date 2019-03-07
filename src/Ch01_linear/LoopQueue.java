package Ch01_linear;

/**
 * @program: data-structure
 * @description: 循环队列
 * @author: Daffupman
 * @create: 2019-03-07 18:34
 **/
public class LoopQueue<E> implements Queue<E> {

	private E[] data;
	private int front, tail;
	private int size;

	public LoopQueue(int capacity) {
		//容量+1，是因为我们要故意浪费一个空间
		data = (E[]) new Object[capacity + 1];
		front = 0;
		tail = 0;
		size = 0;
	}

	public LoopQueue() {
		this(10);
	}

	@Override
	public int getSize() {
		return size;
	}

	public int getCapacity() {
		return data.length-1;
	}

	@Override
	public boolean isEmpty() {
		return front == size;
	}

	@Override
	public void enqueue(E e) {
		//判断是否堆满
		if( (tail+1)%data.length == front) {
			resize(getCapacity() * 2);
		}

		//元素入队
		data[tail] = e;
		//tail循环后移
		tail = (tail+1)%data.length;
		size ++;
	}

	private void resize(int newCapacity) {
		E[] newData = (E[]) new Object[newCapacity + 1];
		for (int i = 0; i < size; i++) {
			newData[i] = data[(i+front)%data.length];
		}
		data = newData;
		front = 0;
		tail = size;
	}

	@Override
	public E dequeue() {
		//队列是否为空
		if(isEmpty()) {
			throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
		}

		E ret = data[front];
		data[front] = null;
		front = (front+1)%data.length;
		size --;

		//动态缩容
		if(size == getCapacity() / 4 && getCapacity() /2 != 0) {
			resize(getCapacity() / 2);
		}

		return ret;
	}

	@Override
	public E getFront() {
		//队列是否为空
		if(isEmpty()) {
			throw new IllegalArgumentException("Queue is empty.");
		}
		return data[front];
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append(String.format("LoopQueue:size = %d, capacity = %d\n", size, getCapacity()));
		res.append("front-> [");
		for (int i = front; i != tail ; i = (i+1)%data.length) {
			res.append(data[i]);
			if((i+1)%data.length != tail) {
				res.append(", ");
			}
		}
		res.append("] <-tail\n");
		return res.toString();
	}

	public static void main(String[] args) {
		LoopQueue<Integer> queue = new LoopQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			System.out.println(queue);

			if(i % 3 == 2) {
				queue.dequeue();
				System.out.println(queue);
			}
		}
	}
}
