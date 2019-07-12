package ds.Ch02_tree.$7.unionfind.impl;

import ds.Ch02_tree.$7.unionfind.UF;

/**
 * @description 第三版并查集，孩子节点指向父节点的结构，数组模拟
 * 				基于size的优化
 * @author Daffupman
 * @date 2019/07/09
 */
public class UnionFind3 implements UF {

	private int[] parent;		//用于存储下标对应的元素所在的集合parent
	private int[] sz;			//存储对应地，以sz[i]为根的树的元素个数

	public UnionFind3(int capacity) {
		parent = new int[capacity];
		sz = new int[capacity];
		for (int i = 0; i < parent.length; i++) {
			//初始化状态：parent数组中的父节点指向都是指向自己
			parent[i] = i;
			//sz初始化为1
			sz[i] = 1;
		}
	}
	
	@Override
	public int size() {
		return parent.length;
	}
	
	//返回指定的p和q的元素是否同属一个集合
	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	//返回索引p上的元素所在的集合id
	private int find(int p) {
		if(p < 0 || p > size()-1) {
			throw new IllegalArgumentException("index out of bound!");
		}
		return parent[p];
	}
	
	//联接p和q
	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		
		if(pRoot == qRoot)	return;
		
		//修改p索引处的父节点指向即可
		parent[p] = qRoot;
		
		if(sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[pRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}
	
	public static void main(String[] args) {
		UnionFind3 uf = new UnionFind3(10);
		System.out.println(uf.isConnected(1, 3));
		uf.unionElements(1, 3);
		System.out.println(uf.isConnected(1, 3));
	}

}
