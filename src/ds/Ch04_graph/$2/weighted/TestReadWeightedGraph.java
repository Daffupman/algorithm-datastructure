package ds.Ch04_graph.$2.weighted;

/**
 * @author Daffupman
 * @description 测试通过文件读取带权图
 * @date 2019/7/21 15:07
 */
public class TestReadWeightedGraph {

	public static void main(String[] args) {

		// 使用两种图的存储方式读取testG1.txt文件
		String filename = "testWG1.txt";
		SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<Double>(8, false);
		ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename);
		System.out.println("test G1 in Sparse Weighted Graph:");
		g1.show();

		System.out.println();

		DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<Double>(8, false);
		ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2 , filename );
		System.out.println("test G1 in Dense Graph:");
		g2.show();

		System.out.println();
	}

}
