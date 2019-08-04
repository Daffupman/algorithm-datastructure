package algo.Ch07_binarytree_recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 257 Binary Tree Paths
 * @date 2019/8/4 19:11
 */
public class Example04_id257 {

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

	public List<String> binaryTreePaths(TreeNode root) {
		List<String> res = new ArrayList<>();

		if( root == null) {
			return res;
		}

		if( root.left == null && root.right == null ) {
			res.add( root.val+"" );
		}

		List<String> leftRes = binaryTreePaths( root.left );
		res.addAll(leftRes);

		List<String> rightRes = binaryTreePaths( root.right );
		res.addAll(rightRes);

		return res;
	}

}
