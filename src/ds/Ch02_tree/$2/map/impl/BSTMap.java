package ds.Ch02_tree.$2.map.impl;

import ds.Ch02_tree.$2.map.Map;

/**
 * @description 基于BST的map
 * @author Daffupman
 * @date 2019-07-10 08:26
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

	//存储键值对的节点
	private class Node {
		private K key;
		private V val;
		private Node left, right;
		
		Node(K key, V val) {
			this.key = key;
			this.val = val;
			left = right = null;
		}
		
		@Override
		public String toString() {
			return key.toString()+"="+val.toString();
		}
	}
	
	private Node root;
	private int size;
	
	public BSTMap() {
		root = null;
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

	//向树中添加键值对key-val
	@Override
	public void add(K key, V val) {
		root = add(root, key, val);
	}

	//向以node为根的树中添加键值对key-val，递归
	private Node add(Node node, K key, V val) {
		if(node == null) {
			size ++;
			return new Node(key, val);
		}
		if(key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, val);
			return node;
		} else if(key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, val);
			return node;
		}
		return node;
	}
	
	//在树中移除键为key的节点
	@Override
	public V remove(K key) {
		V ret = get(key);
		if(ret != null) {
			root = remove(root, key);
		}
		return ret;
	}
	
	//在以node为根的树中移除键为key的节点
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
			if(node.right == null) {
				//只有左子树
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			} else if(node.left == null) {
				//该节点只有右子树
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			} else {
				//左右子树都有
				Node successor = minimum(node.right);
				successor.right = removeMin(node.right);
				successor.left = node.left;
				node.left = node.right = null;
				return successor;
			}
		}
	}
	
	//返回以node为根的树中的最小值结点
	private Node minimum(Node node) {
		if(node.left == null) {
			return node;
		}
		return minimum(node.left);
	}
	
	//删除以node为根的二分搜索树中的最小节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMin(Node node) {
		if(node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	@Override
	public void set(K key, V val) {
		Node node = getNode(root, key);
		if(node != null) {
			node.val = val;
		}
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null:node.val;
	}
	
	//返回以node为根的BST中，key所在的节点
	private Node getNode(Node node, K key) {
		if(node == null) {
			return null;
		}
		if(key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else if(key.compareTo(node.key) > 0) {
			return getNode(node.right, key);
		} else {
			return node;
		}
	}
	
	public boolean contains(K key) {
		return contains(root, key);
	}
	
	private boolean contains(Node node, K key) {
		if(node == null)	return false;
		if(key.compareTo(node.key) < 0) {
			return contains(node.left, key);
		} else if(key.compareTo(node.key) > 0) {
			return contains(node.right, key);
		} else {
			return true;
		}
	}
	
	public static void main(String[] args) {
		Map<Integer, Character> map = new BSTMap<>();
		for (int i = 0; i < 5; i++) {
			map.add(i+1, (char)(i+'a'));
		}
		System.out.println(map.remove(3));
		System.out.println(map.get(2));
	}

}
