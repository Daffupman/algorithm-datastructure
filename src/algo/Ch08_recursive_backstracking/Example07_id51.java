package algo.Ch08_recursive_backstracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 51 N-Queens
 * @date 2019/8/6 19:16
 */
public class Example07_id51 {

	private boolean[] col = null;
	private boolean[] dia1 = null;
	private boolean[] dia2 = null;
	private ArrayList<List<String>> res = new ArrayList<>();

	// 尝试在一个n皇后问题中，摆放第index行的皇后位置
	private void putQueen(int n, int index, LinkedList<Integer> row) {

		if(index == n) {
			res.add(generateBoard(n, row));
			return;
		}

		for (int i = 0; i < n; i++) {
			//尝试将第index行的皇后摆放在第i列
			if(!col[i] && !dia1[index + 1] && !dia2[index - i +n - 1]) {
				row.add(i);
				col[i] = true;
				dia1[index + i] = true;
				dia2[index - i + n -1] = true;

				putQueen(n, index+1, row);

				col[i] = false;
				dia1[index + i] = false;
				dia2[index - i + n -1] = false;
				row.removeLast();
			}
		}
	}

	private List<String> generateBoard(int n, LinkedList<Integer> row) {
		assert row.size() == n;

		List<String> board = new ArrayList<>();
		for(int i = 0 ; i < n ; i ++){
			char[] charArray = new char[n];
			Arrays.fill(charArray, '.');
			charArray[row.get(i)] = 'Q';
			board.add(new String(charArray));
		}
		return board;
	}

	public List<List<String>> solveNQueens(int n) {

		LinkedList<Integer> row = new LinkedList<>();

		col = new boolean[n];
		dia1 = new boolean[2 * n - 1];
		dia2 = new boolean[2 * n - 1];

		putQueen(n, 0, row);

		return res;
	}
}
