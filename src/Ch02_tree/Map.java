package Ch02_tree;

/**
 * @program: data-structure
 * @description: map接口方法
 * @author: Daffupman
 * @create: 2019-03-09 20:52
 **/
public interface Map<K,V> {

	void add(K key, V value);
	V remove(K key);
	boolean contains(K key);
	V get(K key);
	void set(K key, V value);
	int getSize();
	boolean isEmpty();

}
