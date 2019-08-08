package algo.Ch02_sort.sort_n2;

import java.util.Arrays;

/**
 * @description 冒泡排序
 * @author Daffupman
 * @date 2019/7/10 18:31
 */
public class BubbleSort {

	private void sort1(int[] arr) {

		int n = arr.length;

		// 外层循环控制循环次数
		for (int i = 1; i < n; i++) {

			// 内层循环控制比较范围, 比较的是相邻的两个元素
			// 每轮的交换都是从0开始比较
			// 然后将最大值放在数组的末尾，i ++,
			// 范围（n-i）自然地就缩小
			for (int j = 0; j < n-i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}

	// 优化思路：
	// 每次循环的时候，都是从0比较到n-i的位置
	// 如果在某轮的比较中，没有发生两个元素的交换
	// 那么也就意味着，范围[0...n-i]内的元素的是有序的
	// 这样的话，之后的循环都是没有意义的，我们应该跳过。
	//
	// 这样的优化思路，对于有序序列或某序列的前段部分是有序的的情况是比较有效的
	private void sort2(int[] arr) {
		int n = arr.length;
		for(int i = 1; i < n; i ++) {
			//在每轮循环比较之前，初始化有序标志位order为true
			boolean ordered = true;

			for(int j = 0; j < n-i; j ++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;

					//发生了元素交换，[0...n-i]并不是有序的
					ordered = false;
				}
			}
			//在每轮循环比较结束后，检查ordered标志位
			if(ordered) {
				// 标志位ordered为true，也就是[0...n-i]范围内的元素是有序的
				// 终止外层循环，冒泡排序结束
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