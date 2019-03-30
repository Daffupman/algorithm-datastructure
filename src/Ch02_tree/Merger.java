package Ch02_tree;

/**
 * @program: data-structure
 * @description: 融合器
 * @author: Daffupman
 * @create: 2019-03-30 12:40
 **/
public interface Merger<E> {

	//将给定的两个元素融合，返回融合后的结果，融合的方式由用户自定义
	E merge(E a,E b);

}
