package ds.Ch02_tree.$7.unionfind.impl;

import ds.Ch02_tree.$7.unionfind.UF;

/**
 * @description 第一版并查集，数组结构
 * @author Daffupman
 * @date 2019/07/09
 */
public class UnionFind1 implements UF {

	private int[] id;		//用于存储下标对应的元素所在的集合id

	public UnionFind1(int capacity) {
		id = new int[capacity];
		for (int i = 0; i < id.length; i++) {
			//初始化状态：id数组中的元素分属不同的集合
			id[i] = i;
		}
	}
	
	@Override
	public int size() {
		return id.length;
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
		return id[p];
	}
	
	//联接p和q
	@Override
	public void unionElements(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		
		if(pId == qId)	return;
		for (int i = 0; i < id.length; i++) {
			//遍历数组将p和q所在的集合id修改成一致的（包括其他的元素）
			if(id[i] == pId) {
				id[i] = qId;
			}
		}
	}
	
	public static void main(String[] args) {
		UnionFind1 uf = new UnionFind1(10);
		System.out.println(uf.isConnected(1, 3));
		uf.unionElements(1, 3);
		System.out.println(uf.isConnected(1, 3));
	}

}
