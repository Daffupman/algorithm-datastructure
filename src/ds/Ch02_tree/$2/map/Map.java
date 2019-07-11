package ds.Ch02_tree.$2.map;

/**
 * @description map接口方法
 * @author Daffupman
 * @date 2019-03-09 20:52
 */
public interface Map<K, V> {
	
	int size();
	boolean isEmpty();
	void add(K key, V val);
	V remove(K key);
	void set(K key, V val);
	V get(K key);

}
