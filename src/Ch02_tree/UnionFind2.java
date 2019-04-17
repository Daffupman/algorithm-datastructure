package Ch02_tree;

/**
 * @program: data-structure
 * @description: 并查集版本2, 联合很快，数组模拟出的一种由父亲指向孩子的树
 * @author: Daffupman
 * @create: 2019-04-14 17:29
 **/
public class UnionFind2 implements UF {

	private int[] parent;

	public UnionFind2(int size) {
		parent = new int[size];

		for (int i = 0; i < size; i++) {
			//初始时，每个节点都是独立的
			parent[i] = i;
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

		parent[pRoot] = qRoot;
	}
}
