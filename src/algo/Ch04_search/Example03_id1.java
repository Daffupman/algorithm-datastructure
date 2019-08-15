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
			for (int j = i+1; j < nums.length; j++) {
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
			// 计算互补元素
			int complement = target - nums[i];
			if(record.containsKey(complement)) {
				// 如果map中有互补的元素，则返回两者的索引
				return new int[]{i, record.get(complement)};
			}
			// map中没有互补的元素，放入map中
			record.put(nums[i], i);
		}
		throw new IllegalArgumentException("No two sum solution");
	}

	//todo 对撞指针

	public static void main(String[] args) {
		int[] nums = {3,2,4};
		int target = 6;
		Example03_id1 test = new Example03_id1();
//		int[] res = test.twoSum1(nums, target);
		int[] res = test.twoSum2(nums, target);
		System.out.println(Arrays.toString(res));
	}

}
