package ds.Ch02_tree.$1.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @description 二分搜索树
 * @author Daffupman
 * @date 2019-03-08 19:55
 */
public class BST<E extends Comparable<E>> {

	//BST的节点
	private class Node {
		private E e;
		private Node left, right;
		
		public Node(E e) {
			this.e = e;
			left = right = null;
		}
		
		@Override
		public String toString() {
			return e.toString();
		}
	}
	
	private Node root;
	private int size;
	
	public BST() {
		root = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	//--------增--------
	////添加不重复的元素e
	public void add(E e) {
		root = add(root, e);
	}
	
	//向以node为根的二分搜索树插入元素e，递归算法
	private Node add(Node node, E e) {
		if(node == null) {
			size ++;
			return new Node(e);
		}
		if(e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if(e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}
		return node;
	}
	
	//--------删--------
	public void remove(E e) {
		if(root == null) {
			throw new RuntimeException("Empty BST！");
		} else {
			root = remove(root, e);
		}
	}
	
	private Node remove(Node node, E e) {
		if(node == null) {
			return null;
		}
		if(e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		} else if(e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		} else {
			if(node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			} else if(node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			} else {
				Node successor = minimum(node.right);
				successor.right = removeMin(node.right);
				successor.left = node.left;
				node.left = node.right = null;
				return successor;
			}
		}
	}
	
	private Node removeMin(Node node) {
		if(node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size --;
			return rightNode;
		}
		return removeMin(node.left);
	}

	//--------查--------
	public E minimum() {
		if(root == null) {
			throw new RuntimeException("Empty BST！");
		} else {
			return minimum(root).e;
		}
	}
	
	private Node minimum(Node node) {
		if(node.left == null)	return node;
		return minimum(node.left);
	}
	
	public E maximum() {
		if(root == null) {
			throw new RuntimeException("Empty BST！");
		} else {
			return maximum(root).e;
		}
	}
	
	private Node maximum(Node node) {
		if(node.right == null)	return node;
		return minimum(node.right);
	}
	
	//返回元素是否在该树里
	public boolean contains(E e) {
		return contains(root, e);
	}
	
	//树中是否包含元素e，递归算法
	private boolean contains(Node node, E e) {
		if(node == null)	return false;
		if(e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		} else if(e.compareTo(node.e) > 0) {
			return contains(node.right, e);
		} else {
			return true;
		}
	}
	
	//--------遍历--------
	//深度优先遍历
	//先根遍历
	public void preOrder() {
		if(root == null) {
			System.out.println("Empty BST!");
		} else {
			preOrder(root);
		}
	}
	
	//先根遍历(递归)
	private void preOrder(Node node) {
		if(node == null) {
			return;
		}
		System.out.print(node.e+" ");
		preOrder(node.left);
		preOrder(node.right);
	}
	
	//先根遍历(非递归)
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.add(root);
		while(!stack.isEmpty()) {
			Node curr = stack.pop();
			System.out.print(curr.e+" ");
			
			if(curr.right != null)	stack.push(curr.right);
			if(curr.left != null)	stack.push(curr.left);
		}
	}
	
	//中根遍历
	public void inOrder() {
		if(root == null) {
			System.out.println("Empty BST!");
		} else {
			inOrder(root);
		}
	}
	
	//中根遍历（递归）
	private void inOrder(Node node) {
		if(node == null)	return;
		
		inOrder(node.left);
		System.out.print(node.e+" ");
		inOrder(node.right);
	}
	
	//中根遍历（递归）
	public void inOrderNR() {
		Stack<Node> stack = new Stack<>();
		Node curr = root;
		while(curr != null || !stack.isEmpty()) {
			if(curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				System.out.print(curr.e+" ");
				curr = curr.right;
			}
		}
	}
	
	//后根遍历
	public void postOrder() {
		if(root == null) {
			System.out.println("Empty BST!");
		} else {
			postOrder(root);
		}
	}
	
	//后根遍历（递归）
	private void postOrder(Node node) {
		if(node == null)	return;

		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.e+" ");
	}
	
	//后根遍历（非递归）
	public void postOrderNR() {
		Stack<Node> traStack = new Stack<>();
		Stack<Node> resStack = new Stack<>();
		traStack.push(root);
		while(!traStack.isEmpty()) {
			Node curr = traStack.pop();
			resStack.push(curr);
			
			if(curr.left != null)	traStack.push(curr.left);
			if(curr.right != null)	traStack.push(curr.right);
		}
		while(!resStack.isEmpty()) {
			System.out.print(resStack.pop()+" ");
		}
	}
	
	//广度优先遍历，借助queue
	public void levelOrder() {
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		System.out.println("-------层序遍历-------");
		while(!queue.isEmpty()) {
			Node curr = queue.remove();
			System.out.print(curr.e+" ");
			
			if(curr.left != null)	queue.add(curr.left);
			if(curr.right != null)	queue.add(curr.right);
		}
		System.out.println("\n--------------------");
	} 
	
	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		bst.add(2);
		for (int i = 0; i < 5; i++) {
			bst.add(i);
		}
		bst.preOrder();
		bst.inOrder();
		bst.postOrder();
		bst.levelOrder();
		
	}
}
