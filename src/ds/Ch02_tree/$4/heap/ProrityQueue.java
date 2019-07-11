package ds.Ch02_tree.$4.heap;

import ds.Ch01_linear.$3.queue.Queue;

/**
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
	public int size(){
		return maxHeap.size();
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
