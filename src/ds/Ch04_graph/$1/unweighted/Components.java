package ds.Ch04_graph.$1.unweighted;

/**
 * @author Daffupman
 * @description 求无权图的连通分量
 * @date 2019/7/21 9:26
 */
public class Components {

	Graph G;        //图的引用
	private boolean[] visited;      //记录dfs过程中节点是否被访问过
	private int ccount;    //记录连通分量的个数
	private int[] id;   //每个节点对应的连通分量标记

	public Components(Graph G) {
		//初始化
		this.G = G;
		visited = new boolean[G.V()];
		id = new int[G.V()];
		ccount = 0;
		for (int i = 0; i < G.V(); i++) {
			//初始化中，每个节点默认没有被访问过
			visited[i] = false;
			//每个节点都没有所属的连通分量
			id[i] = -1;
		}

		//求图的连通分量
		for (int i = 0; i < G.V(); i++) {
			if(!visited[i]) {
				//如果当前节点没有被访问过，那么就进行一次dfs
				dfs(i);
				//一轮dfs结束，也就是找到了一个连通分量
				ccount ++;
			}
		}
	}

	//对节点v进行dfs
	private void dfs(int v) {

		//节点已被访问
		visited[v] = true;
		//给当前节点标记所属连通分量
		id[v] = ccount;

		//针对当前节点的邻接节点，再次dfs
		for (int i : G.adj(v)) {
			if(!visited[i]) {
				//只有没有被遍历过的节点才可以再次dfs
				dfs(i);
			}
		}
	}

	// 返回图的连通分量
	public int count() {
		return ccount;
	}

	//查询点v和点w是否连通
	public boolean isConnected(int v, int w) {
		assert v >= 0 && v < G.V();
		assert w >= 0 && v < G.V();

		return id[v] == id[w];
	}

}
