package algo.Ch03_array;

/**
 * @author Daffupman
 * @description leetcode：283 Move Zeros
 * @date 2019/7/27 18:30
 */
public class Example01 {

	//时间复杂度：O(n)
	//空间复杂度：O(n)
	public void moveZeros1(int[] nums) {
		int[] arr = new int[nums.length];
		int j = 0;
		for (int num : nums) {
			if (num != 0) {
				arr[j ++] = num;
			}
		}
		System.arraycopy(arr, 0, nums, 0, arr.length);
	}

	//时间复杂度：O(n)
	//空间复杂度：O(1)
	public void moveZeros2(int[] nums) {

		int k = 0;      //索引内都是非0元素
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] != 0) {
				nums[k++] = nums[i];
			}
		}
		for(int i = k; i < nums.length; i ++) {
			nums[i] = 0;
		}
	}

	public void moveZeros3(int[] nums) {
		int k = 0;      //索引内都是非0元素
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] != 0) {
				if(i != k) {
					int temp = nums[i];
					nums[i] = nums[k];
					nums[k] = temp;
				}
				k ++;
			}
		}
	}

	public static void main(String[] args) {
		int[] nums = {0,1,0,3,12};
//		int[] nums = {1,2,0,3,0};
		new Example01().moveZeros3(nums);
		for (int num : nums) {
			System.out.print(num+" ");
		}
	}

}
