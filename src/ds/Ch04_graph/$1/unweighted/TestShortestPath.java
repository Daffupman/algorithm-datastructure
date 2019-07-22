package ds.Ch04_graph.$1.unweighted;

/**
 * @author Daffupman
 * @description // 测试无权图最短路径算法
 * @date 2019/7/21 11:26
 */
public class TestShortestPath {

	public static void main(String[] args) {

		String filename = "testG.txt";
		SparseGraph g = new SparseGraph(7, false);
		ReadGraph readGraph = new ReadGraph(g, filename);
		g.show();

		// 比较使用深度优先遍历和广度优先遍历获得路径的不同
		// 广度优先遍历获得的是无权图的最短路径
		Path dfs = new Path(g,0);
		System.out.print("DFS : ");
		dfs.showPath(6);

		ShortestPath bfs = new ShortestPath(g,0);
		System.out.print("BFS : ");
		bfs.showPath(6);

		System.out.println();

		filename = "testG1.txt";
		SparseGraph g2 = new SparseGraph(13, false);
		ReadGraph readGraph2 = new ReadGraph(g2, filename);
		g2.show();

		// 比较使用深度优先遍历和广度优先遍历获得路径的不同
		// 广度优先遍历获得的是无权图的最短路径
		Path dfs2 = new Path(g2,0);
		System.out.print("DFS : ");
		dfs2.showPath(3);

		ShortestPath bfs2 = new ShortestPath(g,0);
		System.out.print("BFS : ");
		bfs.showPath(3);
	}

}
