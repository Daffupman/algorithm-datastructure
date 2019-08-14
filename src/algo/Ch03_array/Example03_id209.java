package algo.Ch03_array;

/**
 * @author Daffupman
 * @description leetcode:209 Minimum Size Subarray Sum, 滑动窗口
 * @date 2019/7/28 11:24
 */
public class Example03_id209 {

	public int minSubArrayLen(int s, int[] nums) {
		//[l...r]为滑动窗口
		int l = 0, r = -1;  //初始化时，滑动窗口里是没有元素的
		int sum = 0;        //记录当前滑动窗口内的元素总和
		int res = nums.length+1;    //记录当前寻找到的最小连续子数组的长度

		// 只要当前滑动窗口内的元素之和小于s，滑动窗口左扩张，
		// 反之，右缩减
		while(l < nums.length) {
			if( r+1 < nums.length && sum < s) {
				sum += nums[++r];
			}else{
				sum -= nums[l++];
			}

			if(sum >= s) {
				// 检查
				res = Math.min(res, r-l+1);
			}
		}
		// 如果res值依然是初始值，那么无解
		if(res == nums.length+1) {
			return 0;
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {2,3,1,2,4,3};
		int s = 7;
	}

}
