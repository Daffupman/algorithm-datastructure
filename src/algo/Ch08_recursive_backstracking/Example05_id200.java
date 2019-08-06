package algo.Ch08_recursive_backstracking;

/**
 * @author Daffupman
 * @description leetcode: 200 Number of Islands
 * @date 2019/8/6 18:28
 */
public class Example05_id200 {

	// 这四个方向的顺序并不重要，只要都考虑到了就行
	private int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
	// 考虑到的地图大小
	private int m, n;
	// 记录grid对应的元素是否被访问过
	private boolean[][] visited;

	// 判断坐标（x,y）是否在grid中
	private boolean inArea(int x, int y) {
		return x >= 0 && x < m && y >= 0 && y < n;
	}

	// floodfill算法，本质上是一次深度优先遍历
	// 从grid[x][y]的位置开始floodfill
	private void dfs(char[][] grid, int x, int y) {
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int newx = x + d[i][0];
			int newy = y + d[i][1];
			if(!inArea(newx, newy) && !visited[newx][newy] && grid[newx][newy] == '1') {
				dfs(grid, newx, newy);
			}
		}
	}

	public int numIslands(char[][] grid) {
		m = grid.length;
		if(m == 0) {
			return 0;
		}
		n = grid[0].length;

		visited = new boolean[m][n];

		//存储grid上有多少个岛屿
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j] == '1' && !visited[i][j]) {
					res ++;
					dfs(grid, i, j);
				}
			}
		}

		return res;
	}

}
