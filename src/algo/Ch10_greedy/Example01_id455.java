package algo.Ch10_greedy;

import java.util.Arrays;

/**
 * @author Daffupman
 * @description leetcode: 455 Assign Cookies
 * @date 2019/8/7 18:54
 */
public class Example01_id455 {

	// 先尝试满足最贪心的小朋友
	// g数组存储小朋友，s数组存储饼干
	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);

		//gi,si指针初始化时指向数组的末尾
		int gi = g.length - 1, si = s.length - 1;
		int res = 0;
		while(gi >= 0 && si >= 0) {
			if(s[si] >= g[gi]) {
				//当前饼干满足当前小朋友
				res ++;
				si --;
				gi --;
			} else {
				//当前饼干不满足当前小朋友，跳过当前小朋友
				gi --;
			}
		}

		return res;
	}

}
