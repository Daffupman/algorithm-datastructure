package ds.Ch04_graph;

/**
 * @author Daffupman
 * @description 图的顶级接口
 * @date 2019/7/18 21:00
 */
public interface Graph {

	int V();
	int E();
	void addEdge(int v, int w);
	boolean hasEdge(int v, int w);
	void show();
	Iterable<Integer> adj(int v);

}
