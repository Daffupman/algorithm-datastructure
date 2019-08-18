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

	/**
	 * 另一种非递归的写法，对经典写法改造：
	 * 使用栈模拟系统栈，把原本应该由系统将当前程序的执行状态压入系统栈中
	 * 转变成使用自己创建的栈保存系统需要保存的程序状态。
	 *
	 * 所以需要封装一个Command类来保存节点及其状态（描述信息）
	 * 其中描述信息s， 其取值为go或print，代表对当前节点的指令描述
	 * 对于每一个节点的初始指令描述为go，当从栈中取出之后，指令变更为print
	 *
	 * 大体思路与经典的一致：
	 * 先将root压栈，接着一直监听stack的是否为空。
	 * 如果不为空，则
	 * 		- 判断当前节点的状态
	 * 			- go：前寻指令
	 * 			- print：访问指令
	 * 栈空，结束遍历的过程
	 */
	public ArrayList<Integer> preorderTraversal2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<>();
		if( root == null ) {
			return res;
		}

		Stack<Command> stack = new Stack<>();
		stack.push(new Command("go", root));
		while( !stack.isEmpty() ) {
			Command cmd = stack.pop();

			if( "print".equals( cmd.s ) ) {
				// 专注访问的分支
				res.add( cmd.node.val );
			} else {
				// 专注前寻的分支
				assert "go".equals(cmd.s);

				//===核心过程,改动go和print的入栈顺序即可完成先、中、后序遍历====
				if( cmd.node.right != null ) {
					stack.push( new Command("go", cmd.node.right) );
				}
				if( cmd.node.left != null ) {
					stack.push( new Command( "go", cmd.node.left ) );
				}
				stack.push( new Command( "print", cmd.node ) );
				//======================================================
			}
		}
		return res;
	}


}
