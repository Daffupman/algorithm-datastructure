package ds.Ch04_graph.$1.unweighted;

/**
 * @author Daffupman
 * @description 测试寻路算法
 * @date 2019/7/21 10:33
 */
public class TestPath {

	// 测试寻路算法
	public static void main(String[] args) {

		String filename = "testG.txt";
		SparseGraph g = new SparseGraph(7, false);
		ReadGraph readGraph = new ReadGraph(g, filename);
		g.show();
		System.out.println();

		Path path = new Path(g,0);
		System.out.println("Path from 0 to 6 : ");
		path.showPath(6);
	}

}
