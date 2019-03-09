package Ch02_tree;

/**
 * @program: data-structure
 * @description:
 * @author: Daffupman
 * @create: 2019-03-09 15:58
 **/
public interface Set<E> {

	void add(E e);
	void remove(E e);
	boolean contains(E e);
	int getSize();
	boolean isEmpty();

}
