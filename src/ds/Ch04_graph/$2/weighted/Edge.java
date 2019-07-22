package ds.Ch04_graph.$2.weighted;

/**
 * @author Daffupman
 * @description 封装边的信息
 * @date 2019/7/21 14:11
 */
public class Edge<Weight extends Number & Comparable> implements Comparable<Edge> {

	private int a,b;      //边的两个端点
	private Weight weight;

	public Edge(int a, int b, Weight weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	public Edge(Edge<Weight> e) {
		this.a = e.a;
		this.b = e.b;
		this.weight = e.weight;
	}

	//返回第一个端点
	public int v() {
		return a;
	}

	//返回第一个端点
	public int w() {
		return b;
	}

	//返回权值
	public Weight wt() {
		return weight;
	}

	//给定一个端点，返回边上的另一个端点
	public int other(int x) {
		assert x == a || x == b;
		return x == a ? b : a;
	}

	@Override
	public int compareTo(Edge that) {
		return Integer.compare(weight.compareTo(that.wt()), 0);
	}

	@Override
	public String toString() {
		return "" + a + "-" + b + ": " + weight;
	}
}
