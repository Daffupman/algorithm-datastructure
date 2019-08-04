package algo.Ch07_binarytree_recursive;

/**
 * @author Daffupman
 * @description leetcode: 104 Maximum Depth of Binary Tree
 * @date 2019/8/4 15:48
 */
public class Example01_id104 {

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

	public int maxDepth(TreeNode root) {
		if(root == null) {
			return 0;
		}

		int leftMaxDepth = maxDepth(root.left);
		int rightMaxDepth = maxDepth(root.right);
		return Math.max( leftMaxDepth, rightMaxDepth ) + 1;
	}

}
