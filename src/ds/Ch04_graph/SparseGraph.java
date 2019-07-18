package ds.Ch04_graph;

import java.util.Vector;

/**
 * @author Daffupman
 * @description 稀疏图，使用邻接表表示
 * @date 2019/7/18 20:06
 */
public class SparseGraph {

	private int n;              //节点数
	private int m;              //边数
	private boolean directed;   //是否是有向图
	private Vector<Integer>[] g;//图的具体数据

	//构造函数
	public SparseGraph(int n, boolean directed) {
		assert n >= 0;
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = (Vector<Integer>[]) new Vector[n];
		for (int i = 0; i < n; i++) {
			g[i] = new Vector<>();
		}
	}

	public int V() {
		return n;
	}

	public int E() {
		return m;
	}

	//可能会有平行边
	public void addEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;

		g[v].add(w);
		if(v != w && !directed) {
			//v != w处理自环边
			g[w].add(v);
		}

		m ++;
	}

	private boolean hasEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;

		for (int i = 0; i < g[v].size(); i++) {
			if(g[v].elementAt(i) == w) {
				return true;
			}
		}
		return false;
	}

	//返回图中一个顶点的所有邻边，由于java的引用机制，返回一个Vector不会带来额外的开销
	public Iterable<Integer> adj(int v) {
		assert v >= 0 && v < n;
		return g[v];
	}

}
