package Ch03_heap;

import Ch01_linear.Queue;

/**
 * @program: data-structure
 * @description: 基于最大堆的队列
 * @author: Daffupman
 * @create: 2019-03-11 21:19
 **/
public class ProrityQueue<E extends Comparable<E>> implements Queue<E> {

	private MaxHeap<E> maxHeap;

	public ProrityQueue(){
		maxHeap = new MaxHeap<>();
	}

	@Override
	public int getSize(){
		return maxHeap.getSize();
	}

	@Override
	public boolean isEmpty(){
		return maxHeap.isEmpty();
	}

	@Override
	public E getFront(){
		return maxHeap.findMax();
	}

	@Override
	public void enqueue(E e){
		maxHeap.add(e);
	}

	@Override
	public E dequeue(){
		return maxHeap.extractMax();
	}

}
