package ds.Ch02_tree.$5.segmenttree;

/**
 * @description 线段树
 * @author Daffupman
 * @date 2019-03-30 12:18
 */
public class SegmentTree<E> {

	private E[] data;	//存储数据
	private E[] tree;	//data数据以树的形式存储
	private Merger<E> merger;	//融合器，用于处理区间内的数据
	
	public SegmentTree(E[] arr, Merger<E> merger) {
		this.merger = merger;
		data = (E[])new Object[arr.length];
		//对于区间内的arr数组，把线段树看成一个满二叉树
		//开辟4*length的空间就足以存储线段树的所有节点
		tree = (E[])new Object[4 * arr.length];
		
		System.arraycopy(arr, 0, data, 0, arr.length);
		buildSegmentTree(0, 0, data.length-1);
	}
	
	public int size() {
		return data.length;
	}
	
	//在treeIndex的位置创建表示区间[l,r]的线段树
	private void buildSegmentTree(int treeIndex, int l, int r) {
		if(l == r) {
			//区间已经不能再划分了
			tree[treeIndex] = data[l];
			return;
		}
		
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		//mid = (l+r)/2 : 可能会发生溢出
		int mid = l + (r-l)/2;
		
		buildSegmentTree(leftTreeIndex, l, mid);
		buildSegmentTree(rightTreeIndex, mid+1, r);
		
		tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
	}
	
	//返回区间[queryL,queryR]的值
	public E query(int queryL, int queryR) {
		if(queryL < 0 || queryR >= size() ||
				queryR < 0 || queryR >=size() || queryL > queryR) {
			throw new IllegalArgumentException("illegal query bound");
		}
		
		return query(0, 0, data.length-1, queryL, queryR);
	}
	
	//在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL,queryR]的值
	private E query(int treeIndex, int l, int r, int queryL, int queryR) {
		if(l == queryL && r == queryR) {
			return tree[treeIndex];
		}
		
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		int mid = l + (r-l)/2;
		
		if(queryL >= mid+1) {
			//搜索区间在全部落在右半部分
			return query(rightTreeIndex, mid+1, r, queryL, queryR);
		} else if(queryR <= mid) {
			//搜索区间在全部落在左半部分
			return query(leftTreeIndex, l, mid, queryL, queryR);
		}
		
		//搜索区间落在左右区间
		E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
		E rightResult = query(rightTreeIndex, mid+1, r, mid+1, queryR);
		return merger.merge(leftResult, rightResult);
	}
	
	//将index位置的值更新为e
	public void set(int index, E e) {
		if(index < 0 ||index >= data.length) {
			throw new IllegalArgumentException("illegal index");
		}
		
		data[index] = e;
		set(0, 0 , data.length-1, index, e);
	}
	
	//在以treeIndex为根的线段树中更新index的值为e
	private void set(int treeIndex, int l, int r, int index, E e) {
		if(l == r) {
			tree[treeIndex] = e;
			return;
		}

		// 左孩子节点的索引
		int leftTreeIndex = leftChild(treeIndex);
		// 右孩子节点的索引
		int rightTreeIndex = rightChild(treeIndex);
		int mid = l + (r-l)/2;
		if(index >= mid+1) {
			// 目标索引在右子树
			set(rightTreeIndex, mid+1, r, index, e);
		} else {
			// 目标索引在左子树
			set(leftTreeIndex, l, mid, index, e);
		}
		
		//更新之后需要将线段树上其他节点更新
		tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
	}
	
	private int leftChild(int i) {
		return 2*i + 1;
	}
	
	private int rightChild(int i) {
		return 2*i + 2;
	}
	
	public static void main(String[] args) {
		Integer[] arr = {0,1,2,3,4};
		SegmentTree<Integer> tree = new SegmentTree<>(arr, (a,b)->a+b);
		System.out.println(tree.query(0, 4));
		tree.set(0, 10);
		System.out.println(tree.query(0, 4));
	}
	
}
