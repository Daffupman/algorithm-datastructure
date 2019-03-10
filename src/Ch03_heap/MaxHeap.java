package Ch03_heap;

import Ch01_linear.Array;

import java.util.Random;

/**
 * @program: data-structure
 * @description: 最大堆
 * @author: Daffupman
 * @create: 2019-03-10 16:24
 **/
public class MaxHeap<E extends Comparable<E>> {

	private Array<E> data;

	public MaxHeap(int capacity){
		data = new Array<>(capacity);
	}

	public MaxHeap(){
		data = new Array<>();
	}

	public MaxHeap(E[] arr){
		data = new Array<>(arr);
		for (int i = parent(arr.length - 1); i >= 0 ; i--) {
			siftDown(i);
		}
	}

	//返回堆中的元素
	public int getSize(){
		return data.getSize();
	}

	//返回堆是否为空
	public boolean isEmpty(){
		return data.isEmpty();
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素父亲节点的索引
	private int parent(int index) {
		if(index == 0) {
			throw new IllegalArgumentException("index-0 doesn't have parent.");
		}
		return (index-1)/2;
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素左孩子节点的索引
	private int leftChild(int index) {
		return index*2 + 1;
	}

	//返回完全二叉树的数组表示中，一个索引表示的元素右孩子节点的索引
	private int rightChild(int index) {
		return index*2 + 2;
	}

	//向堆中添加元素
	public void add(E e){
		data.addLast(e);
		siftUp(data.getSize()-1);
	}

	//元素上浮
	private void siftUp(int k) {
		while(k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0){
			//index位置上的元素大于其父节点上的值，并index不为根index
			data.swap(k, parent(k));
			k = parent(k);
		}
	}

	//查看堆中的最大值
	public E findMax(){
		if(data.getSize() == 0){
			throw new IllegalArgumentException("Cannot findMax when heap is empty");
		}
		return data.get(0);
	}

	//取出堆中最大值（包含删除操作）
	public E extractMax(){
		E ret = findMax();
		data.swap(0, data.getSize()-1);
		data.removeLast();
		siftDown(0);

		return ret;
	}

	//index位置元素下沉
	private void siftDown(int k) {
		while(leftChild(k) < data.getSize()){
			//k节点的左孩子的下标
			int j = leftChild(k);
			if(j+1 < data.getSize() &&
				data.get(j+1).compareTo(data.get(j))>0) {
				//存在右孩子，且右孩子大于左孩子
				j ++;   //j存储着左右孩子中最大值
			}
			if(data.get(k).compareTo(data.get(j))>=0){
				break;
			}
			data.swap(k,j);
			k = j;
		}
	}

	//取出堆中最大值，替换成e,并返回最大值
	public E replace(E e){
		E ret = findMax();
		data.set(0, e);
		siftDown(0);
		return ret;
	}

	public static void main(String[] args) {
//		int n = 1000000;

//		MaxHeap<Integer> maxHeap = new MaxHeap<>();
//		Random random = new Random();
//		for (int i = 0; i < n; i++) {
//			//存入随机数
//			maxHeap.add(random.nextInt(20));
//		}
//		int[] arr = new int[n];
//		//将堆中的数全部取出，放入arr中，arr必定是有序的
//		for (int i = 0; i < n; i++) {
//			arr[i] = maxHeap.extractMax();
//		}

//		//验证arr的有序性
//		for (int i = 1; i < n; i++) {
//			if (arr[i-1] < arr[i]) {
//				throw new IllegalArgumentException("Error");
//			}
//		}

		Integer[] arr = {11,24,3,14,5};
		MaxHeap<Integer> maxHeap = new MaxHeap<>(arr);

		System.out.println("Test MaxHeap complete");

	}

}
