package algo.Ch04_search;

import java.util.TreeSet;

/**
 * @author Daffupman
 * @description leetcode: 220 Contains Duplicate II
 * @date 2019/8/1 11:59
 */
public class Example07_id220 {

	//时间复杂度：O(nlogn)
	//空间复杂度：O(k)
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

		// 这个问题的测试数据在使用int进行加减运算时会溢出
		// 所以使用long long
		TreeSet<Long> record = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {

			Long ceil = record.ceiling((long) nums[i] - (long) t);
			if (ceil != null && ceil <= (long) nums[i] + (long) t)
				return true;

			record.add((long) nums[i]);

			if (record.size() == k + 1)
				record.remove((long) nums[i - k]);
		}

		return false;
	}

}
