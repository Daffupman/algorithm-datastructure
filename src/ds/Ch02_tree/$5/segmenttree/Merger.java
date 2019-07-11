package ds.Ch02_tree.$5.segmenttree;

/**
 * @description 融合器的接口定义
 * @author Daffupman
 * @date 2019-03-30 12:40
 */
public interface Merger<E> {

	//将给定的两个元素融合，返回融合后的结果，融合的方式由用户自定义
	E merge(E a, E b);
	
}
