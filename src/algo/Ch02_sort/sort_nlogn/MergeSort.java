package algo.Ch02_sort.sort_nlogn;

import java.util.Arrays;

/**
 * @description 归并排序
 * @author Daffupman
 * @date 2019/7/11 20:39
 */
public class MergeSort {

	private static void sort(int[] arr) {
		sort2(arr, 0, arr.length-1);
	}

	//递归使用归并排序,对[l...r]范围进行排序
	private static void sort1(int[] arr, int l, int r) {
		//递归的终止：左边的指针大于右边
		if(l >= r)  return;

		int mid = l + (r-l)/2;
		//先将数组分半
		sort1(arr, l, mid);
		sort1(arr, mid+1, r);

		//优化：增加merge的判断条件，对近乎有序的数组十分有效
		//经过上两步的操作，已经保证了在[l,mid]和[mid+1,r]这两个区间的有序性
		//arr[mid]是前一个数组的最大值，arr[mid+1]是前一个数列的最小值
		//如果数组a的最大值都小于数组b的最小值，那么数组a中的所有元素都是小于数组b的最小值的。
		if(arr[mid] > arr[mid+1]) {
			//只有当偏左边的数组的最大小于偏右边数组的最小值时，才会发生归并的操作
			merge(arr, l, mid, r);
		}
	}

	//sort1的优化
	private static void sort2(int[] arr, int l, int r) {
		if(r - l <= 15) {
			//在数据规模小于16时，改用插入排序，性能会有一定提升
			insertSort(arr, l, r);
			return;
		}

		int mid = l + (r-l)/2;
		sort2(arr, l, mid);
		sort2(arr, mid+1, r);

		if(arr[mid] > arr[mid+1]) {
			merge(arr, l, mid, r);
		}
	}

	//
	private static void merge(int[] arr, int l, int mid, int r) {

		//生成辅助数组，拷贝原数组范围为[l,r]的部分，将aux数组的数归并到arr原数组中
		int[] aux = Arrays.copyOfRange(arr, l, r+1);

		int left = l;       //指向偏左数组的第一个元素
		int right = mid+1;  //指向偏右数组的第一个元素

		//在[l...r]范围选取元素加入到aux中
		//在访问数组的时候要始终减去偏移量l
		for(int i = l; i <= r; i ++) {
			if(left > mid) {
				//指向左边数组的指针已经遍历完了，那么直接加入右边还没遍历完的数
				arr[i] = aux[right - l];
				right ++;
			} else if(right > r) {
				//指向右边数组的指针已经遍历完了，那么直接加入左边的没遍历完的数
				arr[i] = aux[left - l];
				left ++;
			} else if(aux[left-l] < aux[right-l]) {
				//aux偏左的序列（数组）的left指针上的元素小于偏右的right指针指向的元素，那么将较小元素复制到原数组arr中
				arr[i] = aux[left-l];
				left ++;
			} else {
				arr[i] = aux[right-l];
				right ++;
			}
		}

	}

	//在数组arr的[l,r]范围内进行插入排序
	private static void insertSort(int[] arr, int l, int r) {
		for (int i = l+1; i <= r; i++) {
			//暂存待插入的元素
			int e = arr[i];
			//j的结果是待插入元素应该放入到前面序列的位置
			int j = i;
			for ( ; j > 0 && e < arr[j-1]; j--) {
				//反向去寻找待插入的索引位置
				arr[j] = arr[j-1];
			}
			arr[j] = e;
		}
	}

	public static void main(String[] args) {
		int[] arr = {5,7,3,9,2};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
