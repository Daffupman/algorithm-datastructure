package algo.Ch04_search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Daffupman
 * @description leetcode:1 Two Sum
 * @date 2019/8/1 9:02
 */
public class Example03_id1 {

	//暴力循环
	//时间复杂度：O(n^2)
	public int[] twoSum1(int[] nums, int target) {

		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if(target - nums[i] == nums[j]) {
					return new int[]{i,j};
				}
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	//使用查找表: 准备一个map集合。遍历数组的时候，先检查map中是否有当前遍历元素的互补元素，有的话返回
	//时间复杂度：O(n)
	public int[] twoSum2(int[] nums, int target) {
		//record存储着nums数组中的各个元素和其索引的键值对
		Map<Integer, Integer> record = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if(record.containsKey(complement)) {
				return new int[]{i, record.get(complement)};
			}
			record.put(i, nums[i]);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	public static void main(String[] args) {
		int[] nums = {5,6,100,4, 200,7,8,9, 1, 3, 2};
		int target = 4;
		Example03_id1 test = new Example03_id1();
//		int[] res = test.twoSum1(nums, target);
		int[] res = test.twoSum2(nums, target);
		System.out.println(Arrays.toString(res));
	}

}
