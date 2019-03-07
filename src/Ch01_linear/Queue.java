package Ch01_linear;

/**
 * @program: data-structure
 * @description: 队列的接口方法
 * @author: Daffupman
 * @create: 2019-03-07 18:00
 **/
public interface Queue<E> {

	//返回队列的大小
	int getSize();

	//判断队列是否为空
	boolean isEmpty();

	//入队
	void enqueue(E e);

	//出队
	E dequeue();

	//返回队首元素
	E getFront();

}
