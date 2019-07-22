package ds.Ch04_graph.$1.unweighted;

import java.util.Stack;
import java.util.Vector;

/**
 * @author Daffupman
 * @description 求graph中两点之间的路径
 * @date 2019/7/21 10:15
 */
public class Path {

	private Graph G;                //图的引用
	private int s;                  //起始点
	private boolean[] visited;      //记录该节点是否被访问
	private int[] from;             //记录路径，from[i]表示查找的路径上i的上一个节点

	public Path(Graph graph, int s) {

		G = graph;
		assert s >= 0 && s < G.V();

		visited = new boolean[G.V()];
		from = new int[G.V()];
		for (int i = 0; i < G.V(); i++) {
			visited[i] = false;
			from[i] = -1;
		}
		this.s = s;

		//寻路算法
		dfs(s);

	}

	//图的深度优先遍历
	private void dfs(int v) {
		visited[v] = true;
		for (int i : G.adj(v)) {
			if(!visited[i]) {
				from[i] = v;
				dfs(i);
			}
		}
	}

	//查询点s点到w点是否有路径
	private boolean hasPath(int w) {
		assert w > 0 && w < G.V();
		return visited[w];
	}

	//查询s点到w点的路径，存放在res中
	private Vector<Integer> path(int w) {
		assert hasPath(w);

		Stack<Integer> s = new Stack<>();
		int p = w;
		while(p != -1) {
			s.push(p);
			p = from[p];
		}

		Vector<Integer> res = new Vector<>();
		while(!s.empty()) {
			res.add(s.pop());
		}

		return res;
	}

	//打印从s点到w点的路径
	public void showPath(int w) {
		assert hasPath(w);

		Vector<Integer> vec = path(w);
		for (int i = 0; i < vec.size(); i++) {
			System.out.print(vec.elementAt(i));
			if(i == vec.size()-1) {
				System.out.println();
			} else {
				System.out.print(" -> ");
			}
		}
	}
}
