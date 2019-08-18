package algo.Ch06_stack_queue;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Daffupman
 * @description leetcode: 102 Binary Tree Level Order Traversal
 * @date 2019/8/3 21:22
 */
public class Example03_id102 {

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

	//非递归
	public List<List<Integer>> levelOrderNR(TreeNode root ) {
		List<List<Integer>> res = new ArrayList<>();
		if( root == null)   return res;

		//使用LinkedList的实现队列
		//Pair对存储着树节点和其所在的层数（根节点为0）
		Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
		queue.add(new Pair<>(root, 0));

		while( !queue.isEmpty() ) {
			Pair<TreeNode, Integer> front = queue.remove();
			TreeNode node = front.getKey();
			Integer level = front.getValue();
			if( level == res.size() ) {
				// 生成一个List集合，存储这一层的所有数据
				res.add(new ArrayList<>());
			}
			assert level < res.size();

			//加入结果集
			res.get(level).add(node.val);

			//将左右孩子分别加入队列中
			if( node.left != null ) {
				queue.add(new Pair<>(node.left, level + 1));
			}
			if( node.right != null) {
				queue.add(new Pair<>(node.right, level + 1));
			}
		}

		return res;
	}

	// 递归
	public List<List<Integer>> levelOrder(TreeNode root ) {
		List<List<Integer>> res = new ArrayList<>();
		levelOrderHelper(root, 0, res);
		return res;
	}

	private void levelOrderHelper(TreeNode node, int level, List<List<Integer>> res) {
		if(node == null) {
			return;
		}

		if(res.size() == level) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(node.val);
			res.add(list);
		}else{
			res.get(level).add(node.val);
		}

		levelOrderHelper(node.left, level + 1, res);
		levelOrderHelper(node.right, level + 1, res);
	}

}
