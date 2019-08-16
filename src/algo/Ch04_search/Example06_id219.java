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
		// 维持一个size为k+1大小的滑动窗口
		HashSet<Integer> record = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if( record.contains(nums[i]) ) {
				// 窗口内有相同元素则返回true
				// 此时这两个相同元素的索引之差必然小于等于k
				return true;
			}

			// 加入当前遍历的元素
			record.add( nums[i] );

			//record中最多有k个元素
			if( record.size() == k + 1 ) {
				// 如果窗口容量已满，则去除最左边的元素
				record.remove(nums[i-k]);
			}
		}
		return false;
	}

}
