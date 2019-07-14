package algo.Ch02_sort.search;

/**
 * @author Daffupman
 * @description 二分查找法
 * @date 2019/7/14 10:05
 */
public class BinarySearch {

	//二分查找的递归写法
	private static int binarySearchRE(int[] arr, int n, int target) {
		return bs(arr, 0, n-1, target);
	}

	private static int bs(int[] arr, int l, int r, int target) {
		if(l > r)   return -1;

		int mid = l + (r-l)/2;

		if(target < arr[mid]) {
			return bs(arr, l, mid-1, target);
		} else if(target > arr[mid]) {
			return bs(arr, mid+1, r, target);
		} else {
			return mid;
		}
	}

	//二分查找的非递归
	private static int binarySearchNR(int[] arr, int n, int target) {
		//在[l...r]中查找target
		int l = 0, r = n-1;
		while(l <= r) {
			int mid = l +(r-l)/2;
			if(arr[mid] == target) {
				return mid;
			}

			if(target < arr[mid]) {
				r = mid-1;
			} else if(target > arr[mid]) {
				l = mid+1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
		System.out.println(binarySearchRE(arr, arr.length, 4));
	}

}
