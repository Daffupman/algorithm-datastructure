package Ch01_linear;

/**
 * @program: data-structure
 * @description: 使用数组底层实现队列
 * @author: Daffupman
 * @create: 2019-03-07 18:02
 **/
public class ArrayQueue<E> implements Queue<E> {

	Array<E> array;

	public ArrayQueue(int capacity) {
		array = new Array<>(capacity);
	}

	public ArrayQueue() {
		array = new Array<>();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public void enqueue(E e) {
		array.addLast(e);
	}

	@Override
	public E dequeue() {
		return array.removeFirst();
	}

	@Override
	public E getFront() {
		return array.getFirst();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Queue: front-> [");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if(i != array.getSize() - 1) {
				res.append(", ");
			}
		}
		res.append("] <-tail");
		return res.toString();
	}

	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<>();
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}
		System.out.println(queue);
		queue.dequeue();
		System.out.println(queue);
	}
}
