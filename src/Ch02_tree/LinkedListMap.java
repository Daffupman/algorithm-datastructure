package Ch02_tree;

import Ch01_linear.LinkedList;

import java.util.ArrayList;

/**
 * @program: data-structure
 * @description: 使用链表实现map
 * @author: Daffupman
 * @create: 2019-03-09 20:55
 **/
public class LinkedListMap<K,V> implements Map<K,V> {

	private class Node {

		public K key;
		public V value;
		public Node next;

		public Node(K key, V value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}

		public Node(K key) {
			this(key, null, null);
		}

		public Node() {
			this(null, null, null);
		}

		@Override
		public String toString() {
			return key.toString()+":"+value.toString();
		}
	}

	private Node dummyHead;
	private int size;

	public LinkedListMap() {
		dummyHead = new Node();
		size = 0;
	}

	@Override
	public void add(K key, V value) {

		Node node = getNode(key);
		if(node == null) {
			dummyHead.next = new Node(key, value, dummyHead.next);
			size ++;
		} else {
			node.value = value;
		}

	}

	@Override
	public V remove(K key) {
		Node prev = dummyHead;
		while(prev.next != null) {
			if(prev.next.value.equals(key)) {
				break;
			}
			prev = prev.next;
		}

		if(prev.next != null) {
			Node delNode = prev.next;
			prev.next = delNode.next;
			delNode.next = null;
			size--;
			return delNode.value;
		}
		return null;
	}

	@Override
	public boolean contains(K key) {
		return getNode(key) != null;
	}

	@Override
	public V get(K key) {
		Node node = getNode(key);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K key, V value) {
		Node node = getNode(key);
		if(node == null) {
			throw new IllegalArgumentException("key" + "doesn't exist!");
		}
		node.value = value;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//根据key获取Node
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

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		String filename = "E:\\workspace\\idea\\data-structure\\src\\pride-and-prejudice.txt";
		if (FileOperation.readFile(filename, words1)) {
			System.out.println("Total words: " + words1.size());

			LinkedListMap<String , Integer> map = new LinkedListMap<>();
			for (String word : words1) {
				if(map.contains(word)) {
					map.set(word, map.getSize() + 1);
				} else {
					map.add(word, 1);
				}
			}
			System.out.println("Total different words: " + map.getSize());
		}
	}
}
