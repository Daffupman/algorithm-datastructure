package algo.Ch07_binarytree_recursive;

/**
 * @author Daffupman
 * @description leetcode: 226 Invert Binary Tree
 * @date 2019/8/4 15:59
 */
public class Example02_226 {

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode ( int x ) {
			val = x;
			left = null;
			right = null;
		}
	}

	public TreeNode invertTree(TreeNode root) {

		if( root == null)   return null;

		invertTree(root.left);
		invertTree(root.right);
		swap(root, root.left, root.right);

		return root;
	}

	private void swap(TreeNode root, TreeNode left, TreeNode right) {
		root.left = right;
		root.right = left;
	}

}
