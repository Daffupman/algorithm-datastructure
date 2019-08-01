package algo.Ch04_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daffupman
 * @description leetcode:350 Intersection of Twp Arrays II
 * @date 2019/7/28 12:57
 */
public class Example02_id350 {

	//时间复杂度：O(n)/O(nlogn),取决于map和set底层实现
	//空间复杂度：O(n)
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums1.length; i++) {
			Integer value = map.get(nums1[i]);
			if(value == null) {
				map.put(nums1[i], 1);
			} else {
				map.put(nums1[i], value+1);
			}
		}
		int[] res = new int[nums1.length+nums2.length];
		int index = 0;
		for (int i : nums2) {
			Integer value = map.get(i);
			if(value != null && value > 0) {
				res[index] = i;
				index ++;
				map.put(i, value-1);
			}
		}
		int[] ret = new int[index];
		System.arraycopy(res, 0, ret, 0, index);
		return ret;
	}

}
