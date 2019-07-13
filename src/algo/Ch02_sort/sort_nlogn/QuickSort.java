package algo.Ch02_sort.sort_nlogn;

import java.util.Arrays;

/**
 * @description 快速排序
 * @author Daffupman
 * @date 2019/7/12 21:16
 */
public class QuickSort {


	private static void sort(int[] arr) {
		double begin = System.nanoTime();
		quickSort(arr, 0 , arr.length-1);
		double end = System.nanoTime();
		double time = (end-begin)/1000000000;
		System.out.println(time);

		begin = System.nanoTime();
		quickSort3Ways(arr, 0 ,arr.length-1);
		end = System.nanoTime();
		time = (end-begin)/1000000000;
		System.out.println(time);

	}

	private static void quickSort(int[] arr, int l, int r) {
		if(r-l < 16) {
			//当数据规模很小的时候，改用插入排序
			insertionSort(arr, l ,r);
			return;
		}
		int p = partition1(arr, l, r);
		quickSort(arr, l, p-1);
		quickSort(arr, p+1, r);
	}

	//quickSort1Way:
	//分区的时候，与基准元素相同的元素可能会被划分到大于基准元素的范围
	//这样很容易造成左右两端的不平衡
	private static int partition1(int[] arr, int l, int r) {
		// 优化：针对一个近乎有序的数组
		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
		// 这样的期望复杂度为O(nlogn)
		swap(arr, l, (int)(Math.random()*(r-l+1))+l);

		//设立基准元素
		int v = arr[l];
		//j是大于和小于元素e的分界点，j属于小于e的区域
		int j = l;
		//指针i指向待分配的元素
		for (int i = l+1; i <= r; i++) {
			if(arr[i] < v) {
				//若当前元素小于基准元素，将当前元素和分界点j后一位元素交换,同时需要扩大小于基准元素的范围（++j）
				j ++;
				swap(arr, j, i);
			}
		}
		//一轮partition的最后，需要交换基准元素索引和分界点索引j上元素
		swap(arr, l, j);
		//向上返回分界点索引
		return j;
	}

	//quickSort2ways:
	//返回j，使得arr[l...j-1] <= arr[j],arr[j+1...r] >= arr[j]
	//也就是与基准元素相同的元素会被分到分界点的两边，比单路快排有着比较好的平衡
	private static int partition2(int[] arr, int l, int r) {
		//在[l...r]中随机选取基准点
		swap(arr, l, (int) (Math.random()*(r-l+1)+l));
		//标注l位置上的元素为基准点
		int v = arr[l];

		//设立两个指针i和j：i指向下一个小于基准点元素应该放入的位置
		//               j指向下一个大于基准点元素应该放入的位置
		//i和j初始在数组的两端,所以：[l+1...i) <= v , (j...r] >= v
		int i = l+1, j = r;
		while(true) {
			//i和j指向分别扫描，直到i指针发现当前元素大于v，j指针发现当前元素小于v时停止
			while(i <= r && arr[i] <= v)     i++;
			while(j >= l+1 && arr[j] >= v)     j--;

			if(i > j) {
				//当数组是倒序，如[5,4,3,2,1]时会进入
				//在i和j扫面后，i为r+1,j为r，此时i已经不再范围内，所以break
				//或者这个范围内已经划分完时，就会进入，所以要break；
				break;
			}
			//交换i和j索引上的元素
			swap(arr, i , j);
			//两指针前进
			i++;    j--;
		}
		swap(arr, l, j);

		return j;
	}

	//quickSort3Ways：
	//对于数组，将被划分成三个区域，小于v、等于v和大于v的区域
	private static void quickSort3Ways(int[] arr, int l, int r) {
		// 对于小规模数组, 使用插入排序
		if( r - l <= 15 ){
			insertionSort(arr, l, r);
			return;
		}

		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
		swap(arr, l, (int) (Math.random()*(r-l+1))+l);

		int v = arr[l];

		int lt = l;     //arr[l+1...lt] < v
		int gt = r+1;   //arr[gt...r] > v
		int i = l+1;    //arr[lt+1...i) == v

		//=====【核心逻辑】=====
		while (i < gt) {
			//[i...gt)区域为待定范围
			if(arr[i] < v) {
				swap(arr, i, lt+1);
				i ++; lt ++;
			} else if(arr[i] > v) {
				swap(arr, i, gt-1);
				gt --;
			} else {
				i ++;
			}
		}
		//====================
		swap(arr, l ,lt);
		quickSort3Ways(arr, l, lt-1);
		quickSort3Ways(arr, gt, r);
	}

	//在arr的局部范围上插排，注意一些边界值
	private static void insertionSort(int[] arr, int l, int r) {
		for (int i = l+1; i <= r; i++) {
			int e = arr[i];
			int j = i;
			for (; j > l && arr[j-1] > e; j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = e;
		}
	}

	private static void swap(int[] arr, int i, int j) {
		if(i == j) return;
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void main(String[] args) {
		int[] arr = new int[10000000];
//		int[] arr = {5,2,3,4,1,0};
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random()*(arr.length));
		}
		double begin = System.nanoTime();
		sort(arr);

	}

}
