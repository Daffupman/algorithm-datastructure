package algo.Ch08_dp;

/**
 * @author Daffupman
 * @description 动态规划
 * @date 2019/7/30 18:33
 */
public class DP {

	private static int n = 40;
	private static int[] memo = new int[n];
	int num = 0;

	//递归方式
	private static int fibRe(int n) {
		assert n >= 0;
		if(n <=1) return n;
		return fibRe(n-1) + fibRe(n-2);
	}

	//记动态规划，忆化搜索，自上而下
	private static int fibMemo(int n) {
		assert n >= 0;
		if(n <= 1)  return n;
		if(memo[n] == 0) {
			memo[n] = fibMemo(n-1) + fibMemo(n-2);
		}

		return memo[n];
	}

	//记动态规划，记忆化搜索，自下而上
	private static int fibMemoBU(int n) {
		int[] memo2 = new int[n+1];

		memo2[0] = 0;
		memo2[1] = 1;
		for (int i = 2; i <= n; i++) {
			memo2[i] = memo2[i-1] + memo2[i-2];
		}
		return memo2[n];
	}

	public static void main(String[] args) {
		double start = System.currentTimeMillis();
//		int res = fibRe(n);
//		int res = fibMemo(n);
		int res = fibMemoBU(n);
		double end = System.currentTimeMillis();

		System.out.println("f("+n+")"+" = "+res+", 耗时（秒）："+(end-start)/1000);

	}

}
