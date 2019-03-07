package Ch01_linear;

/**
 * @program: data-structure
 * @description: 栈的接口设计规范
 * @author: Daffupman
 * @create: 2019-03-07 16:16
 **/
public interface Stack<E> {

	//返回栈的大小
	int getSize();

	//判断是否为空
	boolean isEmpty();

	//入栈
	void push(E e);

	//出栈
	E pop();

	//获取栈顶元素
	E peek();

}
