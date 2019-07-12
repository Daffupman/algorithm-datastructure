package ds.Ch02_tree.$3.set;

/**
 * @description Set集合的接口定义
 * @author Daffupman
 * @date 2019-03-09 15:58
 */
public interface Set<E> {

	int size();
	boolean isEmpty();
	boolean contains(E e);
	void add(E e);
	void remove(E e);
	
}
