package algo.Ch02_sort.sort_n2;

import java.util.Arrays;

/**
 * @author Daffupman
 * @description 选择排序
 * @date 2019/7/10 18:57
 */
public class SelectionSort {

	private void sort(int[] arr) {
		int n = arr.length;

		//从索引i（初始为0）处开始，在数组中，和之后的元素相比，取小值放在i处
		//只需要比较数组长度-1次
		for (int i = 0; i < n-1; i++) {
			//假定当前元素是本次选取的最小值
			int minIndex = i;
			for (int j = i+1; j < n; j++) {
				//索引i和索引j处的元素比较，取较小的那个
				if(arr[i] > arr[j])  {
					minIndex = j;
				}
			}
			if(minIndex != i) {
				//只有当minIndex值改变时，才会发生交换，避免了频繁交换
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = {5,4,3,2,1};
		new SelectionSort().sort(arr);
		System.out.println(Arrays.toString(arr));
	}

}
