package algo.Ch03_array;

/**
 * @author Daffupman
 * @description leetcode:75 Sort Colors
 * @date 2019/7/27 19:35
 */
public class Example02_id75 {

	//时间复杂度：O(n)
	//空间复杂度：O(k) ，k为频次数组的大小，在这里为O(1)
	public void sortColors1(int[] nums) {
		//存放数字频次的数组
		int[] freq = new int[3];
		//遍历数组，计算数字的频次
		for (int num : nums) {
			freq[num]++;
		}
		//扫描数组，按频次数组重新赋值
		int index = 0;
		for (int i = 0; i < freq[0]; i++) {
			nums[index ++] = 0;
		}
		for (int i = 0; i < freq[1]; i++) {
			nums[index ++] = 1;
		}
		for (int i = 0; i < freq[1]; i++) {
			nums[index ++] = 2;
		}

	}

	//三路快排的思想
	//时间复杂度：O(n)
	//空间复杂度：O(1),只需遍历一遍数组
	public void sortColors2(int[] nums) {
		//[0,zero] == 0, [zero+1,two-1] == 1, [two, n-1]
		int zero = -1;
		int two = nums.length;

		int i = 0;
		while(i < two){
			if(nums[i] == 0) {
				swap(nums, i ++, ++ zero);
			} else if(nums[i] == 2) {
				swap(nums, i, -- two);
			} else {
				assert(nums[i] == 1);
				i ++;
			}
		}
	}

	private void swap(int[] arr, int i, int j) {
		if(i != j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
		}
	}

	public static void main(String[] args) {
		int[] nums = {2,0,2,1,1,0};
		new Example02_id75().sortColors2(nums);
		for (int num : nums) {
			System.out.print(num+" ");
		}
	}

}
