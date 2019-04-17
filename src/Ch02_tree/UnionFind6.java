package Ch02_tree;

/**
 * @program: data-structure
 * @description: 并查集版本6,版本5的递归方式
 * @author: Daffupman
 * @create: 2019-04-14 17:29
 **/
public class UnionFind6 implements UF {

	private int[] parent;
	private int[] rank;         //rank[i]表示以i为根的集合所表示的树的层数

	public UnionFind6(int size) {
		parent = new int[size];
		rank = new int[size];

		for (int i = 0; i < size; i++) {
			//初始时，每个节点都是独立的
			parent[i] = i;
			rank[i] = 1;
		}
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	//查找元素p对应的集合编号
	//复杂度为O(h)，h为树的高度
	private int find(int p) {

		if(p < 0 && p >= parent.length) {
			throw new IllegalArgumentException("p is out of bound");
		}

		//递归方式
		if(p != parent[p]) {
			parent[p] = find(parent[p]);
		}
		return parent[p];
	}

	//合并元素p和元素q所属的集合
	//复杂度为O(h)，h为树的高度
	@Override
	public void UnionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);

		if(pRoot == qRoot) {
			return;
		}

		//根据两个元素所在树的元素个数不同判断合并方向
		//将元素个数少的集合合并到元素个数多的集合上
		if(rank[pRoot] < rank[qRoot]) {
			parent[pRoot] = qRoot;
		} else if(rank[qRoot] < rank[pRoot]){
			parent[qRoot] = pRoot;
		}else{
			parent[qRoot] = pRoot;
			rank[pRoot] += 1;
		}
	}
}
