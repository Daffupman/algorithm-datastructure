package ds.Ch04_graph;

/**
 * @author Daffupman
 * @description 测试图的连通分量
 * @date 2019/7/21 9:40
 */
public class TestComponent {

	public static void main(String[] args) {

		// TestG1.txt
		String filename1 = "testG1.txt";
		SparseGraph g1 = new SparseGraph(13, false);
		ReadGraph readGraph1 = new ReadGraph(g1, filename1);
		Components component1 = new Components(g1);
		System.out.println("TestG1.txt, Component Count: " + component1.count());
		System.out.println();

		// TestG2.txt
		String filename2 = "testG2.txt";
		SparseGraph g2 = new SparseGraph(6, false);
		ReadGraph readGraph2 = new ReadGraph(g2, filename2);
		Components component2 = new Components(g2);
		System.out.println("TestG2.txt, Component Count: " + component2.count());
	}

}
