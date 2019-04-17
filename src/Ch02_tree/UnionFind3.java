package Ch02_tree;

/**
 * @program: data-structure
 * @description: 并查集版本3, 基于size的优化
 * @author: Daffupman
 * @create: 2019-04-14 17:29
 **/
public class UnionFind3 implements UF {

	private int[] parent;
	private int[] sz;         //size[i]表示以i为根的集合中元素的个数

	public UnionFind3(int size) {
		parent = new int[size];
		sz = new int[size];

		for (int i = 0; i < size; i++) {
			//初始时，每个节点都是独立的
			parent[i] = i;
			sz[i] = 1;
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

		while(p != parent[p]) {
			p = parent[p];
		}
		return p;
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
		if(sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}

		parent[pRoot] = qRoot;
	}
}
