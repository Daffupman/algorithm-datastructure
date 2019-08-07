package algo.Ch10_greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Daffupman
 * @description leetcode: 435 Non-overlapping Intervals
 * @date 2019/8/7 19:09
 */
public class Example02_id435 {

	public int eraseOverlapIntervals(int[][] intervals) {

		if(intervals.length == 0) {
			return 0;
		}

		Arrays.sort(intervals, (o1, o2) -> {

			if(o1[o1.length-1] != o2[o2.length-1]) {
				return o1[o1.length-1] - o2[o2.length-1];
			}
			return o1[0] - o2[0];
		});

		int res = 1;
		int pre = 0;
		for (int i = 1; i < intervals.length; i++) {
			if(intervals[i][0] >= intervals[pre][intervals[pre].length-1]) {
				res ++;
				pre = i;
			}
		}

		return intervals.length - res;
	}

}
