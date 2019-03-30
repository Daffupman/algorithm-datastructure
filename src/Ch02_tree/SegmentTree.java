package Ch02_tree;

/**
 * @program: data-structure
 * @description: 线段树
 * @author: Daffupman
 * @create: 2019-03-30 12:18
 **/
public class SegmentTree<E> {

	private E[] tree;
	private E[] data;
	private Merger<E> merger;

	public SegmentTree(E[] arr,Merger<E> merger){

		this.merger = merger;

		data = (E[]) new Object[arr.length];

		for(int i=0; i<arr.length; i++){
			data[i] = arr[i];
		}
		//对于区间内的arr数组，把线段树看成一个满二叉树
		//开辟4*length的空间就足以存储线段树的所有节点
		tree = (E[]) new Object[4*data.length];

		buildSegmentTree(0,0,data.length-1);
	}

	//在treeIndex的位置创建表示区间[l,r]的线段树
	private void buildSegmentTree(int treeIndex,int l,int r){
		//区间已经不能再划分了
		if(l == r){
			tree[treeIndex] = data[l];
			return;
		}
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		//mid = (l+r)/2 : 可能会发生溢出
		int mid = l + (r-l)/2;

		buildSegmentTree(leftTreeIndex,l,mid);
		buildSegmentTree(rightTreeIndex,mid+1,r);

		tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
	}

	public int getSize(){
		return data.length;
	}

	public E get(int index){
		if(index < 0 || index >= data.length){
			throw new IllegalArgumentException("Index is illegal.");
		}
		return data[index];
	}

	private int leftChild(int index){
		return 2*index+1;
	}

	private int rightChild(int index){
		return 2*index+2;
	}

	//返回区间[queryL,queryR]的值
	public E query(int queryL,int queryR){
		if(queryL < 0 || queryL >= data.length ||
			queryR < 0 || queryR >= data.length ||queryL > queryR){
			throw new IllegalArgumentException("Index is illegal.");
		}

		return query(0,0,data.length-1,queryL,queryR);
	}

	//在以treeID为根的线段树中[l...r]的范围里，搜索区间[queryL,queryR]的值
	private E query(int treeIndex,int l,int r,int queryL,int queryR){
		if(l == queryL && r == queryR){
			return tree[treeIndex];
		}
		int mid = l + (r - l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);

		if(queryL >= mid + 1){
			//搜索区间在全部落在右半部分
			return query(rightTreeIndex,mid+1,r,queryL,queryR);
		}else if(queryR <= mid){
			//搜索区间在全部落在左半部分
			return query(leftTreeIndex,l,mid,queryL,queryR);
		}

		//搜索区间落在左右区间
		E rightResult = query(leftTreeIndex,l,mid,queryL,mid);
		E leftResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
		return merger.merge(leftResult,rightResult);
	}

	//将index位置的值更新为e
	public void set(int index, E e){
		if(index < 0 || index >= data.length){
			throw new IllegalArgumentException("Index is illegal.");
		}
		data[index] = e;
		set(0,0,data.length-1,index,e);
	}

	//在以treeIndex为根的线段树中更新index的值为e
	private void set(int treeIndex,int l,int r,int index,E e){
		if(l == r){
			tree[treeIndex] = e;
			return;
		}
		int mid = l + (r-l) / 2;
		int leftTreeIndex = leftChild(treeIndex);
		int rightTreeIndex = rightChild(treeIndex);
		if(index >= mid+1){
			set(rightTreeIndex,mid+1,r,index,e);
		}else{
			set(leftTreeIndex,l,mid,index,e);
		}
		//更新之后需要将线段树上其他节点更新
		tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
	}


	@Override
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append('[');
		for (int i = 0; i < tree.length; i++) {
			if(tree[i] != null){
				res.append(tree[i]);
			}else{
				res.append("null");
			}
			if(i != tree.length - 1){
				res.append(',');
			}
		}
		return res.toString();
	}

	public static void main(String[] args) {
		Integer[] nums = {-2,0,3,-5,2,-1};
		SegmentTree<Integer> segTree = new SegmentTree<>(nums, (a,b) -> a+b);

		System.out.println(segTree.query(0, 1));
	}

}
