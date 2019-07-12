package ds.Ch02_tree.$3.set.impl;

import ds.Ch01_linear.$2.linkedlist.LinkedList;
import ds.Ch02_tree.$3.set.Set;

/**
 * @description 基于链表的集合
 * @author Daffupman
 * @date 2019-03-09 16:33
 */
public class LinkedListSet<E> implements Set<E> {

	private LinkedList<E> list;
	
	public LinkedListSet() {
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
	public boolean contains(E e) {
		return list.contains(e);
	}

	@Override
	public void add(E e) {
		if(!contains(e)) {
			list.addLast(e);
		}
	}

	@Override
	public void remove(E e) {
		list.removeElement(e);
	}

	@Override
	public String toString() {
		if(isEmpty())	return "Empty Set!";
		StringBuilder res = new StringBuilder(50);
		res.append("Set:\t[");
		for (int i = 0; i < size(); i++) {
			res.append(list.get(i));
			if(i != size()-1) {
				res.append(',');
			}
		}
		res.append(']');
		return res.toString();
	}

	public static void main(String[] args) {
		Set<Integer> set = new LinkedListSet<>();
		System.out.println(set);
		for (int i = 0; i < 5; i++) {
			set.add(i);
		}
		System.out.println(set);
		set.remove(3);
		System.out.println(set);
	}

}
