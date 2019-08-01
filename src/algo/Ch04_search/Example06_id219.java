package algo.Ch04_search;

import java.util.HashSet;

/**
 * @author Daffupman
 * @description leetcode: 219 Contains Duplicate II
 * @date 2019/8/1 11:28
 */
public class Example06_id219 {

	//滑动窗口
	//时间复杂度：O(n)
	//空间复杂度：O(k)
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		HashSet<Integer> record = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if( record.contains(nums[i]) ) {
				return true;
			}

			record.add( nums[i] );

			//record中最多有k个元素
			if( record.size() == k + 1 ) {
				record.remove(nums[i-k]);
			}
		}
		return false;
	}

}
