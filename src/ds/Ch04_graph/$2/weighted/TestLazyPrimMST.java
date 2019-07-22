package ds.Ch04_graph.$2.weighted;

import java.util.Vector;

/**
 * @author Daffupman
 * @description
 * @date 2019/7/22 19:32
 */
public class TestLazyPrimMST {

	public static void main(String[] args) {

		String filename = "testWG1.txt";
		int V = 8;

		SparseWeightedGraph<Double> g = new SparseWeightedGraph<Double>(V, false);
		ReadWeightedGraph readGraph = new ReadWeightedGraph(g, filename);

		// Test Lazy Prim MST
		System.out.println("Test Lazy Prim MST:");
		LazyPrimMST<Double> lazyPrimMST = new LazyPrimMST<Double>(g);
		Vector<Edge<Double>> mst = lazyPrimMST.mstEdges();
		for( int i = 0 ; i < mst.size() ; i ++ )
			System.out.println(mst.elementAt(i));
		System.out.println("The MST weight is: " + lazyPrimMST.result());

		System.out.println();
	}

}
