package algo.Ch06_stack_queue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author Daffupman
 * @description leetcode : 144 Binary Tree PreOrder Traversal
 * @date 2019/8/3 20:33
 */
public class Example02_id144 {

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

	//递归
	public ArrayList<Integer> preorderTraversal1(TreeNode node ) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		preorderTraversal(node, arrayList);
		return arrayList;
	}

	private void preorderTraversal(TreeNode node, ArrayList<Integer> arrayList) {
		if(node != null) {
			arrayList.add(node.val);
			preorderTraversal(node.left, arrayList);
			preorderTraversal(node.right, arrayList);
		}
	}

	//非递归
	//将访问以node为根的二叉树的命令动作封装成一个对象
	class Command {
		String s;   //描述动作：print或go
		TreeNode node;
		Command( String s, TreeNode node ) {
			this.s = s;
			this.node = node;
		}
	}

	public ArrayList<Integer> preorderTraversal2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if( root == null ) {
			return res;
		}

		Stack<Command> stack = new Stack<>();
		stack.push(new Command("go", root));
		while( !stack.isEmpty() ) {
			Command cmd = stack.peek();
			stack.pop();

			if( "print".equals( cmd.s ) ) {
				res.add( cmd.node.val );
			} else {
				assert "go".equals(cmd.s);
				//核心过程,改动Cgo安顿print的入栈顺序即可
				if( cmd.node.right != null ) {
					stack.push( new Command("go", cmd.node.right) );
				}
				if( cmd.node.left != null ) {
					stack.push( new Command( "go", cmd.node.left ) );
				}
				stack.push( new Command( "print", cmd.node ) );
			}
		}
		return res;
	}


}
