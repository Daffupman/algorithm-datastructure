package ds.Ch01_linear.$4.stack.impl;

import ds.Ch01_linear.$1.array.Array;
import ds.Ch01_linear.$4.stack.Stack;

/**
 * @description 使用数组来实现栈
 * @author Daffupman
 * @date 2019-03-07 16:20
 */
public class ArrayStack<E> implements Stack<E> {
	
	Array<E> arr;
	
	public ArrayStack() {
		arr = new Array<>();
	}
	
	public ArrayStack(int capacity) {
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
	public void push(E e) {
		arr.addLast(e);
	}

	@Override
	public E pop() {
		return arr.removeLast();
	}

	@Override
	public E peek() {
		return arr.getLast();
	}
	
	@Override
	public String toString() {
		if(isEmpty())	return "Empty Stack!";
		StringBuilder res = new StringBuilder(50);
		res.append("Stack:\t(top)[");
		for (int i = size()-1; i >= 0; i--) {
			res.append(arr.get(i));
			if(i != 0) {
				res.append(',');
			}
		}
		res.append(']');
		return res.toString();
	}
	
	public static void main(String[] args ) {
		Stack<Integer> stack = new ArrayStack<>();
		System.out.println(stack);
		for (int i = 0; i < 6; i++) {
			stack.push(i);
		}
		System.out.println(stack);
		stack.pop();
		System.out.println(stack);
		System.out.println(stack.peek());
	}

}
