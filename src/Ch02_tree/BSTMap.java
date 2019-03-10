package Ch02_tree;

import java.util.ArrayList;

/**
 * @program: data-structure
 * @description: 基于BST的map
 * @author: Daffupman
 * @create: 2019-03-10 08:46
 **/
public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {

	//存储键值对的节点
	private class Node {

		public K key;
		public V value;
		public Node left, right;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}

	}

	private Node root;
	private int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	@Override
	public void add(K key, V value) {
		root= add(root, key, value);
	}

	private Node add(Node node, K key, V value) {
		if(node == null) {
			size ++;
			return new Node(key, value);
		}

		if(key.compareTo(node.key) < 0){
			node.left = add(node.left, key, value);
		} else if(key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, value);
		} else {
			node.value = value;
		}

		return node;
	}

	@Override
	public V remove(K key) {

		Node node = getNode(root, key);

		root = remove(root, key);
		return null;
	}

	private Node remove(Node node, K key) {
		if(node == null) {
			return null;
		}

		if(key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if(key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			return node;
		} else {
			//树中存在key
			//该节点只有右子树
			if(node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			//只有左子树
			if(node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			}
			//左右子树都有
			Node successor = getMin(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;

			node.left = node.right = null;

			return successor;
		}
	}

	private Node getMin(Node node) {
		if(node.left == null) {
			return node;
		}
		return getMin(node.left);
	}

	//删除以node为根的二分搜索树中的最小节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {

		if(node.left == null) {
			//找到最小值得节点
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	@Override
	public boolean contains(K key) {
		return getNode(root, key) != null ;
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	//返回以node为根的BST中，key所在的节点
	private Node getNode(Node node, K key) {

		if(node == null) {
			return null;
		}

		if(key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else if(key.compareTo(node.key) >0) {
			return getNode(node.right, key);
		} else {
			return node;
		}

	}

	@Override
	public void set(K key, V value) {
		Node node = getNode(root, key);
		if(node == null) {
			throw new IllegalArgumentException(key + "doesn't exist!");
		}
		node.value = value;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	public static void main(String[] args) {
		System.out.println("Pride and Prejudice");

		ArrayList<String> words1 = new ArrayList<>();
		String filename = "E:\\workspace\\idea\\data-structure\\src\\pride-and-prejudice.txt";
		if (FileOperation.readFile(filename, words1)) {
			System.out.println("Total words: " + words1.size());

			BSTMap<String , Integer> map = new BSTMap<>();
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
