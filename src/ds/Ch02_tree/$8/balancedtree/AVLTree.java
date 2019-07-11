package ds.Ch02_tree.$8.balancedtree;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @description AVLTree，一种可以维护自平衡的树
 * @author Daffupman
 * @date 2019-04-15 15:59
 */
public class AVLTree<K extends Comparable<K>, V> {

	//AVL树中的节点定义
	private class Node {
		private K key;
		private V val;
		private Node left,right;
		private int height;			//当前节点所处的高度值，可用于计算平衡因子
		
		Node(K key, V val) {
			this.key = key;
			this.val = val;
			left = right = null;
			height = 1;
		}
		
		@Override
		public String toString() {
			return key.toString()+"="+val.toString();
		}
	}
	
	private Node root;
	private int size;
	
	public AVLTree() {
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
	
	//中根遍历BST的结果是有序的
	private void inOrder(Node node, ArrayList<K> keys) {
		if(node == null)	return;
		
		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}
	
	//返回该树是否是平衡的
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	//判断以node为根的二叉树是否是一棵平衡二叉树，递归方式
	private boolean isBalanced(Node node) {
		if(node == null)	return true;
		
		int balanceFactor = getBalanceFactor(node);
		if(Math.abs(balanceFactor) > 1) {
			return false;
		}
		
		return isBalanced(node.left) && isBalanced(node.right);
	}
	
	//返回以node为根的树的平衡因子
	private int getBalanceFactor(Node node) {
		if(node == null)	return 0;
		
		return getHeight(node.left) - getHeight(node.right);
	}
	
	//返回node节点的高度值
	private int getHeight(Node node) {
		if(node == null)	return 0;
		
		return node.height;
	}

	/*
	 * ----------------------------------------
	 * 右旋：对节点y进行向右旋转操作，返回旋转后新的根节点x
	 * 			y				x
	 * 		   / \			   / \
	 * 		  x	 T1    --->   z   y
	 * 		 / \			     / \
	 * 		z	T2				T2  T1
	 * ----------------------------------------
	 * 
	 */
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;
		
		//向右旋转过程
		x.right = y;
		y.left = T2;
		
		//必须先更新y节点的height
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		
		return x;
	}
	
	/*
	 * ----------------------------------------
	 * 左旋：对节点y进行向左旋转操作，返回旋转后新的根节点x
	 * 		y				x
	 * 	   / \			   / \
	 * 	  T1  x	  	--->  y   z
	 * 		 / \		 / \  
	 * 		T2	z		T1 T2
	 * ----------------------------------------
	 */
	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T2 = x.left;
		
		// y以x为轴进行左旋转
		x.left = y;
		y.right = T2;
		
		//必须先更新y节点的height
		y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
		x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
		
		return x;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void add(K key, V val) {
		root = add(root, key, val);
	}
	
	private Node add(Node node, K key, V val) {
		if(node == null) {
			size ++;
			return new Node(key, val);
		}
		if(key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, val);
		} else if(key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, val);
		} else {
			//树中已经有该节点
			node.val = val;
		}
		
		//更新height值
		node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

		//计算平衡因子
		int balanceFactor = getBalanceFactor(node);		
		
		//平衡维护
		if(balanceFactor > 1 && getBalanceFactor(node.left) >=0 ) {
			//LL
			return rightRotate(node);
		} else if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			//LR
			node.left = leftRotate(node.left);
			return rightRotate(node);
		} else if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
			return leftRotate(node);
		} else if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		
		return node;
	}
	
	public V remove(K key) {
		Node node = getNode(root, key);
		if(node != null) {
			root = remove(root, key);
			return node.val;
		}
		return null;
	}
	
	private Node remove(Node node, K key) {
		if(node == null)	return null;
		
		//存储要返回回去的节点，在向上返回的时候需要先维护平衡
		Node retNode = null;
		if(key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			retNode = node;
		} else if(key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			retNode = node;
		} else {
			//找到要删除的目标节点
			if(node.right == null) {
				//目标节点的没有左子树
				Node leftNode = node.left;
				node.left = null;
				size --;
				retNode = leftNode;
			} else if(node.left == null) {
				//目标节点没有右子树
				Node rightNode = node.right;
				node.right = null;
				size --;
				retNode = rightNode;
			} else {
				//目标节点左右子树均不为空
                //寻找比目标节点大的最小值，接替其位置
				Node successor = minimum(node.right);
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				//销毁旧node的左右节点
				node.left = node.right = null;
				//这里不需要size--：在removeMin操作的时候，执行了一次size--，
                //但是这一次的操作是不对的，因为我们已经使用successor承接了min节点
                //不是要将它删除，按照逻辑应该是size++，但在最后删除旧节点的时候，还是要size--，
                //这样下来就抵消了。
				retNode = successor;
			}
		}
		
		//【重要】:否则要报空指针异常
		if(retNode == null) {
			//删除节点后，retNode为空
			return null;
		}
		
		
		//更新height
		retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
		
		//计算平衡因子
		int balanceFactor = getBalanceFactor(retNode);
		
		//平衡维护
		if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
			//LL
			return rightRotate(retNode);
		} else if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
			//LR
			retNode.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		} else if(balanceFactor < -1 && getBalanceFactor(retNode.left) <= 0) {
			//RR
			return leftRotate(retNode);
		} else if(balanceFactor < -1 && getBalanceFactor(retNode.left) > 0) {
			//RL
			retNode.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		
		return retNode;
		
	}
	
	//返回以node为根的二分搜索树的最小值所在的节点
	private Node minimum(Node node) {
		if(node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	//在以node为根的树中获取键为key的节点
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
	
	//在该树中获取键为key对应的值
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.val;
	}
	
	//修改键为key对应的值为val
	public void set(K key, V val) {
		Node node = getNode(root, key);
		if(node != null) {
			node.val = val;
		}
	}
	
	//返回该树中是否存在节点的键为key
	public boolean contains(K key) {
		return contains(root, key);
	}
	
	//在以node为根的树中，返回该树中是否存在节点的键为key，递归
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
		AVLTree<Integer, Character> tree = new AVLTree<>();
		for (int i = 0; i < 5; i++) {
			tree.add(i+1, (char)('a'+i));
		}
		
		tree.remove(2);
		System.out.println(tree.contains(1));
		
		System.out.println("debug over!");
	}

}
