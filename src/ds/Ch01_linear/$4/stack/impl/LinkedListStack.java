package ds.Ch01_linear.$4.stack.impl;

import ds.Ch01_linear.$2.linkedlist.LinkedList;
import ds.Ch01_linear.$4.stack.Stack;

public class LinkedListStack<E> implements Stack<E> {

	private LinkedList<E> list;
	
	public LinkedListStack() {
		list = new LinkedList<>();
	}
	
	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void push(E e) {
		list.addLast(e);
	}

	@Override
	public E pop() {
		return list.removeLast();
	}

	@Override
	public E peek() {
		return list.getLast();
	}
	
	@Override
	public String toString() {
		if(isEmpty())	return "Empty Stack!";
		StringBuilder res = new StringBuilder(50);
		res.append("Stack:\t(top)[");
		for (int i = size()-1; i >= 0; i--) {
			res.append(list.get(i));
			if(i != 0) {
				res.append(',');
			}
		}
		res.append(']');
		return res.toString();
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new LinkedListStack<>();
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
