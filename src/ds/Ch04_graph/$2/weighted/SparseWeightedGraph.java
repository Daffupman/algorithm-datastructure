package ds.Ch04_graph.$2.weighted;

import java.util.Vector;

/**
 * @author Daffupman
 * @description 稀疏图，使用邻接表表示
 * @date 2019/7/18 20:06
 */
public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

	private int n;              //节点数
	private int m;              //边数
	private boolean directed;   //是否是有向图
	private Vector<Edge<Weight>>[] g;//图的具体数据

	//构造函数
	public SparseWeightedGraph(int n, boolean directed) {
		assert n >= 0;
		this.n = n;
		this.m = 0;
		this.directed = directed;
		g = (Vector<Edge<Weight>>[]) new Vector[n];
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
	public void addEdge(Edge e) {
		assert e.v() >= 0 && e.v() < n;
		assert e.w() >= 0 && e.w() < n;

		// 注意, 由于在邻接表的情况, 查找是否有重边需要遍历整个链表
		// 我们的程序允许重边的出现

		g[e.v()].add(new Edge(e));
		if(e.v() != e.w() && !directed) {
			//v != w处理自环边
			g[e.w()].add(new Edge(e.w(),e.v(),e.wt()));
		}

		m ++;
	}

	public boolean hasEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;

		for (int i = 0; i < g[v].size(); i++) {
			if(g[v].elementAt(i).other(v) == w) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void show() {
		for( int i = 0 ; i < n ; i ++ ){
			System.out.print("vertex " + i + ":\t");
			for( int j = 0 ; j < g[i].size() ; j ++ )
				System.out.print(g[i].elementAt(j) + "\t");
			System.out.println();
		}
	}

	//返回图中一个顶点的所有邻边，由于java的引用机制，返回一个Vector不会带来额外的开销
	public Iterable<Edge<Weight>> adj(int v) {
		assert v >= 0 && v < n;
		return g[v];
	}

}
