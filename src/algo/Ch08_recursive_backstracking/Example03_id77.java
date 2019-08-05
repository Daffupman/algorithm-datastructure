package algo.Ch08_recursive_backstracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 77 Combinations
 * @date 2019/8/5 18:26
 */
public class Example03_id77 {

	private List<List<Integer>> res = null;

	// 求解C(n,k), 当前已经找到的组合存储在c中, 需要从start开始搜索新的元素
	private void generateCombinations( int n, int k, int start, LinkedList<Integer> c ) {

		if(c.size() == k){
			res.add((List<Integer>)c.clone());
			return;
		}

		//优化：剪枝
		// i < n ==> i < n - (k.c.size()) + 1
		// 还有k - c.size()个空位, 所以, [i...n] 中至少要有 k - c.size() 个元素
		// i最多为 n - (k - c.size()) + 1
		for(int i = start ; i < n - (k - c.size()) +1 ; i ++){
			c.addLast(i);
			generateCombinations(n, k, i + 1, c);
			c.removeLast();
		}

	}

	public List<List<Integer>> combine(int n, int k) {

		res = new ArrayList<List<Integer>>();
		if(n <= 0 || k <= 0 || k > n)
			return res;

		LinkedList<Integer> c = new LinkedList<Integer>();
		generateCombinations(n, k, 1, c);

		return res;
	}

}
