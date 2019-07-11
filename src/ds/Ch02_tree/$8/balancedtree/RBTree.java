package ds.Ch02_tree.$8.balancedtree;

import java.util.ArrayList;

/**
 * @description 红黑树
 * 				只有增加操作，不涉及删除操作，过于复杂
 * @author Daffupman
 * @date 2019-04-16 14:55
 */
public class RBTree<K extends Comparable<K>, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node {
		private K key;
		private V val;
		private Node left,right;
		private boolean color;			//标记节点的颜色
		
		Node(K key, V val) {
			this.key = key;
			this.val = val;
			left = right = null;
			color = RED;		//新生成的节点默认是红色的,就是需要合并的节点
		}
		
		@Override
		public String toString() {
			return key.toString()+"="+val.toString();
		}
	}
	
	private Node root;
	private int size;
	
	public RBTree() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	//返回该树是否是一棵BST
	public boolean isBST() {
		ArrayList<K> keys = new ArrayList<>();
		inOrder(root, keys);
		for (int i = 1; i < keys.size(); i++) {
			if(keys.get(i-1).compareTo(keys.get(i)) > 0) {
				return false;
			}
		}
		return true;
	}
	
	//中根遍历BST的结果是有序de
	private void inOrder(Node node, ArrayList<K> keys) {
		if(node == null)	return;
		
		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}
	
	/*
	 * 右旋
	 * 			y				x
	 * 		   / \			   / \
	 * 		  x	 T1 	--->  z   y
	 * 		 / \			     / \
	 * 		z	T2				T2  T1
	 */
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		
		x.right = y;
		y.left = T2;

		x.color = y.color;
		y.color = RED;
		
		return x;
	}
	
	/*
	 * --------------------------
	 * 左旋
	 * 		y				x
	 * 	   / \			   / \
	 * 	  T1  x	  	--->  y   T3
	 * 		 / \		 / \  
	 * 		T2	T3		T1 T2
	 * --------------------------
	 */
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;
		
		x.left = y;
		y.right = T2;
		
		x.color = y.color;
		y.color = RED;
		
		return x;
	}
	// 颜色翻转
	private void flipColors(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color= BLACK;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	// 向红黑树中添加新的元素(key, value)
	public void add(K key, V val) {
		root = add(root, key, val);
		// 最终根节点为黑色节点
		root.color = BLACK;
	}
	
	// 向以node为根的红黑树中插入元素(key, value)，递归算法
    // 返回插入新节点后红黑树的根
	private Node add(Node node, K key, V val) {
		if(node == null) {
			size ++;
			return new Node(key, val);	// 默认插入红色节点
		}
		if(key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, val);
		} else if(key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, val);
		} else {
			//树中已经有该节点
			node.val = val;
		}
		
		//平衡维护:分三步
		//左黑右红:左旋
		if(!isRed(node.left) && isRed(node.right)) {
			node = leftRotate(node);
		}
		//左左子红：右旋
		if(isRed(node.left) && isRed(node.left.left)) {
			node = rightRotate(node);
		}
		//左右均红：翻转
		if(isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		
		return node;
	}
	
	// 判断节点node的颜色
	private boolean isRed(Node node) {
		if(node == null) 	return BLACK;
		return node.color;
	}
	
	// 返回以node为根的红黑树的最小值所在的节点
	private Node minimum(Node node) {
		if(node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	// 返回以node为根节点的红黑树中，key所在的节点
	private Node getNode(Node node, K key) {
		if(node == null)	return null;
		if(key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else if(key.compareTo(node.key) > 0) {
			return getNode(node.right, key);
		} else {
			return node;
		}
	}
	
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.val;
	}
	
	public void set(K key, V val) {
		Node node = getNode(root, key);
		if(node != null) {
			node.val = val;
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
		RBTree<Integer, Character> tree = new RBTree<>();
		for (int i = 0; i < 5; i++) {
			tree.add(i+1, (char)('a'+i));
		}
		
		System.out.println(tree.contains(1));
		
		System.out.println("debug over!");
	}

}
