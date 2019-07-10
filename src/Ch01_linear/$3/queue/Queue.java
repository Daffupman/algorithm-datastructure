package Ch01_linear.$3.queue;

/**
 * @description Queue的接口定义
 * 				队列的特点：FIFO
 * @author Daffupman
 * @date 2019-03-07 18:00
 */
public interface Queue<E> {

	//返回队列的大小
	int size();

	//判断队列是否为空
	boolean isEmpty();

	//入队
	void enqueue(E e);

	//出队
	E dequeue();

	//返回队首元素
	E getFront();
	
}
