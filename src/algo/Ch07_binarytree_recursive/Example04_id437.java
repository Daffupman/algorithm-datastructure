package algo.Ch07_binarytree_recursive;

/**
 * @author Daffupman
 * @description leetcode: 437 Path Sum III
 * @date 2019/8/4 19:44
 */
public class Example04_id437 {

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

	//在以node为根的二叉树中，返回这样的路径个数
	public int pathSum(TreeNode root, int sum) {
		if( root == null )  return 0;

		//以当前节点为根，和sum的情况
		int res = findPath( root, sum );
		//再分别考虑左右孩子节点的情况
		res += pathSum( root.left, sum );
		res += pathSum( root.right, sum );

		return res;
	}

	//在以node为根节点中的二叉树中，寻找包含node的路径，和为sum
	//返回这样的路径个数
	private int findPath(TreeNode node, int sum) {
		if( node == null ) {
			return 0;
		}

		int res = 0;
		if( node.val == sum ) {
			res += 1;
		}

		res += findPath( node.left, sum - node.val );
		res += findPath( node.right, sum - node.val );

		return res;
	}

}
