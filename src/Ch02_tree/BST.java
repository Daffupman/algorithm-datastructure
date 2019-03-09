package Ch02_tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * @program: data-structure
 * @description: 二分搜索树
 * @author: Daffupman
 * @create: 2019-03-08 19:55
 **/
public class BST<E extends Comparable<E>> {

	//定义树的节点
	private class Node {
		public E e;
		public Node left, right;

		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
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

	//添加不重复的元素e
	public void add(E e) {

		if(root == null) {
			root = new Node(e);
			size ++;
		} else {
			add(root, e);
		}

	}

//	//向以node为根的二分搜索树插入元素E，递归算法
//	//代码臃肿
//	private void add(Node node, E e) {
//
//		if(e.equals(node.e)) {
//			return;
//		} else if (e.compareTo(node.e) < 0 && node.left == null) {
//			node.left = new Node(e);
//			size ++;
//			return;
//		} else if (e.compareTo(node.e) > 0 && node.right == null) {
//			node.right = new Node(e);
//			size ++;
//			return;
//		}
//
//		if(e.compareTo(node.e) < 0) {
//			add(node.left, e);
//		} else {
//			add(node.right, e);
//		}
//
//	}

	//向以node为根的二分搜索树插入元素E，递归算法
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

	//返回元素是否在该树里
	public boolean contains(E e) {
		return contains(root, e);
	}

	//树中是否包含元素e，递归算法
	private boolean contains(Node node, E e) {

		if(node == null) {
			return false;
		}
		if(e.compareTo(node.e) == 0) {
			return true;
		} else if(e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		} else {
			return contains(node.right, e);
		}

	}

	//BST的先序遍历
	public void preOrder() {
		preOrder(root);
	}

	//先序遍历以node为根的BST，递归
	private void preOrder(Node node) {
		if(node == null) {
			return;
		}
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}

	//先序遍历以node为根的BST，非递归（使用栈）
	public void preOrderNR() {
		Stack<Node> stack = new Stack<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			Node curr = stack.pop();
			System.out.println(curr.e);

			if(curr.right != null) {
				stack.push(curr.right);
			}
			if(curr.left != null) {
				stack.push(curr.left);
			}
		}
	}

	//BST的中序遍历
	public void inOrder() {
		inOrder(root);
	}

	//中序遍历以node为根的BST，递归
	private void inOrder(Node node) {
		if(node == null) {
			return;
		}

		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}

	//BST的中序遍历
	public void postOrder() {
		postOrder(root);
	}

	//中序遍历以node为根的BST，递归
	private void postOrder(Node node) {
		if(node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}

	//BST的层序遍历，借助queue
	public void levelOrder() {
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			Node curr = q.remove();
			System.out.println(curr.e);

			if(curr.left != null) {
				q.add(curr.left);
			}
			if(curr.right != null) {
				q.add(curr.right);
			}
		}
	}

	//寻找BST中的最小值
	public E getMin() {
		if(size == 0) {
			throw new IllegalArgumentException("BST is empty");
		}

		return getMin(root).e;
	}

	//返回以node为根的二分搜索树的最小值所在的节点
	private Node getMin(Node node) {
		if(node.left == null) {
			return node;
		}
		return getMin(node.left);
	}

	//寻找BST中的最大值
	public E getMax() {
		return getMax(root).e;
	}

	//返回以node为根的二分搜索树的最大值所在的节点
	private Node getMax(Node node) {
		if(node.right == null) {
			return node;
		}
		return getMax(node.right);
	}

	//从BST中删除最小值所在节点，返回最小值
	public E removeMin() {
		E ret = getMin();
		root = removeMin(root);
		return ret;
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

	//从BST中删除最大值所在节点，返回最大值
	public E removeMax() {
		E ret = getMax();
		root = removeMax(root);
		return ret;
	}

	private Node removeMax(Node node) {
		if(node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size --;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}

	//从BST中删除元素为e的节点
	public void remove(E e) {
		root = remove(root ,e);
	}

	private Node remove(Node node, E e) {
		if(node == null) {
			return null;
		}

		if(e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		} else if (e.compareTo(node.e) > 0){
			node.right = remove(node.right, e);
			return node;
		} else {
			//找到要删除的目标节点
			if(node.left == null) {
				//目标节点的没有左子树
				Node rightNode = node.right;
				node.right = null;
				size --;
				return rightNode;
			}
			if(node.right == null) {
				//目标节点没有右子树
				Node leftNode = node.left;
				node.left = null;
				size --;
				return leftNode;
			}
			//目标节点左右子树均不为空
			//寻找比目标节点大的最小值，接替其位置
			Node successor = getMin(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;

			//销毁旧node的左右节点
			node.left = node.right = null;
			//这里不需要size--：在removeMin操作的时候，执行了一次size--，
			//但是这一次的操作是不对的，因为我们已经使用successor承接了min节点
			//不是要将它删除，按照逻辑应该是size++，但在最后删除旧节点的时候，还是要size--，
			//这样下来就抵消了。
			return successor;
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		generateBSTString(root, 0, res);
		return res.toString();
	}

	//生成以node为根节点，深度为depth的描述二叉树的字符串
	private void generateBSTString(Node node, int depth, StringBuilder res) {
		if(node == null) {
			res.append(generateDepthString(depth)+"null\n");
			return;
		}
		res.append(generateDepthString(depth)+node.e+"\n");
		generateBSTString(node.left, depth+1, res);
		generateBSTString(node.right, depth + 1, res);
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			res.append("--");
		}
		return res.toString();
	}

	public static void main(String[] args) {
		BST<Integer> bst = new BST<>();
		Random random = new Random();

		int n = 10;

		for (int i = 0; i < n; i++) {
			bst.add(random.nextInt(100));
		}
		System.out.println(bst);

//		System.out.println(bst.getMax());
//		System.out.println(bst.getMin());
//		bst.preOrder();
//		System.out.println("-------------");
//		bst.preOrderNR();
//		System.out.println(bst);
//		System.out.println("-------------");
		bst.inOrder();
//		System.out.println("-------------");
//		bst.postOrder();
//		System.out.println(bst);
//		bst.levelOrder();


	}

}
