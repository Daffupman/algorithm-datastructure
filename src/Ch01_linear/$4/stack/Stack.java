package Ch01_linear.$4.stack;

/**
 * @description Stack的接口定义
 * 				栈的特点是：FILO
 * @author Daffupman
 * @date 2019-03-07 16:16
 */
public interface Stack<E> {

	//返回栈中的元素个数
	int size();
	
	//返回栈是否为空
	boolean isEmpty();
	
	//压栈
	void push(E e);
	
	//弹栈
	E pop();
	
	//返回栈顶元素
	E peek();
	
}
