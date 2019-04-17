package Ch02_tree;

/**
 * @program: data-structure
 * @description: 并查集版本1,查找很快
 * @author: Daffupman
 * @create: 2019-04-14 17:15
 **/
public class UnionFind1 implements UF {

	// id数组	0 1 0 1 0 1 0 1 0 1
	//      	-------------------
	// index	0 1 2 3 4 5 6 7 8 9
	private int[] id;   //存储每一个数据对应的所属的集合的编号

	public UnionFind1(int size) {
		id = new int[size];
		for (int i = 0; i < id.length; i++) {
			//初始的状态为：id数组中的每个元素所属的集合都不一样，也就是任意的两个元素都不相连
			id[i] = i;
		}
	}

	@Override
	public int getSize() {
		return id.length;
	}

	//查看元素p和元素q是否所属同一个集合，O(1)
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	//查找元素p所对应的集合编号
	private int find(int p) {
		if(p < 0 || p >= id.length) {
			throw new IllegalArgumentException("p is out of bound");
		}
		return id[p];
	}

	//合并元素p和元素q所属的集合，O(n)
	@Override
	public void UnionElements(int p, int q) {
		int pID = find(p);
		int qID = find(q);

		if(pID == qID) {
			//两个元素已经相连
			return;
		}

		for (int i = 0; i < id.length; i++) {
			if(id[i] == pID) {
				id[i] = qID;
			}
		}
	}
}
