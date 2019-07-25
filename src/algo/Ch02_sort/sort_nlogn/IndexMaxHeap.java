package algo.Ch02_sort.sort_nlogn;

import java.util.Arrays;

/**
 * @author Daffupman
 * @description 最大索引堆
 * @date 2019/7/13 22:57
 */
public class IndexMaxHeap<Item extends Comparable> {

	private Item[] data;      //最大索引堆中的数据，只管添加元素
	private int[] indexes;    //最大索引堆中的索引，每个元素对应着data数组中的一个元素（在元素添加的时候就已经绑定好了），需要交换data中的元素位置时，不直接操作data数组而是交换indexes中的元素
	private int count;        //数组中的元素个数，也是第count个元素存储在data数组索引count处
	private int capacity;     //数组容量

	//创建可以容纳capacity个元素的空堆，索引0不使用
	public IndexMaxHeap(int capacity) {
		data = (Item[]) new Comparable[capacity+1];
		indexes = new int[capacity+1];
		count = 0;
		this.capacity = capacity;
	}

	public int size() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	//向最大索引堆中索引i处插入新元素item，传入的i对用户而言，是从0开始的
	//而事实上索引0处是不存储元素的
	public void insert(int i, Item item) {

		assert count + 1 <= capacity;
		assert i+1 >= 1 && i+1 <= capacity;

		data[++i] = item;
		indexes[++count] = i;

		shiftUp(count);

	}

	//从堆中取出堆顶元素（即最大元素）
	public Item extractMax() {
		assert count > 0;

		Item ret = data[indexes[1]];
		swapIndexes(1, count);
		count --;
		shiftDown(1);

		return ret;
	}

	// 获取最大索引堆中的堆顶元素
	public Item getMax(){
		assert count > 0;
		return data[indexes[1]];
	}

	// 获取最大索引堆中的堆顶元素的索引
	public int getMaxIndex(){
		assert count > 0;
		return indexes[1]-1;
	}

	// 获取最大索引堆中索引为i的元素
	public Item getItem( int i ){
		assert i + 1 >= 1 && i + 1 <= capacity;
		return data[i+1];
	}

	// 将最大索引堆中索引为i的元素修改为newItem
	public void change( int i , Item newItem ){

		i += 1;
		data[i] = newItem;

		// 找到indexes[j] = i, j表示data[i]在堆中的位置
		// 之后shiftUp(j), 再shiftDown(j)
		for( int j = 1 ; j <= count ; j ++ ) {
			if( indexes[j] == i ){
				shiftUp(j);
				shiftDown(j);
				return;
			}
		}
	}

	// 交换索引堆中的索引i和j
	private void swapIndexes(int i, int j){
		int t = indexes[i];
		indexes[i] = indexes[j];
		indexes[j] = t;
	}

	//********************
	//* 最大索引堆核心辅助函数
	//********************

	// 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
	private void shiftUp(int k){

		while( k > 1 && data[indexes[k/2]].compareTo(data[indexes[k]]) < 0 ){
			swapIndexes(k, k/2);
			k /= 2;
		}
	}

	// 索引堆中, 数据之间的比较根据data的大小进行比较, 但实际操作的是索引
	private void shiftDown(int k){

		while( 2*k <= count ){
			int j = 2*k;
			if( j+1 <= count && data[indexes[j+1]].compareTo(data[indexes[j]]) > 0 )
				j ++;

			if( data[indexes[k]].compareTo(data[indexes[j]]) >= 0 )
				break;

			swapIndexes(k, j);
			k = j;
		}
	}

	// 测试索引堆中的索引数组index
	// 注意:这个测试在向堆中插入元素以后, 不进行extract操作有效
	public boolean testIndexes(){

		int[] copyIndexes = new int[count+1];

		for( int i = 0 ; i <= count ; i ++ )
			copyIndexes[i] = indexes[i];

		copyIndexes[0] = 0;
		Arrays.sort(copyIndexes);

		// 在对索引堆中的索引进行排序后, 应该正好是1...count这count个索引
		boolean res = true;
		for( int i = 1 ; i <= count ; i ++ )
			if( copyIndexes[i-1] + 1 != copyIndexes[i] ){
				res = false;
				break;
			}

		if( !res ){
			System.out.println("Error!");
			return false;
		}

		return true;
	}

	// 测试 IndexMaxHeap
	public static void main(String[] args) {

		int N = 5;
		IndexMaxHeap<Integer> indexMaxHeap = new IndexMaxHeap<>(N);
		for( int i = 0 ; i < N ; i ++ )
			indexMaxHeap.insert( i , (int)(Math.random()*N) );
		assert indexMaxHeap.testIndexes();
	}

}
