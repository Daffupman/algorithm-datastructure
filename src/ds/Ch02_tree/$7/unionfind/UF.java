package ds.Ch02_tree.$7.unionfind;

/**
 * @description 并查集的接口
 * @author Daffupman
 * @date 2019-04-14 17:06
 */
public interface UF {

	//返回这个并查集需要考虑多少个元素
	int size();

	//返回p和q是否连接
	boolean isConnected(int p, int q);

	//将p和q两个元素联合起来
	void unionElements(int p, int q);
	
}
