package algo.Ch04_search;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Daffupman
 * @description leetcode:349 Intersection of Two Arrays
 * @date 2019/7/28 12:32
 */
public class Example01_id349 {

	//时间复杂度：O(n)/O(nlogn),取决于map和set底层实现
	//空间复杂度：O(n)
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set = new HashSet<>();
		for (int i : nums1) {
			set.add(i);
		}

		Set<Integer> res = new HashSet<>();
		for (int value : nums2) {
			if (set.contains(value)) {
				res.add(value);
			}
		}

		int[] arr = new int[res.size()];
		int index = 0;
		for (Integer e : res) {
			arr[index ++] = e;
		}

		return arr;
	}

}
