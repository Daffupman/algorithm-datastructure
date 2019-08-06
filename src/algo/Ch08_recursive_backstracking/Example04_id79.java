package algo.Ch08_recursive_backstracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daffupman
 * @description leetcode: 79 Word Search
 * @date 2019/8/5 22:23
 */
public class Example04_id79 {

	//二维数组代表着四个方向：上，右，下，左
	private int[][] d = {{-1, 0}, {0,1}, {1,0}, {0,-1}};
	//m行n列
	private int m, n;
	//记录对应的坐标是否被访问过
	private List<List<Boolean>> visited;

	//判断给定的新坐标是否在平面内
	private boolean inArea(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}

	//从board[startx][starty]开始，寻找word[index...word.size())
	private boolean searchWord( List<List<Character>> board, String word, int index, int startx, int starty ) {
		if( index == word.length()-1 ) {
			return board.get(startx).get(starty).equals(word.charAt( index ));
		}

		if( board.get(startx).get(starty).equals(word.charAt( index )) ) {

			//该点已加入结果
			visited.get( startx ).set( starty, true );

			//从startx,starty出发，向四个方向寻找
			for (int i = 0; i < 4; i++) {
				int newx = startx + d[i][0];
				int newy = starty + d[i][1];
				if( inArea(newx, newy) && !visited.get(startx).get(starty) ) {
					if( searchWord( board, word, index + 1, newx, newy ) ) {
						return true;
					}
				}
			}
			//回溯
			visited.get(startx).set(starty, false);
		}

		return false;
	}

	public boolean exist(List<List<Character>> board, String word) {

		m = board.size();
		assert m > 0;
		n = board.get(0).size();
		assert n > 0;

		//初始化board
		visited = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			visited.add( new ArrayList<>(n) );
		}

		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if( searchWord( board, word, 0, i, j ) ) {
					return true;
				}
			}
		}

		return false;
	}

}
