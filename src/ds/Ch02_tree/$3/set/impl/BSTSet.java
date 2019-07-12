package ds.Ch02_tree.$3.set.impl;

import ds.Ch02_tree.$1.bst.BST;
import ds.Ch02_tree.$3.set.Set;

/**
 * @description 使用BST实现set集合
 * @author Daffupman
 * @date 2019-03-09 15:59
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

	private BST<E> bst;
	
	public BSTSet() {
		bst = new BST<>();
	}
	
	@Override
	public int size() {
		return bst.size();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	@Override
	public boolean contains(E e) {
		return bst.contains(e);
	}

	@Override
	public void add(E e) {
		bst.add(e);
	}
	

	@Override
	public void remove(E e) {
		bst.remove(e);
	}
	
	public static void main(String[] args) {
		Set<Integer> set = new BSTSet<>();
		for (int i = 0; i < 5; i++) {
			set.add(i);
		}
		set.remove(2);
		System.out.println("over");
	}

}
