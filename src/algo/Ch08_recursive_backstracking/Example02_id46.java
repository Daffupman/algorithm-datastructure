package algo.Ch08_recursive_backstracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 46 Permutations
 * @date 2019/8/4 21:07
 */
public class Example02_id46 {

	//结果集
	private List<List<Integer>> res = new ArrayList<>();
	//记录元素是否被使用
	private List<Boolean> used = null;

	// p中保存了一个有index个元素的排列
	// 向这个排列的末尾添加第index+1个元素，获得一个index+1个元素的排列
	private void generatePermutation(List<Integer> nums, int index, List<Integer> p) {

		if( index == nums.size() ) {
			res.add( p );
			return;
		}

		for (int i = 0; i < nums.size(); i++) {
			if( !used.get(i) ) {
				//将nums[i]添加进p中
				p.add( nums.get(i) );
				used.set(i, true);
				generatePermutation(nums, index+1, p);
				p.remove(p.size()-1);
				used.set(i, false);
			}
		}

	}

	public List<List<Integer>>  permute(List<Integer> nums) {

		if( nums.size() == 0 ) {
			return res;
		}

		used = new ArrayList<>(nums.size());
		List<Integer> p = new ArrayList<>();
		generatePermutation( nums, 0, p);

		return res;

	}

}
