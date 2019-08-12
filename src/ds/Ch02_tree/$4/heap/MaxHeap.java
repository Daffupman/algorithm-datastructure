package ds.Ch02_tree.$4.heap;

import ds.Ch01_linear.$1.array.Array;

/**
 * @description 大顶堆
 * @author Daffupman
 * @date 2019-03-10 16:24
 */
public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;

	public MaxHeap() {
		data = new Array<>();
	}

	public MaxHeap(int capacity) {
		data = new Array<>(capacity);
	}

	// heapify
	public MaxHeap(E[] arr) {
		data = new Array<>(arr);
		for (int i = parent(size() - 1); i >= 0; i--) {
			siftDown(i);
		}
	}

	//返回堆中的元素
	public int size() {
		return data.size();
	}

	//返回堆是否为空
	public boolean isEmpty() {
		return data.isEmpty();
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素父亲节点的索引
	private int parent(int i) {
		return (i - 1) / 2;
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素左孩子节点的索引
	private int leftChild(int i) {
		return 2 * i + 1;
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素右孩子节点的索引
	private int rightChild(int i) {
		return 2 * i + 2;
	}

	// 上浮
	private void siftUp(int i) {
		while(i > 0 && data.get(parent(i)).compareTo(data.get(i)) < 0) {
			//index位置上的元素大于其父节点上的值，并index不为根index
			data.swap(i, parent(i));
			i = parent(i);
		}
	}

	// 下沉
	private void siftDown(int i) {
		while (leftChild(i) < size()) {
			int j = leftChild(i);
			if (j + 1 < size() && data.get(j + 1).compareTo(data.get(j)) > 0) {
				//存在右孩子，且右孩子大于左孩子
				j++;	//j存储着左右孩子中最大值
			}
			if (data.get(i).compareTo(data.get(j)) > 0) {
				break;
			}
			data.swap(i, j);
			i = j;
		}
	}

	//向堆中添加元素
	public void add(E e) {
		data.addLast(e);
		siftUp(data.size() - 1);
	}

	//查看堆中的最大值
	public E findMax() {
		return data.getFirst();
	}

	//取出堆中最大值（包含删除操作）
	public E extractMax() {
		E ret = findMax();
		data.swap(0, data.size() - 1);
		data.removeLast();
		siftDown(0);

		return ret;
	}

	//将堆顶的元素替换成e，并返回被替换的元素
	public E replace(E e) {
		E ret = findMax();
		data.set(0, e);
		siftDown(0);
		return ret;
	}
	
	public static void main(String[] args) {
		Integer[] arr = {1,2,3,4,5};
		MaxHeap<Integer> heap = new MaxHeap<>(arr);
		System.out.println(heap.extractMax());
		System.out.println(heap.extractMax());
		System.out.println(heap.extractMax());
	}
}
