package algo.Ch07_binarytree_recursive;

/**
 * @author Daffupman
 * @description leetcode: 112 Path Sum
 * @date 2019/8/4 17:56
 */
public class Example03_id112 {

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

	public boolean hasPathSum(TreeNode root, int sum) {
		if ( root == null ) {
			return false;
		}

		if ( root.left == null && root.right == null ) {
			return root.val == sum;
		}

//		if ( hasPathSum( root.left, sum - root.val) ) {
//			return true;
//		}
//		if( hasPathSum( root.right, sum - root.val ) ) {
//			return true;
//		}
//		return false;

		return hasPathSum( root.left, sum - root.val ) ||
				hasPathSum( root.right, sum - root.val );
	}

}
