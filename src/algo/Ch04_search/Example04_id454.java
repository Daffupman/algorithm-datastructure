package algo.Ch04_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daffupman
 * @description leetcode: 454 4Sum II
 * @date 2019/8/1 9:52
 */
public class Example04_id454 {

	/*
	 * 使用查找表：组合元素放入查找表中
	 * 时间复杂度：O(n^2)
	 * 空间复杂度：O(n^2)
	 */
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		//将C和D的组合以及他们出现的次数放入查找表
		Map<Integer, Integer> record = new HashMap<>();
		for (int i = 0; i < C.length; i++) {
			for (int j = 0; j < D.length; j++) {
				int key = C[i] + D[j];
				Integer val = record.get(key);
				if(val != null) {
					record.put(key, val+1);
				} else {
					record.put(key, 1);
				}
			}
		}

		//记录四个元素之和为0的个数
		int res = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B.length; j++) {
				if( record.containsKey(0-A[i]-B[j])) {
					res += record.get(0 - A[i] - B[j]);
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {

//[-1,-1]
//[-1,1]
//[-1,1]
//[1,-1]
		int[] A = {-1,-1};
		int[] B = {-1,1};
		int[] C = {-1,1};
		int[] D = {1,-1};

		int res = new Example04_id454().fourSumCount(A, B, C, D);
		System.out.println(res);
	}

}
