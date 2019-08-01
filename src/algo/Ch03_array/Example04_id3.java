package algo.Ch03_array;

/**
 * @author Daffupman
 * @description leetcode:3 Longest Substring Without Repeating Character
 *              滑动窗口
 * @date 2019/7/28 11:48
 */
public class Example04_id3 {

	public int lengthOfLongestSubstring(String s) {
		//申请一个256大小的数组记录字符是否重复（ASCII码），
		boolean[] repeat = new boolean[256];
		int l = 0, r = -1;      //滑动窗口为[l...r]
		int res = 0;            //记录最长的滑动窗口大小

		while(l < s.length()) {
			if(r + 1 < s.length() && !repeat[s.charAt(r+1)]) {
				//只要滑动窗口右界的下一个元素不是重复的，就加入窗口内，同时该字符标记为重复
				repeat[s.charAt(++r)] = true;
			} else {

				repeat[s.charAt(l++)] = false;
			}

			//更新最大滑动窗口的大小
			res = r-l+1 > res ? res : r-l+1;
 		}

		return res;
	}


}
