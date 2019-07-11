package algo.Ch02_sort.sort_n2;

import java.util.Arrays;

/**
 * @description
 * @author Daffupman
 * @date 2019/7/10 18:31
 */
public class BubbleSort {

	private void sort1(int[] arr) {

		int n = arr.length;

		//外层循环控制循环次数
		for (int i = 1; i < n; i++) {

			//内层循环控制比较范围
			for (int j = 0; j < n-i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	private void sort2(int[] arr) {
		int n = arr.length;

		//外层循环控制循环次数
		for (int i = 1; i < n; i++) {

			//设立有序标志位
			boolean sorted  = true;

			//内层循环控制比较范围
			for (int j = 0; j < n-i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;

					//发生了排序动作，说明数组的后面不是有序的
					//sorted标志位置为false
					sorted = false;
				}
			}

			//在每轮交换结束时检查sorted标识符
			if(sorted) {
				//当前面的序列已经有序，就不需要排序了，跳出循环
				break;
			}

		}
	}

	public static void main(String[] args) {
		int[] arr = {5,4,3,2,1};
		new BubbleSort().sort2(arr);
		System.out.println(Arrays.toString(arr));
	}

}