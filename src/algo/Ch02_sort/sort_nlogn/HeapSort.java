package algo.Ch02_sort.sort_nlogn;

import java.util.Arrays;

import static java.lang.Math.random;

public class HeapSort {

	// 对整个arr数组使用HeapSort1排序
	// HeapSort1, 将所有的元素依次添加到堆中, 在将所有元素从堆中依次取出来, 即完成了排序
	// 无论是创建堆的过程, 还是从堆中依次取出元素的过程, 时间复杂度均为O(nlogn)
	// 整个堆排序的整体时间复杂度为O(nlogn)
	private static void sort(Comparable[] arr){

		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);
		for (Comparable a : arr) {
			maxHeap.insert(a);
		}

		for( int i = n-1 ; i >= 0 ; i -- ) {
			arr[i] = maxHeap.extractMax();
		}
	}

	//=====================================
	//=============原地堆排序===============
	//=====================================
	private static void  heapSort(int[] arr, int n) {
		//对arr数组heapify操作，包括索引0
		for (int i = (n-1)/2; i >= 0; i--) {
			shiftDown(arr, n, i);
		}
		//将大顶堆的堆顶元素（即最大值）放在数组的最后，
		//将堆范围缩小，然后再次【下沉】，形成新的大顶堆
		//直到做到索引1处，不处理0处的原因是：只有一个元素了，自然是最小值，也不必比较了。
		for (int i = n-1; i > 0; i--) {
			//将堆顶元素放在数组末尾，范围由循环变量i控制，循环减一
			swap(arr, 0, i);
			shiftDown(arr, i, 0);
		}
	}

	private static void shiftDown(int[] arr, int n, int k) {
		while (2*k+1 < n) {
			int j = 2*k+1;
			if(j+1 < n && arr[j+1]>arr[j]) {
				j ++;
			}
			if(arr[k] > arr[j]) break;
			swap(arr, j, k);
			k = j;
		}
	}

	private static void swap(int arr[], int i, int j) {
		if(i == j)  return;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int[] arr = new int[20];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (random() * 20);
		}
//		double start = System.nanoTime();
//		sort(arr);
//		double end = System.nanoTime();
//		System.out.println((end-start)/1000000000);
		heapSort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}

}
