package ds.Ch04_graph.$1.unweighted;

import java.util.Vector;

/**
 * @author Daffupman
 * @description 稠密图，使用邻接矩阵表示
 * @date 2019/7/18 19:54
 */
public class DenseGraph implements Graph{

	private int n;              //节点数
	private int m;              //边数
	private boolean directed;   //是否为有向图
	private boolean[][] g;      //图的具体数据

	//生成n*n的邻接矩阵，并指定是否是有向图
	public DenseGraph(int n, boolean directed) {
		assert n > 0;
		this.n = n;
		this.m = 0;             //初始时没有任何边
		this.directed = directed;
		g = new boolean[n][n];  //g初始化为n*n的布尔矩阵，每个g[i][j]均为false。表示没有任何边
	}

	//返回节点个数
	public int V() {
		return n;
	}

	//返回边的个数
	public int E() {
		return m;
	}

	//向图中添加一个边
	public void addEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;

		if(hasEdge(v, w))   return;

		g[v][w] = true;
		if(!directed) {
			//无向图：另一边也需要置为true
			g[w][v] = true;
		}
		m ++;
	}

	// 验证图中是否有从v到w的边
	public boolean hasEdge(int v, int w) {
		assert v >= 0 && v < n;
		assert w >= 0 && w < n;
		return g[v][w];
	}

	// 显示图的信息
	@Override
	public void show(){

		for( int i = 0 ; i < n ; i ++ ){
			for( int j = 0 ; j < n ; j ++ )
				System.out.print( (g[i][j] ? "1":".") +"\t");
			System.out.println();
		}
	}

	// 返回图中一个顶点的所有邻边
	// 由于java使用引用机制，返回一个Vector不会带来额外开销,
	public Iterable<Integer> adj(int v) {
		assert v >= 0 && v < n;
		Vector<Integer> adjV = new Vector<>();
		for (int i = 0; i < n; i++) {
			if(g[v][i])     adjV.add(i);
		}
		return adjV;
	}

}
