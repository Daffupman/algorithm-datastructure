package ds.Ch04_graph.$2.weighted;

/**
 * @author Daffupman
 * @description 图的顶级接口
 * @date 2019/7/18 21:00
 */
public interface WeightedGraph<Weight extends Number & Comparable> {

	int V();
	int E();
	void addEdge(Edge<Weight> e);
	boolean hasEdge(int v, int w);
	void show();
	Iterable<Edge<Weight>> adj(int v);

}
