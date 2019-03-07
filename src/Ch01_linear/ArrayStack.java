package Ch01_linear;


/**
 * @program: data-structure
 * @description: 使用数组来实现栈
 * @author: Daffupman
 * @create: 2019-03-07 16:20
 **/
public class ArrayStack<E> implements Stack<E> {

	Array<E> array;

	public ArrayStack(int capacity) {
		array = new Array<>(capacity);
	}

	public ArrayStack() {
		array = new Array<>();
	}

	@Override
	public int getSize() {
		return array.getSize();
	}

	public int getCapacity() {
		return array.getCapacity();
	}

	@Override
	public boolean isEmpty() {
		return array.isEmpty();
	}

	@Override
	public void push(E o) {
		array.addLast(o);
	}

	@Override
	public E pop() {
		return array.removeLast();
	}

	@Override
	public E peek() {
		return array.getLast();
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("Stack:[");
		for (int i = 0; i < array.getSize(); i++) {
			res.append(array.get(i));
			if(i != array.getSize() - 1) {
				res.append(", ");
			}
		}
		res.append("] <- top");
		return res.toString();
	}

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>();
		for (int i = 0; i < 5; i++) {
			stack.push(i);
		}
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
	}

}
