package algo.Ch07_binarytree_recursive;

/**
 * @author Daffupman
 * @description leetcode: 235 Lowest Common Ancestor of a Binary
 * @date 2019/8/4 20:01
 */
public class Example05_id235 {

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

	TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

		assert p != null && q != null;

		if( root == null ) {
			return null;
		}

		if( p.val < root.val && q.val < root.val ) {
			//p和q的值都小于root节点的值，root节点切换为root的左孩子
			return lowestCommonAncestor( root.left, p, q );
		}
		if( p.val > root.val && q.val > root.val ) {
			//p和q的值都大于root节点的值， root节点切换为root的右孩子结点
			return lowestCommonAncestor( root.right, p, q );
		}

		//剩下的情况为：p节点小于等于root，q节点大于等于root
		//此时只需返回root节点即可
		return root;

	}

}
