package Ch02_tree;

import java.util.ArrayList;

/**
 * @program: data-structure
 * @description:
 * @author: Daffupman
 * @create: 2019-03-09 15:59
 **/
public class BSTSet<E extends Comparable<E>> implements Set<E> {

	private BST<E> bst;

	public BSTSet() {
		bst = new BST<>();
	}

	@Override
	public void add(E e) {
		bst.add(e);
	}

	@Override
	public void remove(E e) {
		bst.remove(e);
	}

	@Override
	public boolean contains(E e) {
		return bst.contains(e);
	}

	@Override
	public int getSize() {
		return bst.size();
	}

	@Override
	public boolean isEmpty() {
		return bst.isEmpty();
	}

	public static void main(String[] args) {
		System.out.println("Price and Prejudice");
		ArrayList<String> words =  new ArrayList<>();
			FileOperation.readFile("E:\\workspace\\idea\\data-structure\\src\\pride-and-prejudice.txt",words);
		System.out.println("Total words:"+words.size());

		BSTSet<String> set = new BSTSet();
		for (String word : words) {
			set.add(word);
		}
		System.out.println("Total different words:"+set.getSize());

	}
}
