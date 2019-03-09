package Ch02_tree;

import Ch01_linear.LinkedList;

/**
 * @program: data-structure
 * @description: 基于链表的集合
 * @author: Daffupman
 * @create: 2019-03-09 16:33
 **/
public class LinkedListSet<E> implements Set<E> {

	private LinkedList list;

	public LinkedListSet() {
		list = new LinkedList();
	}

	@Override
	public void add(E e) {
		if(!list.contains(e)) {
			list.addFirst(e);
		}
	}

	@Override
	public void remove(E e) {
		list.removeElement(e);
	}

	@Override
	public boolean contains(E e) {
		return list.contains(e);
	}

	@Override
	public int getSize() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
