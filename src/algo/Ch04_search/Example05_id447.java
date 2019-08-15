package algo.Ch04_search;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Daffupman
 * @description leetcode: 447 Number of Boomerangs
 * @date 2019/8/1 11:03
 */
public class Example05_id447 {

	public int numberOfBoomerangs(int[][] points) {
		int res = 0;
		for (int i = 0; i < points.length; i++) {
			//record存储着点之间的距离
			Map<Integer, Integer> record = new HashMap<>();
			for (int j = 0; j < points.length; j ++) {
				if( j != i ) {
					//计算距离时不开根，以保证精度
					int dis = dis(points[i], points[j] );
					if( record.containsKey(dis) ) {
						record.put( dis, record.get(dis) + 1 );
					} else {
						record.put( dis, 1 );
					}
				}
			}
			for (Integer dis : record.keySet()) {
				res += record.get(dis) * ( record.get( dis ) - 1 );
			}
		}
		return res;
	}

	// a^2 + b^ = c^2
	private int dis(int[] pa, int pb[]){
		return (pa[0] - pb[0]) * (pa[0] - pb[0]) +
				(pa[1] - pb[1]) * (pa[1] - pb[1]);
	}

}
