package algo.Ch02_sort.sort_n2;

import java.util.Arrays;

/**
 * @description 插入排序，当数据规模比较小的时候，使用插入排序。性能会得到一定的提升
 * @author Daffupman
 * @date 2019/7/10 19:09
 */
public class InsertionSort {

	private void sort1(int[] arr) {

		//从索引为1处开始向前面的有序序列中插入
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 ; j--) {
				if(arr[j] < arr[j-1]) {
					//向前遍历，后面的元素比相邻的前一个元素小就交换
					int temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
				}
			}
		}
	}

	//优化：暂存待插入的元素，减少大量的交换行为
	private void sort2(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			//暂存待插入的元素
			int e = arr[i];
			//j保存着待插入元素应当插入的位置
			int j = i;
			for ( ; j > 0; j--) {
				//条件不能写成arr[j] < arr[j-1],因为arr[j]很有可能会被前一个元素覆盖，而不是本来的待插入元素了
				if(e < arr[j-1]) {
					arr[j] = arr[j-1];
				}
			}
			//j已经是待插入元素应该存在的位置了
			arr[j] = e;
		}

	}

	public static void main(String[] args) {

		int[] arr = {5,4,3,2,1};
//		new InsertionSort().sort1(arr);
		new InsertionSort().sort2(arr);
		System.out.println(Arrays.toString(arr));

	}

}
