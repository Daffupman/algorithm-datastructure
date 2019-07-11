package ds.Ch02_tree.$2.map;

/**
 * @description 使用链表实现map
 * @author Daffupman
 * @date 2019-03-09 20:55
 */
public class LinkedListMap<K, V> implements Map<K, V> {

	private class Node {
		private K key;
		private V val;
		private Node next;
		
		Node() {
			this(null, null);
		}
		
		Node(K key, V val) {
			this(key, val, null);
		}
		
		Node(K key, V val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return key.toString()+":"+val.toString();
		}
	}
	
	private Node dummyHead;
	private int size;
	
	public LinkedListMap() {
		dummyHead = new Node();
		size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void add(K key, V val) {
		Node node = getNode(key);
		if(node == null) {
//			Node newNode = new Node(key, val);
//			newNode.next = dummyHead.next;
//			dummyHead.next = newNode;
			
			dummyHead.next = new Node(key, val, dummyHead.next);
			size ++;
		} else {
			node.val = val;
		}
	}
	
	private Node getNode(K key) {
		Node curr = dummyHead.next;
		while(curr != null) {
			if(curr.key.equals(key)) {
				return curr;
			}
			curr = curr.next;
		}
		return null;
	}
	
	@Override
	public V remove(K key) {
		Node prev = dummyHead;
		
		while(prev.next != null) {
			if(prev.next.key.equals(key)) {
				break;
			}
			prev = prev.next;
		}
		if(prev.next != null) {
			//非尾部节点
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size --;
			return delNode.val;
		}
		
		return null;
	}

	@Override
	public void set(K key, V val) {
		Node node = getNode(key);
		if(node == null) {
			throw new RuntimeException("Map中没有节点的键为"+key);
		}
		node.val = val;
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);
		return node == null ? null : node.val;
	}

	public static void main(String[] args) {
		Map<Integer, Character> map = new LinkedListMap<>();
		for (int i = 0; i < 5; i++) {
			map.add(i+1, (char)(i+'a'));
		}
		System.out.println(map.remove(3));
		System.out.println(map.get(2));
	}

}
