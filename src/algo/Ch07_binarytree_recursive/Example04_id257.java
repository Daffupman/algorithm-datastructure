package algo.Ch07_binarytree_recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 257 Binary Tree Paths
 * @date 2019/8/4 19:11
 */
public class Example04_id257 {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode ( int x ) {
			val = x;
			left = null;
			right = null;
		}
	}

	public static List<String> binaryTreePaths(TreeNode root) {
		ArrayList<String> res = new ArrayList<String>();

		if(root == null)
			return res;

		if(root.left == null && root.right == null){
			res.add(Integer.toString(root.val));
			return res;
		}

		List<String> leftPaths = binaryTreePaths(root.left);
		for(String s: leftPaths){
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}

		List<String> rightPaths = binaryTreePaths(root.right);
		for(String s: rightPaths) {
			StringBuilder sb = new StringBuilder(Integer.toString(root.val));
			sb.append("->");
			sb.append(s);
			res.add(sb.toString());
		}

		return res;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		TreeNode ll = root.left;
		ll.right = new TreeNode(5);

		List<String> list = binaryTreePaths(root);
		list.forEach(System.out::println);
	}

}
